package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.ClusterRequest;

import com.greatfree.cluster.ecommerce.v1.message.AppID;

public class CreateStoreRequest extends ClusterRequest{

	private static final long serialVersionUID = -3259202518480274368L;
	
	private String storeName;

	public CreateStoreRequest(String storeName) {
		super(storeName, AppID.CREATE_STORE_REQUEST);
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
