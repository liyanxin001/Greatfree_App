package com.greatfree.cluster.experiment.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.greatfree.cluster.experiment.data.StudentInfo;

public class AccountRegistry {
	
	public static AccountRegistry instance;
	
	private final Map<String, String> accounts = new ConcurrentHashMap<>();
	private final Map<String, StudentInfo> infos = new ConcurrentHashMap<>();
	
	public static AccountRegistry AR() {
        if (instance == null) {
        	
            instance = new AccountRegistry();
            return instance;
        }
        return instance;
    }

	public Map<String, String> getAccounts() {
		return accounts;
	}
	
	public void addAccount(String username, String password) {
		this.accounts.put(username, password);
	}
	
	public void getPassword(String username, String password) {
		this.accounts.get(username);
	}
	public Map<String, StudentInfo> getInfos() {
		return infos;
	}
	
	public void addInfo(String studentId, StudentInfo studentInfo) {
		this.infos.put(studentId, studentInfo);
	}
	
	public void addStudent(String username, String password, StudentInfo studentInfo) {
		this.accounts.put(username, password);
		this.infos.put(studentInfo.getStudentId(), studentInfo);
	}
	
	public StudentInfo getInfo(String studentId) {
		return this.infos.get(studentId);
		
	}
	
	public boolean login(String username, String password) {
		if(accounts.get(username) == password) {
			return true;
		}else {
			return false;
		}
	}

}
