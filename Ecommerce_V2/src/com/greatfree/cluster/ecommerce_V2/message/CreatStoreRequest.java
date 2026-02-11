package com.greatfree.cluster.ecommerce_V2.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class CreatStoreRequest extends ClusterRequest{

	private static final long serialVersionUID = -4578503194680002353L;
	
	
	
	private String storeName;
	private String owner;

	public CreatStoreRequest(String storeName, String owner) {
		super(storeName, AppID.CREATE_STORE_REQUEST);
		this.setStoreName(storeName);
		this.setOwner(owner);
		
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
