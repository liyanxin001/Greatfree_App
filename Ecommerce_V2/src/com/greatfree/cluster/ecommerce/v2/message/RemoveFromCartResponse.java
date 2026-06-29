package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class RemoveFromCartResponse extends MulticastResponse{
	
	private static final long serialVersionUID = -4682541080995828820L;
	private boolean isSucceeded;
	

	public RemoveFromCartResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.REMOVE_FROM_CART_RESPONSE, collaboratorKey);
		this.setSucceeded(isSucceeded);
		
	}


	public boolean isSucceeded() {
		return isSucceeded;
	}


	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
