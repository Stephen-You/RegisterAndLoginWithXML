package practice.first.Junit;

import org.junit.Test;

import practice.first.dao.UserDAO;
import practice.first.domain.User;



/**
 * 测试
 * @author cxf
 *
 */
public class UserDaoTest {
	@Test
	public void testFindByUsername() {
		UserDAO userDAO = new UserDAO();//创建dao
		User user = userDAO.findByUsername("王五");//查找名为李四的对象
		System.out.println(user);//输出user对象
	}

	@Test
	public void testFindByEmail() {
		UserDAO userDAO = new UserDAO();//创建dao
		User user = userDAO.findByEmail("lisi@163.com");//查找名为lisi@163.com的对象
		System.out.println(user);//输出user对象
	}

	@Test
	public void testAddUser() {
		UserDAO userDAO = new UserDAO();//创建dao
		
		// 创建user对象，更初始化
		User form = new User();
		form.setUsername("wangWu");
		form.setPassword("789");
		form.setEmail("wangWu@163.com");
		
		userDAO.addUser(form);//使用dao添加user对象到数据库
	}
}
