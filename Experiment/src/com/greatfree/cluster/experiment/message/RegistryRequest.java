package com.greatfree.cluster.experiment.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class RegistryRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = 7679786456746488160L;
	private String username;
	private String password;

	public RegistryRequest(String username, String password) {
		super(username, AppID.REGISTRY_REQUEST);
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
