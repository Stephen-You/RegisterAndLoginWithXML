package practice.first.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.vcode.utils.VerifyCode;

/**
 * ͼƬ��֤��
 * @author Yorick
 */
@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡͼƬ��֤�����
		VerifyCode vc = new VerifyCode();
		//����һ��ͼƬ
		BufferedImage image = vc.getImage();
		//��ȡ�ĸ��ַ�
		String code = vc.getText();
		//���ַ���������session��
		request.getSession().setAttribute("code", code);
		//ͼƬ������ͻ���
		response.setContentType("image/jpeg");
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

}
