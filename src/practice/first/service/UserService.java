package practice.first.service;

import practice.first.dao.UserDAO;
import practice.first.domain.User;

public class UserService {
	private UserDAO userDAO = new UserDAO();//��Ϊservice����dao������������Ҫ�Ȱ�DAO�����ã�
	
	//ע��
	public void regist(User user) throws UserException{
		//����ע���û��û����Ƿ����,
		User user2 = userDAO.findByUsername(user.getUsername());
		if (user2 != null) throw new UserException("�û����Ѿ�����!");
		//����ע���û������Ƿ��Ѿ�ע��
		user2 = userDAO.findByEmail(user.getEmail());
		if(user2 != null) throw new UserException("�����Ѿ�����!");
		//����û��������䶼����,��ô���ñ��淽��,���浽���ݿ�
		userDAO.addUser(user);
	}
	
	//��¼
	public User login(User user) throws UserException{
		User user1 = userDAO.findByUsername(user.getUsername());
		//�ж����������,��ô�׳��쳣,�����ж������Ƿ���ȷ,�û���,���붼��ȷ,��ô�ʹ����¼�ɹ�
		if(user1==null) throw new UserException("�û���������");
		if(!user1.getPassword().equals(user.getPassword())) throw new UserException("���벻��ȷ!");
		//��½�ɹ�,�����û���Ϣ
		return user1;
		
	}
}
