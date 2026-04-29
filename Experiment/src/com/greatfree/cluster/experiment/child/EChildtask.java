package com.greatfree.cluster.experiment.child;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import com.greatfree.cluster.experiment.app.CommentRepository;
import com.greatfree.cluster.experiment.message.AppID;
import com.greatfree.cluster.experiment.message.CommentNotification;
import com.greatfree.cluster.experiment.message.GetCommentRequest;
import com.greatfree.cluster.experiment.message.GetCommentResponse;

import edu.greatfree.cluster.child.ChildTask;
import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.ClusterResponse;
import edu.greatfree.cluster.message.InterChildrenNotification;
import edu.greatfree.cluster.message.InterChildrenRequest;
import edu.greatfree.cluster.message.IntercastNotification;
import edu.greatfree.cluster.message.IntercastRequest;
import edu.greatfree.multicast.message.MulticastResponse;

public class EChildtask extends ChildTask{
	
	private final static Logger log = Logger.getLogger("com.greatfree.cluster.experiment.child");

	@Override
	public void processNotification(ClusterNotification notification) {
		switch (notification.getAppID()) 
		{
		    case AppID.COMMENT_NOTIFICATION:
		    	log.info("COMMENT_NOTIFICATION received @" + Calendar.getInstance().getTime());
			    CommentNotification cn = (CommentNotification) notification;
			    CommentRepository.CR().addComment(cn.getStudentId(), cn.getComment());
			break;
		}
		
	}

	@Override
	public MulticastResponse processRequest(ClusterRequest request) {
		switch (request.getAppID()) 
		{
		    case AppID.COMMENT_NOTIFICATION:
		    	log.info("COMMENT_NOTIFICATION received @" + Calendar.getInstance().getTime());
			    GetCommentRequest gcr = (GetCommentRequest) request;
			    return new GetCommentResponse(CommentRepository.CR().getComment(gcr.getStudentId()), gcr.getCollaboratorKey());
			
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

}
