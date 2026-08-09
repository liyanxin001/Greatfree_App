package com.greatfree.cluster.ecommerce.v3.message;



import edu.greatfree.cluster.message.ClusterRequest;


public class SearchForProductKeysRequest extends ClusterRequest{

	private static final long serialVersionUID = -814483568691007001L;
	
	private String keyword;

	public SearchForProductKeysRequest(String randomSource, String keyword) {
		super(randomSource, TRAppID.SEARCH_FOR_PRODUCTS_KEYS_REQUEST);
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
