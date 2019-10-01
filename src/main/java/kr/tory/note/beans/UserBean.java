package kr.tory.note.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserBean {
	
	@Size(min=4, max=16, message="계정은 최소 4글자 최대 16글자 이내로 작성해주세요")
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="계정은 한글,특수문자를 제외한 영문,숫자로 구성 되어야 합니다.")
	private String User;
	
	@Pattern(regexp="^[a-zA-Z0-9]*$", message="비밀번호는 한글,특수문자를 제외한 영문,숫자로 구성 되어야 합니다.")
	@Size(min=8, max=16, message="비밀번호는 최소 8글자 최대 16글자입니다.")
	private String Password;
	
	@Size(min=2, max=10, message="닉네임은 최소 4글자 최대 10글자로 구성되어야합니다.")
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
		return "UserBean [User=" + User + ", Password=" + Password + ", Nickname=" + Nickname + ", Sex=" + Sex
				+ ", Age=" + Age + "]";
	}
	
}
