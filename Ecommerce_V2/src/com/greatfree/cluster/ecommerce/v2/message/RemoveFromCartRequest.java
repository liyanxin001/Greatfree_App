package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class RemoveFromCartRequest extends ClusterRequest{
	
	private static final long serialVersionUID = 7616443523024569212L;
	
	private String userName;
	private String productName;

	public RemoveFromCartRequest(String username, String productName) {
		super(username, AppID.REMOVE_FROM_CART_REQUEST);
		this.userName = username;
		this.productName = productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
