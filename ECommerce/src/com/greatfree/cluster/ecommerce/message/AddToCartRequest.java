package com.greatfree.cluster.ecommerce.message;


import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;


public class AddToCartRequest extends ClusterRequest {

	private static final long serialVersionUID = 6802097259899340393L;
	
	private String productName;
	private int quantity;
	private String userName;
	private String storeName;
	

	public AddToCartRequest(String productName, String storeName, int quantity, String userName) {
		super(RequestType.BROADCAST_REQUEST,AppID.ADD_TO_CART_REQUEST);
		this.productName = productName;
		this.quantity = quantity;
		this.userName = userName;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getStoreName() {
		return this.storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}

