package com.andy.model;

public class User {
	private Integer userid;

	private String username;

	private String password;

	private Integer age;

	private String sex;

	public User() {
		super();
	}

	public User(String username, String password, Integer age, String sex) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.sex = sex;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", age=" + age
				+ ", sex=" + sex + "]";
	}
	
	
}