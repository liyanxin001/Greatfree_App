package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.multicast.message.MulticastResponse;

public class CreateStoreResponse extends MulticastResponse{

	private static final long serialVersionUID = -33000443047824287L;
	
	private boolean isSucceeded;

	public CreateStoreResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.CREATE_STORE_RESPONSE, collaboratorKey);
		this.isSucceeded = isSucceeded;
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
