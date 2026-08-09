package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.data.Product;
import com.greatfree.cluster.ecommerce.v1.message.AppID;


import edu.greatfree.cluster.message.IntercastNotification;

public class PutOnSaleNotification extends IntercastNotification {

	private static final long serialVersionUID = 6746523863552626659L;
	
	private Product product;
	

	public PutOnSaleNotification(Product product) {
		super(product.getStoreName(), product.getKey(), AppID.PUT_ON_SALE_NOTIFICATION);
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
