package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.v1.message.AppID;
import edu.greatfree.cluster.message.IntercastNotification;


public class UpdateStockQuantityNotification extends IntercastNotification{
	
	
	
	private static final long serialVersionUID = 6249321730914973791L;
	
	private int newStockQuantity;
	private String storeName;
	private String productKey;

	public UpdateStockQuantityNotification(int newStockQuantity, String storeName, String productKey) {
		super(storeName,productKey, AppID.UPDATE_STOCK_QUANTITY_NOTIFICATION);
		this.newStockQuantity = newStockQuantity;
		this.productKey = productKey;
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
		return productKey;
	}

	public void setProductName(String productName) {
		this.productKey = productName;
	}

}
