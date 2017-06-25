package practice.first.service;

import practice.first.dao.UserDAO;
import practice.first.domain.User;

public class UserService {
	private UserDAO userDAO = new UserDAO();//因为service依赖dao，所以我们需要先把DAO创建好！
	
	//注册
	public void regist(User user) throws UserException{
		//检验注册用户用户名是否存在,
		User user2 = userDAO.findByUsername(user.getUsername());
		if (user2 != null) throw new UserException("用户名已经存在!");
		//检验注册用户邮箱是否已经注册
		user2 = userDAO.findByEmail(user.getEmail());
		if(user2 != null) throw new UserException("邮箱已经存在!");
		//如果用户名和邮箱都正常,那么调用保存方法,保存到数据库
		userDAO.addUser(user);
	}
	
	//登录
	public User login(User user) throws UserException{
		User user1 = userDAO.findByUsername(user.getUsername());
		//判断如果不存在,那么抛出异常,否则判断密码是否正确,用户名,密码都正确,那么就代表登录成功
		if(user1==null) throw new UserException("用户名不存在");
		if(!user1.getPassword().equals(user.getPassword())) throw new UserException("密码不正确!");
		//登陆成功,返回用户信息
		return user1;
		
	}
}
