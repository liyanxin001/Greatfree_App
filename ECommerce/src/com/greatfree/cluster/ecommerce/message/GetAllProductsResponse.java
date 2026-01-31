package edu.greatfree.cluster.ecommerce.message;

import java.util.List;


import edu.greatfree.cluster.ecommerce.data.Product;
import edu.greatfree.multicast.message.MulticastResponse;

public class GetAllProductsResponse extends MulticastResponse{
	
	 
	private static final long serialVersionUID = 3344191193370364046L;
	private List<Product> allProducts;

	public GetAllProductsResponse(List<Product> allProducts , String collaboratorKey) {
		super(AppID.GET_ALL_PRODUCTS_RESPONSE, collaboratorKey);
		this.allProducts = allProducts;
	}

	public List<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(List<Product> allProducts) {
		this.allProducts = allProducts;
	}
     
}
