package com.greatfree.cluster.ecommerce.v2.message;

import com.greatfree.cluster.ecommerce.data.Product;

import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.NotificationType;

public class PutOnSaleNotification extends ClusterNotification {
	
	private static final long serialVersionUID = 5972347217293143974L;
	
	
	private Product product;
    
	

	public PutOnSaleNotification(Product product) {
		super(NotificationType.BROADCAST_NOTIFICATION, AppID.PUT_ON_SALE_NOTIFICATION);
		this.product = product;
		
	}


	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}

}
