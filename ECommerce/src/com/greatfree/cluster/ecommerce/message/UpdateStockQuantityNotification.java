package com.greatfree.cluster.ecommerce.message;

import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.NotificationType;

public class UpdateStockQuantityNotification extends ClusterNotification{

	private static final long serialVersionUID = -5122134950040193324L;
	
	private String productName;
	private int NewStockQuantity;
	private String storeName;

	public UpdateStockQuantityNotification(String productName, String storeName, int NewStockQuantity) {
		super(NotificationType.BROADCAST_NOTIFICATION, AppID.UPDATE_STOCK_QUANTITY_NOTIFICATION);
		this.productName = productName;
		this.NewStockQuantity = NewStockQuantity;
		this.storeName = storeName;
		
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNewStockQuantity() {
		return NewStockQuantity;
	}

	public void setNewStockQuantity(int newStockQuantity) {
		NewStockQuantity = newStockQuantity;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
