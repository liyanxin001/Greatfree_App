package com.greatfree.cluster.experiment.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetCommentResponse extends MulticastResponse{

	private static final long serialVersionUID = -7812988057441025566L;
	
	private String comment;

	public GetCommentResponse(String comment , String collaboratorKey) {
		super(AppID.GET_COMMENT_RESPONSE, collaboratorKey);
		this.setComment(comment);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
