package edu.greatfree.cluster.ecommerce.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class PayResponse extends MulticastResponse{
	

	private static final long serialVersionUID = -2336341999754421459L;
	
	private boolean isSucceeded;
	
	public PayResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.PAY_RESPONSE, collaboratorKey);
		this.isSucceeded = isSucceeded;

	}
	

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
