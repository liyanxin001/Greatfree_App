package com.greatfree.cluster.experiment.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.greatfree.cluster.experiment.data.StudentInfo;

public class AccountRegistry {
	
	public static AccountRegistry instance;
	
	private final Map<String, String> accounts = new ConcurrentHashMap<>();
	private final Map<String, StudentInfo> infos = new ConcurrentHashMap<>();
	private final Map<String, String> imageUrls = new ConcurrentHashMap<>();
	
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
	
	public Map<String, StudentInfo> getInfos() {
		return infos;
	}
	
	public StudentInfo getInfo(String studentId) {
		return this.infos.get(studentId);
		
	}
	public void addInfo(String studentId, StudentInfo studentInfo) {
		this.infos.put(studentId, studentInfo);
	}
	
	public void addStudent(String username, String password, StudentInfo studentInfo) {
		this.accounts.put(username, password);
		this.infos.put(studentInfo.getStudentId(), studentInfo);
	}
	
	
	
	public boolean login(String username, String password) {
		if(accounts.get(username) == password) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean register(String username, String password, StudentInfo studentInfo) {
		if(accounts.containsKey(username)) {
			return false;
		}else {
			this.addStudent(username, password, studentInfo);
			return true;
		}
		
	}

	public Map<String, String> getImageUrls() {
		return imageUrls;
	}
	
	public boolean addImageUrl(String studentId, String imgUrl) {
		this.imageUrls.put(studentId, imgUrl);
		return true;
	}

}
