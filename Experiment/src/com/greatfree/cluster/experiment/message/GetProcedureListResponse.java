package com.greatfree.cluster.experiment.message;




import com.greatfree.cluster.experiment.data.ProcedureList;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetProcedureListResponse extends MulticastResponse{

	private static final long serialVersionUID = 8646501169237898069L;
	
	private ProcedureList procedureList;

	public GetProcedureListResponse(ProcedureList procedureList, String collaboratorKey) {
		super(AppID.GET_COMMENT_RESPONSE, collaboratorKey);
		this.procedureList = procedureList;
	}
	public ProcedureList getProcedureList() {
		return procedureList;
	}
	public void setProcedureList(ProcedureList procedureList) {
		this.procedureList = procedureList;
	}
}
