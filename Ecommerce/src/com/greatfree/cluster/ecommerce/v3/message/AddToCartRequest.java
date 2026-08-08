package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.cluster.message.IntercastRequest;

public class AddToCartRequest extends IntercastRequest{
	
	private static final long serialVersionUID = 2082783540379937014L;
	
	private int quantity;
	private String productKey;
	private String userName;

	public AddToCartRequest(String productKey, String userName, int quantity, String storeName) {
		super(productKey, userName, AppID.WITHDRAW_FROM_STORE_REQUEST);
		this.productKey = productKey;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
