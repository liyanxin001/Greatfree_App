package com.greatfree.cluster.experiment.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class RegsitryResponse extends MulticastResponse{

	private static final long serialVersionUID = -6591345225537034585L;
	
	private boolean isSucceeded;

	public RegsitryResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.REGISTRY_RESPONSE, collaboratorKey);
		this.isSucceeded = isSucceeded;
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
