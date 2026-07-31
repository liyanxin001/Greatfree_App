package com.greatfree.cluster.ecommerce.v1.message;

import edu.greatfree.cluster.message.ClusterRequest;


public class GetStoreRequest extends ClusterRequest{
	
	
	
	private static final long serialVersionUID = -8750413280374412693L;

	private String storeName;

	public GetStoreRequest(String storeName, String randomDestination) {
		super(randomDestination,  AppID.GET_STORE_REQUEST);
		this.storeName = storeName;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
