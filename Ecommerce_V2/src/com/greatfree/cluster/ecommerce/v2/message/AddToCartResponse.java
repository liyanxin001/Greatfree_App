package com.greatfree.cluster.ecommerce.v2.message;



import edu.greatfree.multicast.message.MulticastResponse;

public class AddToCartResponse extends MulticastResponse{
	
	private static final long serialVersionUID = 6468081410236404230L;
	private boolean isSucceeded;

	public AddToCartResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.ADD_TO_CART_RESPONSE, collaboratorKey);
		this.isSucceeded = isSucceeded;

	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

    
	

	

}
