package com.greatfree.cluster.experiment.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CommentRepository {
	
	private static CommentRepository instance;
	
	private final Map<String, String> comments = new ConcurrentHashMap<>();
	
	public static CommentRepository CR() {
        if (instance == null) {
        	
            instance = new CommentRepository();
            return instance;
        }
        return instance;
    }

	public Map<String, String> getComments() {
		return comments;
	}
	
	public void addComment(String studentId, String comment) {
		this.comments.put(studentId, comment);
	}
    
	public String getComment(String studentId) {
		return this.comments.get(studentId);
	}
}
