package com.greatfree.cluster.experiment.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.greatfree.cluster.experiment.data.Procedure;
import com.greatfree.cluster.experiment.data.ProcedureList;

public class ProcedureRepository {
	
	private static ProcedureRepository instance;
	
	private final Map<String, ProcedureList> procedureLists = new ConcurrentHashMap<>();
	
    public static ProcedureRepository PR() {
        if (instance == null) {
        	
            instance = new ProcedureRepository();
            return instance;
        }
        return instance;
    }

	public Map<String, ProcedureList> getProcedureList() {
		return procedureLists;
	}
	
	public void addProcedureList(String realName, ProcedureList procedureList) {
		this.procedureLists.put(realName, procedureList);
	}
	
	public void addProcedure(String realName, Procedure procedure) {
		this.procedureLists.get(realName).addProcedure(procedure.getId(), procedure);
	}

}
