package com.greatfree.cluster.ecommerce.v3.message;

import java.util.List;

import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.multicast.message.MulticastResponse;

public class SearchForProductKeysResponse extends MulticastResponse{
	
	private static final long serialVersionUID = 8164720014059462909L;
	private List<String> productKeys;
	

	public SearchForProductKeysResponse(List<String> productKeys,  String collaboratorKey) {
		super(AppID.SEARCH_FOR_PRODUCTS_RESPONSE, collaboratorKey);
		this.productKeys = productKeys;
	}

	public List<String> getProductKeys() {
		return productKeys;
	}

	public void setProductKeys(List<String> productKeys) {
		this.productKeys = productKeys;
	}

}
