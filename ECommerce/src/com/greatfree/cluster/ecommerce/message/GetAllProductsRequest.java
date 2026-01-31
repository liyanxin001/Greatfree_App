package com.greatfree.cluster.ecommerce.message;



import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class GetAllProductsRequest extends ClusterRequest{

	private static final long serialVersionUID = 8015080993487807672L;
	
	String userName;

	public GetAllProductsRequest(String userName) {
		super(RequestType.UNICAST_REQUEST, AppID.GET_ALL_PRODUCTS_RESPONSE);
		this.userName = userName;
	}
	
	

}
