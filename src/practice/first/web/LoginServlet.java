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
 * �ѱ���Ϣ��װ��JavaBean,Ȼ���ж��Ƿ������ݿ�����
 * @author Yorick
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		UserService userService = new UserService();
		//�ж��Ƿ�������û�
		try {
			User user1 = userService.login(user);
			//ִ�е�����˵��,������û�,��ô���û���Ϣ���浽session
			request.getSession().setAttribute("user", user1);
			//�ض���
			response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		} catch (UserException e) {
			//ִ�е�����,˵��û������û�,�����û����������,�Ѵ�����Ϣ���浽request����,������Ϣ���ص���¼ҳ��
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}

}
