package kr.tory.note.beans;

public class loginBean {
	
	private String User;
	private String Password;
	
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	@Override
	public String toString() {
		return "loginBean [User=" + User + ", Password=" + Password + "]";
	}
}
