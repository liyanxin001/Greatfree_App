package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.NotificationType;

public class RemoveProductRegistryNotification extends ClusterNotification{

	private static final long serialVersionUID = -2894701142001491785L;
	
	private String productKey;

	public RemoveProductRegistryNotification(String productKey) {
		super(NotificationType.BROADCAST_NOTIFICATION, TRAppID.REMOVE_PRODUCT_REGISTRY_NOTIFICATION);
		this.productKey = productKey;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

}
