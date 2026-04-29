package com.greatfree.cluster.experiment.data;

import java.io.Serializable;

public class StudentInfo implements Serializable{
	
	private static final long serialVersionUID = -4255686103524614332L;
	
	private String realName;
	private String studentId;
	private boolean isInSchool;
	private String major;
	private String className;
	private int age;
	private Gender gender;
	private String phoneNumber;
	

	
	public StudentInfo(String realName, String studentId, boolean isInSchool, String major, String className, int age, Gender gender, String phoneNumber) {
		this.studentId = studentId;
		this.isInSchool = isInSchool;
		this.major = major;
		this.className = className;
		this.realName = realName;
		this.age = age;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
	    return "StudentInfo{" +
	           ", realName=" + realName +
	           ", studentId=" + studentId +
	           ", isInSchool=" + isInSchool +
	           ", major='" + major + '\'' +
	           ", className='" + className + '\'' +
	           ", realName='" + realName + '\'' +
	           ", age=" + age +
	           ", gender=" + gender +
	           ", phoneNumber='" + phoneNumber + '\'' +
	           '}';
	}

	public boolean isInSchool() {
		return isInSchool;
	}

	public void setInSchool(boolean isInSchool) {
		this.isInSchool = isInSchool;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
