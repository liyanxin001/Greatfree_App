package com.greatfree.cluster.experiment.message;

import org.greatfree.message.ServerMessage;

public class LoginRequest extends ServerMessage {

	private static final long serialVersionUID = -6753626455211866466L;
	
	private String username;
	private String password;

	public LoginRequest(int type, String key) {
		super(type, key);
		// TODO Auto-generated constructor stub
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

}
