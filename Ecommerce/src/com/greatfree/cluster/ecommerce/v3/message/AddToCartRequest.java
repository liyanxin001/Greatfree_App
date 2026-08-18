package com.greatfree.cluster.ecommerce.v3.message;


import edu.greatfree.cluster.message.IntercastRequest;

public class AddToCartRequest extends IntercastRequest{
	
	private static final long serialVersionUID = 2082783540379937014L;
	
	private int quantity;
	private String productKey;
	private String userName;

	public AddToCartRequest(String productKey, String userName, int quantity) {
		super(productKey, userName, TRAppID.ADD_TO_CART_REQUEST);
		this.productKey = productKey;
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

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

}
