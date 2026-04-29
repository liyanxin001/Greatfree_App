package com.greatfree.cluster.experiment.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class GetStudentListRequest extends ClusterRequest{

	private static final long serialVersionUID = 8232633662959120739L;
	

	public GetStudentListRequest() {
		super(RequestType.BROADCAST_REQUEST, AppID.GET_STUDENTLIST_REQUEST);
		
	}

}
