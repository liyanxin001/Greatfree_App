package com.greatfree.cluster.ecommerce.v3.message;

import java.util.List;

import edu.greatfree.cluster.message.IntercastRequest;
import edu.greatfree.cluster.message.RequestType;

public class GetProductsRequest extends IntercastRequest{

	private static final long serialVersionUID = 8108715486388037081L;
	
	private List<String> productKeys;

	public GetProductsRequest(String randomSource, List<String> productKeys) {
		super(RequestType.BROADCAST_REQUEST, randomSource, productKeys, TRAppID.GET_PRODUCTS_REQUEST);
		this.productKeys = productKeys;
	}

	public List<String> getProductKeys() {
		return productKeys;
	}

	public void setProductKeys(List<String> productKeys) {
		this.productKeys = productKeys;
	}

}
