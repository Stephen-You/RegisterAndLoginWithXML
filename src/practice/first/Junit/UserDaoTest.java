package practice.first.Junit;

import org.junit.Test;

import practice.first.dao.UserDAO;
import practice.first.domain.User;



/**
 * ����
 * @author cxf
 *
 */
public class UserDaoTest {
	@Test
	public void testFindByUsername() {
		UserDAO userDAO = new UserDAO();//����dao
		User user = userDAO.findByUsername("����");//������Ϊ���ĵĶ���
		System.out.println(user);//���user����
	}

	@Test
	public void testFindByEmail() {
		UserDAO userDAO = new UserDAO();//����dao
		User user = userDAO.findByEmail("lisi@163.com");//������Ϊlisi@163.com�Ķ���
		System.out.println(user);//���user����
	}

	@Test
	public void testAddUser() {
		UserDAO userDAO = new UserDAO();//����dao
		
		// ����user���󣬸���ʼ��
		User form = new User();
		form.setUsername("wangWu");
		form.setPassword("789");
		form.setEmail("wangWu@163.com");
		
		userDAO.addUser(form);//ʹ��dao���user�������ݿ�
	}
}
