package com.greatfree.cluster.experiment.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class UploadImageRequest extends ClusterRequest{

	private static final long serialVersionUID = 2228391224343493926L;
	
	private String studentId;
	private String imgUrl;

	public UploadImageRequest(int requestType, int appID) {
		super(requestType, appID);
		// TODO Auto-generated constructor stub
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
