package com.greatfree.cluster.ecommerce.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class GetStoreRequest extends ClusterRequest{
	
	
	
	private static final long serialVersionUID = -8750413280374412693L;

	private String storeName;

	public GetStoreRequest(String storeName) {
		super(RequestType.UNICAST_REQUEST, AppID.GET_CART_REQUEST);
		this.storeName = storeName;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
