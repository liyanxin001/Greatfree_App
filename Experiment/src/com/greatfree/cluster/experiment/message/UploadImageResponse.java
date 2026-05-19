package com.greatfree.cluster.experiment.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class UploadImageResponse extends MulticastResponse{

	private static final long serialVersionUID = -5862264004598909141L;

	public UploadImageResponse(int type, String collaboratorKey) {
		super(type, collaboratorKey);
		// TODO Auto-generated constructor stub
	}

}
