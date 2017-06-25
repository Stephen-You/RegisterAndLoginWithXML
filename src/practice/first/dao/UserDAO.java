package practice.first.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import practice.first.domain.User;

public class UserDAO {
	//ͨ���û�������
	public User findByUsername(String username){
		User user = null;
		try {
			//����������
			SAXReader sr = new SAXReader();
			//����user.xml�ļ�,��ȡ�ĵ�����
			Document document = sr.read("F:/user.xml");
			//����ָ������������user
			Element element = (Element) document.selectSingleNode("//user[@username='"+username+"']");
			//���û�в�ѯ��user,��ô����null,��ѯ��,��ô��user��װ��user������
			if(element == null){
				user = null;
			} else {
				user =  toUser(element);
			}
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	//ͨ����������֤,��ѯ�����Ƿ��Ѿ�ע��
	public User findByEmail(String email){
		User user = new User();
		try {
			//����������
			SAXReader sr = new SAXReader();
			Document document;
			document = sr.read("F://user.xml");
			Element element = (Element) document.selectSingleNode("//user[@email='"+email+"']");
			if(element == null){
				 user = null;
			}else {
				user = toUser(element);
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	//��ע���û����浽���ݿ�
	public void addUser(User user){	
		try {
			SAXReader sr = new SAXReader();
			Document document = sr.read("F://user.xml");
			//��ȡ���ڵ�
			Element element = document.getRootElement();
			//����Ԫ������һ��userԪ��
			Element element2 = element.addElement("user");
			//����userΪuser��ǩ������
			element2.addAttribute("username", user.getUsername());
			element2.addAttribute("password", user.getPassword());
			element2.addAttribute("email", user.getEmail());
			//��documentд��XML�ļ�
			// ���������ʽ������ʹ��\t�������Զ����У�
			OutputFormat opf = new OutputFormat("\t", true);
			//ȥ�����õĿհף�\t�����С��ո�ȵȣ���
			opf.setTrimText(true);
			//���������
			PrintWriter pw = new PrintWriter("F://user.xml", "UTF-8");
			// ʹ��format��out����XMLWriter
			XMLWriter writer = new XMLWriter(pw, opf);
			//��documentд�뵽Ŀ���ļ�
			writer.write(document);
			writer.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//��userԪ��ת��Ϊuser����
	public User toUser(Element element){
		//����һ��User����
		User user = new User();
		//��element����Ϊusername����ֵ������User�������Ϊusername����
		user.setUsername(element.attributeValue("username"));
		user.setPassword(element.attributeValue("password"));
		user.setEmail(element.attributeValue("email"));
		return user;
	}
}
