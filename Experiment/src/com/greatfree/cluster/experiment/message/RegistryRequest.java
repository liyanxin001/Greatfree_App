package com.greatfree.cluster.experiment.message;


import com.greatfree.cluster.experiment.data.StudentInfo;

import edu.greatfree.cluster.message.ClusterRequest;

public class RegistryRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = 7679786456746488160L;
	private String username;
	private String password;
    private StudentInfo studentInfo;
	

	public RegistryRequest(String username, String password, StudentInfo studentInfo) {
		super(studentInfo.getRealName(), AppID.REGISTRY_REQUEST);
		this.username = username;
		this.password = password;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public StudentInfo getStudentInfo() {
		return studentInfo;
	}


	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

}
