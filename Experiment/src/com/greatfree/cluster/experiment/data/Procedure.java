package com.greatfree.cluster.experiment.data;

import java.io.Serializable;

public class Procedure implements Serializable{

	private static final long serialVersionUID = -6283802974180322948L;
	
	private String id;
	private String procedureName;
	private boolean isCorrect;
	private int score;
	
	
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
