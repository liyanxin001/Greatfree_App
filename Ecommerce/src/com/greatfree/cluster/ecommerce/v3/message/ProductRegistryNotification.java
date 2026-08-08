package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.RequestType;

public class ProductRegistryNotification extends ClusterNotification{

	private static final long serialVersionUID = -2740757715442812L;
	
	private String productKey;
	private String productName;

	public ProductRegistryNotification(String productKey, String productName) {
		super(RequestType.BROADCAST_REQUEST, TRAppID.PRODUCT_REGISTRY_NOTIFICATION);
		this.productKey = productName;
		this.productName = productName;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
