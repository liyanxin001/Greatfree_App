package com.greatfree.cluster.experiment.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class UploadProcedureResponse extends MulticastResponse{

	private static final long serialVersionUID = -8978578616963390512L;
	private boolean isSucceeded;

	public UploadProcedureResponse(boolean isSucceeded, String collaboratorKey) {
		super(AppID.UPLOAD_PROCEDURE_RESPONSE, collaboratorKey);
		this.setSucceeded(isSucceeded);
	}

	public boolean isSucceeded() {
		return isSucceeded;
	}

	public void setSucceeded(boolean isSucceeded) {
		this.isSucceeded = isSucceeded;
	}

}
