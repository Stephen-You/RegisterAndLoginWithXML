package practice.first.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import practice.first.domain.User;
import practice.first.service.UserException;
import practice.first.service.UserService;

/**
 * 把表单信息封装到JavaBean,然后判断是否在数据库中有
 * @author Yorick
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		UserService userService = new UserService();
		//判断是否有这个用户
		try {
			User user1 = userService.login(user);
			//执行到这里说明,有这个用户,那么把用户信息保存到session
			request.getSession().setAttribute("user", user1);
			//重定向
			response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		} catch (UserException e) {
			//执行到这里,说明没有这个用户,或者用户名密码错误,把错误信息保存到request域中,错误信息返回到登录页面
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}

}
