package com.greatfree.cluster.experiment.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class GetProcedureListRequest extends ClusterRequest{

	private static final long serialVersionUID = -7800848569372306571L;

	private String studentId;
	
	public GetProcedureListRequest(String studentId, int appID) {
		super(studentId, AppID.GET_PROCEDURELIST_REQUEST);
		this.setStudentId(studentId);
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
