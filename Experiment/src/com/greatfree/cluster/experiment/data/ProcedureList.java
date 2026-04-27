package com.greatfree.cluster.experiment.data;

import java.io.Serializable;
import java.util.Map;

public class ProcedureList implements Serializable {
	
	private static final long serialVersionUID = 8251858305786259253L;
	private Map<String, Procedure> procedures;
	
	
	
	public Map<String, Procedure> getProcedures() {
		return procedures;
	}
	public void setProcedures(Map<String, Procedure> procedures) {
		this.procedures = procedures;
	}
	
	public void addProcedure(String id, Procedure procedure) {
		this.procedures.put(id, procedure);
	}
	
	

}
