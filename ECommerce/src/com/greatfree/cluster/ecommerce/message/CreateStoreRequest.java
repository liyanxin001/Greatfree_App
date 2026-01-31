package com.greatfree.cluster.ecommerce.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class CreateStoreRequest extends ClusterRequest {
	
	
	private static final long serialVersionUID = -6335641991934876395L;

	private String userName;
	private String storeName;
	
	public CreateStoreRequest(String userName, String storeName) {
		super(RequestType.BROADCAST_REQUEST, AppID.CREATE_STORE_REQUEST);
		this.setUserName(userName);
		this.setStoreName(storeName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
