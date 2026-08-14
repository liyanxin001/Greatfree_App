package com.greatfree.cluster.ecommerce.v3.message;

import java.util.List;


import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetStoreResponse extends MulticastResponse{
	
	
	private static final long serialVersionUID = 2643775483987591008L;
	
	private boolean isOwner;
	private List<String> productKeys;

	public GetStoreResponse(boolean isOwner,List<String> productKeys, String collaboratorKey) {
		super(AppID.GET_STORE_RESPONSE, collaboratorKey);
		this.isOwner = isOwner;
		this.productKeys = productKeys;
	}

	public List<String> getProductKeys() {
		return productKeys;
	}

	public void setProductKeys(List<String> productKeys) {
		this.productKeys = productKeys;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}



}
