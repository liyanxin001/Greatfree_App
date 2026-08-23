package com.greatfree.cluster.ecommerce.v3.message;


import com.greatfree.cluster.ecommerce.data.Product;

import edu.greatfree.cluster.message.IntercastRequest;

public class AddToCartRequest extends IntercastRequest{
	
	private static final long serialVersionUID = 2082783540379937014L;
	
	private int quantity;
	private Product product;
	private String userName;

	public AddToCartRequest(Product product, String userName, int quantity) {
		super(product.getKey(), userName, TRAppID.ADD_TO_CART_REQUEST);
		this.product = product;
		this.quantity = quantity;
		this.userName = userName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}




}
