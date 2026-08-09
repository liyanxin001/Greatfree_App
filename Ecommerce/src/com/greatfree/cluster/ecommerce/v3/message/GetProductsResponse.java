package com.greatfree.cluster.ecommerce.v3.message;



import java.util.Map;

import com.greatfree.cluster.ecommerce.data.Product;
import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetProductsResponse extends MulticastResponse{

	private static final long serialVersionUID = -1691820674318156569L;
	
	private Map<Integer, Product>  products;

	public GetProductsResponse(Map<Integer, Product> products, String collaboratorKey) {
		super(AppID.GET_ALL_PRODUCTS_RESPONSE, collaboratorKey);
		this.products = products;
	}	
		
	public Map<Integer, Product> getProducts() {
		return products;
	}

	public void setProducts(Map<Integer, Product> products) {
		this.products = products;
	}

}
