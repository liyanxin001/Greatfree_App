package com.greatfree.cluster.experiment.message;

import java.util.Map;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetStudentListResponse extends MulticastResponse{

	private static final long serialVersionUID = 3372977711356418356L;
	
	private Map<String, String> studentList;

	public GetStudentListResponse(Map<String, String> studentList, String collaboratorKey) {
		super(AppID.GET_STUDENTLIST_RESPONSE, collaboratorKey);
		this.studentList = studentList;
	}

	public Map<String, String> getStudentList() {
		return studentList;
	}

	public void setStudentList(Map<String, String> studentList) {
		this.studentList = studentList;
	}

}
