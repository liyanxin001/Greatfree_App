package com.greatfree.cluster.ecommerce.message;

import com.greatfree.cluster.ecommerce.data.Product;

import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.NotificationType;

public class PutOnSaleNotification extends ClusterNotification {
	
	private static final long serialVersionUID = 5972347217293143974L;
	private String storeName;
	
	private Product product;
    
	

	public PutOnSaleNotification(Product product, String storeName) {
		super(NotificationType.BROADCAST_NOTIFICATION, AppID.PUT_ON_SALE_NOTIFICATION);
	
		this.product = product;
		this.storeName = storeName;
	}











	public String getStoreName() {
		return storeName;
	}



	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}

}
