package com.greatfree.cluster.experiment.message;

import java.util.Map;

import com.greatfree.cluster.experiment.data.Procedure;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetProcedureListResponse extends MulticastResponse{

	private static final long serialVersionUID = 8646501169237898069L;
	
	private Map<String, Procedure> procedureList;

	public GetProcedureListResponse(Map<String, Procedure> procedureList, String collaboratorKey) {
		super(AppID.GET_COMMENT_RESPONSE, collaboratorKey);
		this.procedureList = procedureList;
	}
	public Map<String, Procedure> getProcedureList() {
		return procedureList;
	}
	public void setProcedureList(Map<String, Procedure> procedureList) {
		this.procedureList = procedureList;
	}
}
