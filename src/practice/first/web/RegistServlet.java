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
 * �û�ע��web��
 * @author Yorick
 *
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������������Ӧ����
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService us = new UserService();
		//��װ�����ݵ�JavaBean��
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		/* ��form����У��
		 *   > ���У�����
		 *     * ���������Ϣ��request��
		 *     * ����form��request�򣨻��ԣ�
		 */
		// ����һ��Map�������������еĴ�����Ϣ
		// keyΪ���ֶ����ƣ�valueΪ������Ϣ
		Map<String, String> map = new HashMap<String, String>();
		//У��username
		String username = user.getUsername();
		if(username==null||username.trim().isEmpty()){
			//���������Ϣ��map��
			map.put("username", "�û�������Ϊ��!");
		}else if (username.length()<6||username.length()>15) {
			map.put("username", "�û���������6��15λ֮��!");
		}
		//��֤ͼƬ��֤��
		String code = (String) request.getSession().getAttribute("code");
		String verifyCode = request.getParameter("verifyCode");
		if(!code.equals(verifyCode)){
			map.put("verifyCode", "ͼƬ��֤�����!");
		}
		//������ڴ�����Ϣ
		/*
		 * 1. ����map��request��
		 * 2. ���浱ǰuser��request�򣨻��ԣ�
		 * 3. ת����regist.jsp
		 * 4. ���return�����������ݲ���ִ��
		 */
		if(map.size()>0){
			request.setAttribute("map", map);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
		try {
			//�����֤��Ϣ����ȷ,��ô����regist(),����user����
			us.regist(user);
			/*
			 * 3. ���ִ�е����ע���ѳɹ���
			 * * ����ɹ���Ϣ��request��
			 * * ת����msg.jsp��ʾ�ɹ���Ϣ
			 */
			request.setAttribute("msg", "ע��ɹ�");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		} catch (UserException e) {
			/*
			 * 4. ���ִ�е����˵��ע��ʧ���ˣ�
			 * * �����쳣��Ϣ��request��
			 * * ת����regist.jsp��ʾ������Ϣ
			 */
			request.setAttribute("msg", e.getMessage());
			/*
			 * Ϊ��ҳ��Ļ��ԣ���Ҫ�ѵ�ǰ�����ݱ��浽request���У�
			 */
			request.setAttribute("user", user);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}
	}

}
