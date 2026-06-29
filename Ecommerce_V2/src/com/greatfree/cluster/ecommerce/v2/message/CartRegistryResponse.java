package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class CartRegistryResponse extends MulticastResponse{
	
	private static final long serialVersionUID = 6315747722069537859L;
	private boolean isSucceeded;

	public CartRegistryResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.CART_REGISTRY_RESPONSE, collaboratorKey);
		this.isSucceeded = isSucceeded;
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}
	
	

}
