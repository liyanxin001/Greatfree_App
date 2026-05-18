package com.greatfree.cluster.experiment.message;

import java.util.Map;

import com.greatfree.cluster.experiment.data.StudentInfo;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetStudentListResponse extends MulticastResponse{

	private static final long serialVersionUID = 3372977711356418356L;
	
	private Map<String, StudentInfo> studentList;

	public GetStudentListResponse(Map<String, StudentInfo> studentList, String collaboratorKey) {
		super(AppID.GET_STUDENTLIST_RESPONSE, collaboratorKey);
		this.studentList = studentList;
	}

	public Map<String, StudentInfo> getStudentList() {
		return studentList;
	}

	public void setStudentList(Map<String, StudentInfo> studentList) {
		this.studentList = studentList;
	}

}
