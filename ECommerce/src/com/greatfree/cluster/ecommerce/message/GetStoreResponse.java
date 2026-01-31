package com.greatfree.cluster.ecommerce.message;

import com.greatfree.cluster.ecommerce.data.Store;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetStoreResponse extends MulticastResponse{
	
	
	private static final long serialVersionUID = 2643775483987591008L;
	
	private Store store;

	public GetStoreResponse(Store store, String collaboratorKey) {
		super(AppID.GET_STORE_RESPONSE, collaboratorKey);
		this.setStore(store);
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
