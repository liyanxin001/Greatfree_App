package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.cluster.message.ClusterRequest;

public class GetCartRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = 6440999665214108702L;
	private String userName;
	

	public GetCartRequest(String userName) {
		super(userName, AppID.GET_CART_REQUEST);
		this.setUserName(userName);
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

}
