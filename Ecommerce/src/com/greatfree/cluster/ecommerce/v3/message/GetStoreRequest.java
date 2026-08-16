package com.greatfree.cluster.ecommerce.v3.message;


import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.cluster.message.ClusterRequest;


public class GetStoreRequest extends ClusterRequest{
	
	
	
	private static final long serialVersionUID = -8750413280374412693L;
	
    private String userName;
	private String storeName;

	public GetStoreRequest(String userName, String storeName) {
		super(storeName, AppID.GET_STORE_REQUEST);
		this.userName = userName;
		this.storeName = storeName;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}