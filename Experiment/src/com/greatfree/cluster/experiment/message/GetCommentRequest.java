package com.greatfree.cluster.experiment.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class GetCommentRequest extends ClusterRequest{

	private static final long serialVersionUID = -5960214970259149988L;
	private String studentId;

	public GetCommentRequest(String studentId) {
		super(RequestType.BROADCAST_REQUEST, AppID.GET_COMMENT_REQUEST);
		this.studentId = studentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
