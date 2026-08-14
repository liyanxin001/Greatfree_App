package com.greatfree.cluster.ecommerce.v3.message;

import java.util.Date;
import edu.greatfree.cluster.message.ClusterRequest;

public class LatestOrdersRequest extends ClusterRequest{

	private static final long serialVersionUID = -2083300875372030943L;
	
	private String userName;
	private String storeName;
	private Date timeStamp;

	public LatestOrdersRequest(String userName, String storeName, Date timeStamp) {
		super(storeName, TRAppID.LATEST_ORDERS_REQUEST);
		this.userName = userName;
		this.storeName = storeName;
		this.timeStamp = timeStamp;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
