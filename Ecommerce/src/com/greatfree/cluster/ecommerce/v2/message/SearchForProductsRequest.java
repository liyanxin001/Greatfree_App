package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class SearchForProductsRequest extends ClusterRequest{
	
	private static final long serialVersionUID = 6473133055057897780L;
	private String username;
	private String keyword;

	public SearchForProductsRequest(String username,String keyword) {
		super(RequestType.BROADCAST_REQUEST, AppID.SEARCH_FOR_PRODUCTS_REQUEST);
        this.setUserName(username);
        this.setKeyword(keyword);
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	

}
