package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterNotification;

public class AddToStoreNotification extends ClusterNotification{
	
	
	private static final long serialVersionUID = 3104517154402159452L;
	private int quantity;
	private String storeName;

	public AddToStoreNotification(int quantity, String storeName) {
		super(storeName, AppID.ADD_TO_STORE_NOTIFICATION);
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

}
