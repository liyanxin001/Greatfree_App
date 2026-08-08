package com.greatfree.cluster.ecommerce.v3.message;



import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.cluster.message.IntercastNotification;

public class RemoveFromCartNotification extends IntercastNotification{

	private static final long serialVersionUID = 7483239669723031050L;
	
	private String userName;
    private String productKey;
    
    
    public RemoveFromCartNotification(String userName, String productKey) {
		super(userName, productKey, AppID.REMOVE_FROM_CART_NOTIFICATION);
		this.userName = userName;
		this.setProductKey(productKey);
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getProductKey() {
		return productKey;
	}


	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}





}