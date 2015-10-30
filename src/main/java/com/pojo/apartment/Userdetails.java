package com.pojo.apartment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Userdetails {
	@XmlElement
	private String lname;
	@XmlElement
	private String telnum;
	@XmlElement
	private String pwd;
	@XmlElement
	private String uname;
	@XmlElement
	private String email;

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return uname;
	}
	public void setUserName(final String userName) {
		this.uname = userName;
	}
}
