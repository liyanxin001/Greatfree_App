package com.greatfree.cluster.experiment.message;

import com.greatfree.cluster.experiment.data.Procedure;

import edu.greatfree.cluster.message.ClusterRequest;

public class UploadProcedureRequest extends ClusterRequest{

	
	private static final long serialVersionUID = -4316782204310950794L;
	
	
	private String studentId;
    private Procedure procedure;
    
    
	public UploadProcedureRequest(String studentId, Procedure procedure) {
		super(studentId, AppID.UPLOAD_PROCEDURE_REQUEST);
		this.setStudentId(studentId);
		this.setProcedure(procedure);
	}


	public String getStudentId() {
		return studentId;
	}


	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


	public Procedure getProcedure() {
		return procedure;
	}


	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
}
