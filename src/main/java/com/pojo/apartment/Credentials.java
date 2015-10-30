package com.pojo.apartment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Credentials  {
	@XmlElement
	private String email;
	@XmlElement
	private String salt;

	public String getUsername() {
		return email;
	}
	public void setUsername(String username) {
		this.email = username;
	}
	public String getPassword() {
		return salt;
	}
	public void setPassword(String password) {
		this.salt = password;
	}

}
