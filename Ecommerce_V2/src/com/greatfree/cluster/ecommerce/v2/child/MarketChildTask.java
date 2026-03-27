package com.greatfree.cluster.ecommerce.v2.child;

import java.util.List;
import java.util.logging.Logger;

import com.greatfree.cluster.ecommerce.v2.message.AppID;

import edu.greatfree.cluster.child.ChildTask;
import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.ClusterResponse;
import edu.greatfree.cluster.message.InterChildrenNotification;
import edu.greatfree.cluster.message.InterChildrenRequest;
import edu.greatfree.cluster.message.IntercastNotification;
import edu.greatfree.cluster.message.IntercastRequest;
import edu.greatfree.multicast.message.MulticastResponse;

final class MarketChildTask extends ChildTask{
	
	private final static Logger log = Logger.getLogger("edu.greatfree.cluster.ecommerce.child");

	@Override
	public void processNotification(ClusterNotification paramClusterNotification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MulticastResponse processRequest(ClusterRequest request) {
		switch(request.getAppID())
		{
		    case AppID.CREATE_STORE_REQUEST:
		    	
		    	
		    	
		}
		return null;
	}

	@Override
	public InterChildrenNotification prepareNotification(IntercastNotification paramIntercastNotification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterChildrenRequest prepareRequest(IntercastRequest paramIntercastRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processNotification(InterChildrenNotification paramInterChildrenNotification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processNotification(InterChildrenNotification paramInterChildrenNotification, List<String> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MulticastResponse> processRequest(InterChildrenRequest paramInterChildrenRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MulticastResponse> processRequest(InterChildrenRequest paramInterChildrenRequest,
			List<String> paramList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processResponse(ClusterResponse paramClusterResponse) {
		// TODO Auto-generated method stub
		
	}

	public static Logger getLog() {
		return log;
	}

}
