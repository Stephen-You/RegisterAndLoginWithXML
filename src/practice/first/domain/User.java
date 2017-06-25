package practice.first.domain;
//JavaBean封装注册信息及登录信息
public class User {
	private String username;
	private String password;
	private String repassword;
	private String email;
	private String verifyCode;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", repassword=" + repassword + ", email=" + email
				+ ", verifyCode=" + verifyCode + "]";
	}
}
