package com.greatfree.cluster.experiment.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class UploadImageResponse extends MulticastResponse{

	private static final long serialVersionUID = -5862264004598909141L;
	
	private boolean isSuccessful;

	public UploadImageResponse(boolean isSuccessful, String collaboratorKey) {
		super(AppID.UPLOAD_IMAGE_RESPONSE, collaboratorKey);
		this.isSuccessful = isSuccessful;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

}
