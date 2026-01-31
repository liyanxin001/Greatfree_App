package edu.greatfree.cluster.ecommerce.message;

import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.RequestType;

public class PayRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = -1929149239066394920L;
	
	private String userName;

	public PayRequest(String userName) {
		super(RequestType.BROADCAST_REQUEST, AppID.PAY_REQUEST);
		this.setUserName(userName);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
