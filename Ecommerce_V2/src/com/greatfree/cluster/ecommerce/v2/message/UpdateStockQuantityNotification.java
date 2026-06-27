package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterNotification;


public class UpdateStockQuantityNotification extends ClusterNotification{
	
	
	
	private static final long serialVersionUID = 6249321730914973791L;
	
	private int newStockQuantity;
	private String storeName;
	private String productName;

	public UpdateStockQuantityNotification(int newStockQuantity, String storeName, String productName) {
		super(storeName, AppID.UPDATE_STOCK_QUANTITY_NOTIFICATION);
		this.newStockQuantity = newStockQuantity;
		this.productName = productName;
		this.storeName = storeName;
	}

	public int getNewStockQuantity() {
		return newStockQuantity;
	}

	public void setNewStockQuantity(int newStockQuantity) {
		this.newStockQuantity = newStockQuantity;
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
