package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class CartRegistryRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = -2213335248527667441L;
	private String userName;

	public CartRegistryRequest(String username) {
		super(username, AppID.CART_REGISTRY_REQUEST);
		this.setUserName(username);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

}
