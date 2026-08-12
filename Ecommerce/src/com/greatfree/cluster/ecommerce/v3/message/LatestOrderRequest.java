package com.greatfree.cluster.ecommerce.v3.message;

import java.util.Date;
import edu.greatfree.cluster.message.ClusterRequest;

public class LatestOrderRequest extends ClusterRequest{

	private static final long serialVersionUID = -2083300875372030943L;
	
	private String storeName;
	
	private Date timeStamp;

	public LatestOrderRequest(String storeName, Date timeStamp) {
		super(storeName, TRAppID.LATEST_ORDER_REQUEST);
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

}
