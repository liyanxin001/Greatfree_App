package com.greatfree.cluster.ecommerce.v3.message;


import edu.greatfree.multicast.message.MulticastResponse;

public class AddToCartResponse extends MulticastResponse {

	private static final long serialVersionUID = 1944874644988516498L;
	
	private boolean isSucceeded;

	public AddToCartResponse(boolean isSucceeded, String collaboratorKey) {
		super(TRAppID.ADD_TO_CART_REQUEST, collaboratorKey);
		this.setSucceeded(isSucceeded);
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}


}
