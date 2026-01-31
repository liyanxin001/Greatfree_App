package com.greatfree.cluster.ecommerce.message;


import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.NotificationType;

public class RemoveFromSaleNotification extends ClusterNotification {
	
	
	private static final long serialVersionUID = -4615489201370859321L;

	
	private String productName;
	private String storeName;
    
	

	public RemoveFromSaleNotification( String productName, String storeName) {
		super(NotificationType.BROADCAST_NOTIFICATION, AppID.REMOVE_FROM_SALE_NOTIFICATION);
		this.storeName = storeName;
		this.productName = productName;
		
	}

	public String getProductName() {
		return productName;
	}



	public void setProductName(String ProductName) {
		this.productName = ProductName;
	}




	public String getStoreName() {
		return storeName;
	}




	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


}




