package com.greatfree.cluster.ecommerce.v2.message;



import edu.greatfree.cluster.message.IntercastNotification;

public class RemoveFromCartNotification extends IntercastNotification{

	private static final long serialVersionUID = 7483239669723031050L;
	
	private String username;
    private String storeName;
    private String productName;
    
    
    public RemoveFromCartNotification(String username, String storeName, String productName) {
		super(username, storeName, AppID.REMOVE_FROM_CART_NOTIFICATION);
		this.username = username;
		this.storeName = storeName;
		this.productName = productName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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