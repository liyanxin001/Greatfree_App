package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterRequest;


public class AddToCartRequest extends ClusterRequest{
	
	
	
	private static final long serialVersionUID = 5902250438819515287L;

	private String username;
	private String storeName;
	private String productName;
	private int quantity;
	

	public AddToCartRequest(String username, String storeName, String productName, int quantity) {
		super(username, AppID.ADD_TO_CART_REQUEST);
		this.username = username;
		this.storeName = storeName;
		this.setProductName(productName);
		this.setQuantity(quantity);
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
