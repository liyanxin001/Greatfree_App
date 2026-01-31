package edu.greatfree.cluster.ecommerce.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class UpdateQuantityRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = 4173061299504557418L;

	private String userName;
	private String productName;
	private int newQuantity;

	public UpdateQuantityRequest(String userName, String productName, int quantity) {
		super(RequestType.BROADCAST_REQUEST, AppID.UPDATE_QUANTITY_REQUEST);
		this.setuserName(userName);
		this.setProductName(productName);
		this.setQuantity(quantity);
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return newQuantity;
	}

	public void setQuantity(int quantity) {
		this.newQuantity = quantity;
	}
	
	

}
