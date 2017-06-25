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
	//通过用户名查找
	public User findByUsername(String username){
		User user = null;
		try {
			//创建解析器
			SAXReader sr = new SAXReader();
			//加载user.xml文件,获取文档对象
			Document document = sr.read("F:/user.xml");
			//根据指定的条件查找user
			Element element = (Element) document.selectSingleNode("//user[@username='"+username+"']");
			//如果没有查询到user,那么返回null,查询到,那么把user封装到user对象中
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
	//通过邮箱名验证,查询邮箱是否已经注册
	public User findByEmail(String email){
		User user = new User();
		try {
			//创建解析器
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
	//把注册用户保存到数据库
	public void addUser(User user){	
		try {
			SAXReader sr = new SAXReader();
			Document document = sr.read("F://user.xml");
			//获取根节点
			Element element = document.getRootElement();
			//给根元素新增一个user元素
			Element element2 = element.addElement("user");
			//保存user为user标签的属性
			element2.addAttribute("username", user.getUsername());
			element2.addAttribute("password", user.getPassword());
			element2.addAttribute("email", user.getEmail());
			//把document写到XML文件
			// 创建输出格式化器！使用\t缩进，自动换行！
			OutputFormat opf = new OutputFormat("\t", true);
			//去除无用的空白（\t、换行、空格等等！）
			opf.setTrimText(true);
			//创建输出流
			PrintWriter pw = new PrintWriter("F://user.xml", "UTF-8");
			// 使用format和out创建XMLWriter
			XMLWriter writer = new XMLWriter(pw, opf);
			//把document写入到目标文件
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
	//把user元素转换为user对象
	public User toUser(Element element){
		//创建一个User对象
		User user = new User();
		//把element的名为username属性值，赋给User对象的名为username属性
		user.setUsername(element.attributeValue("username"));
		user.setPassword(element.attributeValue("password"));
		user.setEmail(element.attributeValue("email"));
		return user;
	}
}
