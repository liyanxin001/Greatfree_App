package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class WithdrawFromStoreResponse extends MulticastResponse{
	
	
	private static final long serialVersionUID = -1948179343069080736L;
	private boolean isSucceeded;

	public WithdrawFromStoreResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.WITHDRAW_FROM_STORE_RESPONSE, collaboratorKey);
		this.setSucceeded(isSucceeded);
		
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
