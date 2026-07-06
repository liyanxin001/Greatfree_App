package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class GetCartRequest extends ClusterRequest{
	
	
	private String username;
	

	public GetCartRequest(int appID) {
		super(appID);
		// TODO Auto-generated constructor stub
	}

}
