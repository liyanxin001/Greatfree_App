package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.ClusterNotification;

import com.greatfree.cluster.ecommerce.v1.message.AppID;

public class CreateStoreRequest extends ClusterNotification{

	private static final long serialVersionUID = -3259202518480274368L;
	
	
	private String owner;
	private String storeName;

	public CreateStoreRequest(String owner, String storeName) {
		super(storeName, AppID.CREATE_STORE_REQUEST);
		this.owner = owner;
		this.storeName = storeName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
