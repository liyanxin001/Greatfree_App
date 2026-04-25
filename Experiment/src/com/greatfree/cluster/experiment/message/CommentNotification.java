package com.greatfree.cluster.experiment.message;

import edu.greatfree.multicast.message.MulticastNotification;

public class CommentNotification extends MulticastNotification{
	
	private static final long serialVersionUID = 1343617958137542014L;
	private String comment;

	public CommentNotification(int type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
