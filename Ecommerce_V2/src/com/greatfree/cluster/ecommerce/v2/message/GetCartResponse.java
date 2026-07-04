package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetCartResponse extends MulticastResponse{

	private static final long serialVersionUID = 2374598869607715710L;

	public GetCartResponse(int type, String collaboratorKey) {
		super(type, collaboratorKey);
		// TODO Auto-generated constructor stub
	}

}
