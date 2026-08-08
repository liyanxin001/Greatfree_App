package com.greatfree.cluster.ecommerce.v2.message;

import java.util.List;

import com.greatfree.cluster.ecommerce.data.Product;
import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.multicast.message.MulticastResponse;

public class SearchForProductsResponse extends MulticastResponse{
	
	private static final long serialVersionUID = -963706149616712823L;
	private List<Product> products;
	
	public SearchForProductsResponse(List<Product> products, String collaboratorKey) {
		super(AppID.SEARCH_FOR_PRODUCTS_RESPONSE, collaboratorKey);
		this.products = products;

	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
	
	

}
