package com.greatfree.cluster.experiment.app;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.greatfree.cluster.experiment.data.StudentInfo;

public class StudentInfoRepository{ 
	
	private static StudentInfoRepository instance;
	
	
	private final Map<String, StudentInfo> infos = new ConcurrentHashMap<>();

    public static StudentInfoRepository SIP() {
        if (instance == null) {
        	
            instance = new StudentInfoRepository();
            return instance;
        }
        return instance;
    }
	
	public Map<String, StudentInfo> getInfos() {
		return infos;
	}
	
	public void addInfo(String studentId, StudentInfo studentInfo) {
		this.infos.put(studentId, studentInfo);
	}
	
	public StudentInfo getInfo(String studentId) {
		return this.infos.get(studentId);
		
	}

}
