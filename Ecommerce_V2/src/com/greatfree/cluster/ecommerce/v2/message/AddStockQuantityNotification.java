package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterNotification;

public class AddStockQuantityNotification extends ClusterNotification{
	
	
	private static final long serialVersionUID = 3104517154402159452L;
	private int quantity;
	private String storeName;
	private String productName;

	public AddStockQuantityNotification(int quantity, String storeName, String productName) {
		super(storeName, AppID.ADD_STOCK_QUANTITY_NOTIFICATION);
		this.quantity = quantity;
		this.storeName = storeName;
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
