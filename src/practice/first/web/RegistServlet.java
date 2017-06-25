package practice.first.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import practice.first.domain.User;
import practice.first.service.UserException;
import practice.first.service.UserService;
import cn.itcast.commons.CommonUtils;

/**
 * 用户注册web层
 * @author Yorick
 *
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理请求编码和响应编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService us = new UserService();
		//封装表单数据到JavaBean中
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		/* 对form进行校验
		 *   > 如果校验出错
		 *     * 保存错误信息到request域
		 *     * 保存form到request域（回显）
		 */
		// 创建一个Map，用来保存所有的错误信息
		// key为表单字段名称，value为错误信息
		Map<String, String> map = new HashMap<String, String>();
		//校验username
		String username = user.getUsername();
		if(username==null||username.trim().isEmpty()){
			//保存错误信息到map中
			map.put("username", "用户名不能为空!");
		}else if (username.length()<6||username.length()>15) {
			map.put("username", "用户名长度在6到15位之间!");
		}
		//验证图片验证器
		String code = (String) request.getSession().getAttribute("code");
		String verifyCode = request.getParameter("verifyCode");
		if(!code.equals(verifyCode)){
			map.put("verifyCode", "图片验证码错误!");
		}
		//如果存在错误信息
		/*
		 * 1. 保存map到request域
		 * 2. 保存当前user到request域（回显）
		 * 3. 转发到regist.jsp
		 * 4. 添加return，让下面内容不再执行
		 */
		if(map.size()>0){
			request.setAttribute("map", map);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		try {
			//如果验证信息都正确,那么调用regist(),传递user参数
			us.regist(user);
			/*
			 * 3. 如果执行到这里，注册已成功！
			 * * 保存成功信息到request域
			 * * 转发到msg.jsp显示成功信息
			 */
			request.setAttribute("msg", "注册成功");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		} catch (UserException e) {
			/*
			 * 4. 如果执行到这里，说明注册失败了！
			 * * 保存异常信息到request域
			 * * 转发到regist.jsp显示错误信息
			 */
			request.setAttribute("msg", e.getMessage());
			/*
			 * 为了页面的回显，需要把当前表单数据保存到request域中！
			 */
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}
	}

}
