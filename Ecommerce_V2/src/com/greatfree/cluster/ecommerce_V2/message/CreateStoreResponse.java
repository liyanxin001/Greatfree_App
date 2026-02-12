package com.greatfree.cluster.ecommerce_V2.message;



import edu.greatfree.multicast.message.MulticastResponse;

public class CreateStoreResponse extends MulticastResponse{
	private static final long serialVersionUID = 3166620997871078566L;
	
	private boolean isSucceeded;

	public CreateStoreResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.CREATE_STORE_REQUEST, collaboratorKey);
		this.setSucceeded(isSucceeded);
		
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
