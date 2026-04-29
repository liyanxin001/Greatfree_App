package com.greatfree.cluster.experiment.message;

import edu.greatfree.cluster.message.ClusterNotification;


public class CommentNotification extends ClusterNotification{
	
	private static final long serialVersionUID = 1343617958137542014L;
	private String comment;
	private String studentId;

	public CommentNotification(String comment, String studentId) {
		super(studentId, AppID.COMMENT_NOTIFICATION);
		this.comment = comment;
	    this.studentId = studentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
