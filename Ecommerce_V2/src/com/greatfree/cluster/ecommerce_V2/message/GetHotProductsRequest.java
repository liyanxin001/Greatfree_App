package com.greatfree.cluster.ecommerce_V2.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class GetHotProductsRequest extends ClusterRequest{
	
	private static final long serialVersionUID = 6473133055057897780L;
	private String userName;

	public GetHotProductsRequest(String userName) {
		super(RequestType.BROADCAST_REQUEST);
        this.setUserName(userName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
