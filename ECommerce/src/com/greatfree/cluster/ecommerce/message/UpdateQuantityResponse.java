package edu.greatfree.cluster.ecommerce.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class UpdateQuantityResponse extends MulticastResponse{
	
	private static final long serialVersionUID = -1130781741710652497L;

	private boolean isSucceeded;
	
	public UpdateQuantityResponse(boolean isSucceeded,String collaboratorKey) {
		super(AppID.UPDATE_QUANTITY_RESPONSE, collaboratorKey);
		this.setSucceeded(isSucceeded);
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

	
	
	

}
