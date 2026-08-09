package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.v1.message.AppID;
import edu.greatfree.cluster.message.IntercastNotification;

public class RemoveFromSaleNotification extends IntercastNotification{

	private static final long serialVersionUID = -4400205704365197205L;

	
	private String productKey;
	private String storeName;

	public RemoveFromSaleNotification(String storeName, String productKey) {
		super(storeName, productKey,AppID.REMOVE_FROM_SALE_NOTIFICATION );
        this.storeName = storeName;
        this.productKey = productKey;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
