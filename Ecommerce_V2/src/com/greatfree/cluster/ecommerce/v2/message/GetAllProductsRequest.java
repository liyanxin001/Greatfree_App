package com.greatfree.cluster.ecommerce.v2.message;



import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class GetAllProductsRequest extends ClusterRequest{

	private static final long serialVersionUID = 8015080993487807672L;
	
	private String userName;

	public GetAllProductsRequest(String userName) {
		super(RequestType.UNICAST_REQUEST, AppID.GET_ALL_PRODUCTS_REQUEST);
		this.setUserName(userName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
