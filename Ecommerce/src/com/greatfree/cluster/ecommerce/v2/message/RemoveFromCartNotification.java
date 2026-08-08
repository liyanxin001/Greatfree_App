package com.greatfree.cluster.ecommerce.v2.message;



import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.cluster.message.IntercastNotification;

public class RemoveFromCartNotification extends IntercastNotification{

	private static final long serialVersionUID = 7483239669723031050L;
	
	private String userName;
    private String storeName;
    private String productName;
    
    
    public RemoveFromCartNotification(String userName, String storeName, String productName) {
		super(userName, productName, AppID.REMOVE_FROM_CART_NOTIFICATION);
		this.userName = userName;
		this.storeName = storeName;
		this.productName = productName;
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


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


}