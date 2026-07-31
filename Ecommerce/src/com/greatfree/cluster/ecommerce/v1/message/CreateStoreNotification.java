package com.greatfree.cluster.ecommerce.v1.message;

import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.NotificationType;


public class CreateStoreNotification extends ClusterNotification {
	
	
	private static final long serialVersionUID = -6335641991934876395L;

	private String userName;
	private String storeName;
	
	public CreateStoreNotification(String userName, String storeName) {
		super(NotificationType.BROADCAST_NOTIFICATION, AppID.CREATE_STORE_NOTIFICATION);
		this.setUserName(userName);
		this.setStoreName(storeName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
