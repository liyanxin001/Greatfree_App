package com.greatfree.cluster.ecommerce.v2.message;

import com.greatfree.cluster.ecommerce.data.CartItem;

import edu.greatfree.cluster.message.ClusterNotification;



public class AddToCartNotification extends ClusterNotification{
	
	
	
	private static final long serialVersionUID = 5902250438819515287L;

	private CartItem item;
	private String userName;
	private String productName;
	

	public AddToCartNotification(CartItem item, String userName, String productName) {
		super(userName, AppID.ADD_TO_CART_NOTIFICATION);
		this.item = item;
		this.userName = userName;
		this.productName = productName;

	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String username) {
		this.userName = username;
	}


	public CartItem getItem() {
		return item;
	}


	public void setItem(CartItem item) {
		this.item = item;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}



}
