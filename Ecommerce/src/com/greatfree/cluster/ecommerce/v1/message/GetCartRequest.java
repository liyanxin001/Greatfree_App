package com.greatfree.cluster.ecommerce.v1.message;

import edu.greatfree.cluster.message.ClusterRequest;


public class GetCartRequest extends ClusterRequest {
	
	

	private static final long serialVersionUID = -8798567153935681724L;
	
	
	private String userName;
	
	

	public GetCartRequest(String userName) {
		super(userName,AppID.GET_CART_REQUEST);
		this.userName = userName;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String clientID) {
		this.userName = clientID;
	}

	
	

}
