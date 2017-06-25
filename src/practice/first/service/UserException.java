package practice.first.service;
//异常类,作为验证时抛出的异常,让web层捕获
public class UserException extends Exception{

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
