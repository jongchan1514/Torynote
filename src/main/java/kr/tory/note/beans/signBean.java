package kr.tory.note.beans;

public class signBean {
	
	private String User;
	private String Password;
	private String Nickname;
	private String Sex;
	private String Age;
	
	
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
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	@Override
	public String toString() {
		return "signBean [User=" + User + ", Password=" + Password + ", Nickname=" + Nickname + ", Sex=" + Sex
				+ ", Age=" + Age + "]";
	}
}
