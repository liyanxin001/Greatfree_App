package com.greatfree.cluster.ecommerce_V2.message;

import java.util.List;

import com.greatfree.cluster.ecommerce_V2.data.product;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetHotProductsResponse extends MulticastResponse{
	
	private static final long serialVersionUID = -963706149616712823L;
	private List<product> products;
	
	public GetHotProductsResponse(List<product> products, String collaboratorKey) {
		super(AppID.GET_HOT_PRODUCTS_RESPONSE, collaboratorKey);
		this.setProducts(products);

	}

	public List<product> getProducts() {
		return products;
	}

	public void setProducts(List<product> products) {
		this.products = products;
	}

	
	
	
	

}
