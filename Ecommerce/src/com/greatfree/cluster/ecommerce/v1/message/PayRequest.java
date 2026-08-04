package com.greatfree.cluster.ecommerce.v1.message;

import edu.greatfree.cluster.message.ClusterRequest;


public class PayRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = -1929149239066394920L;
	
	private String userName;

	public PayRequest(String userName) {
		super(userName, AppID.PAY_REQUEST);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
