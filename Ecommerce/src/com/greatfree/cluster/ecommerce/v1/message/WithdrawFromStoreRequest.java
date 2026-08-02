package com.greatfree.cluster.ecommerce.v1.message;

import edu.greatfree.cluster.message.ClusterRequest;

public class WithdrawFromStoreRequest extends ClusterRequest{
	
	private static final long serialVersionUID = 2082783540379937014L;
	
	private int quantity;
	private String storeName;
	private String productName;

	public WithdrawFromStoreRequest(int quantity, String storeName, String productName) {
		super(null, AppID.WITHDRAW_FROM_STORE_REQUEST);
		this.productName = productName;
		this.storeName = storeName;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

}
