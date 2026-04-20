package com.greatfree.cluster.experiment.message;


import edu.greatfree.cluster.message.ClusterRequest;

public class LoginRequest extends ClusterRequest {

	private static final long serialVersionUID = -6753626455211866466L;
	
	private String username;
	private String password;

	public LoginRequest(String username, String password) {
		super(username, AppID.LOGIN_REQUEST);
		this.username = username;
		this.password = password;
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
