package com.greatfree.cluster.ecommerce.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class AddToCartResponse extends MulticastResponse{
	
	private static final long serialVersionUID = -3061785624925846286L;
	
	private boolean isSuccessed;

	public AddToCartResponse(boolean isSuccessed, String collaboratorKey) {
		super(AppID.ADD_TO_CART_RESPONSE, collaboratorKey);
		this.isSuccessed = isSuccessed;
	}

	public boolean IsSuccessed() {
		return isSuccessed;
	}

	public void setSuccessed(boolean isSuccessed) {
		this.isSuccessed = isSuccessed;
	}

}
