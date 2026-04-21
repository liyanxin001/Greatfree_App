package com.greatfree.cluster.experiment.message;

import com.greatfree.cluster.experiment.data.Gender;

import edu.greatfree.cluster.message.ClusterRequest;

public class RegistryRequest extends ClusterRequest{
	
	
	private static final long serialVersionUID = 7679786456746488160L;
	private String username;
	private String password;
	private boolean isInSchool;
	private String major;
	private String className;
	private String realName;
	private int age;
	private Gender gender;
	private String phoneNumber;

	public RegistryRequest(String username, String password, boolean isInSchool, String major, String className, String realName, int age, Gender gender, String phoneNumber) {
		super(username, AppID.REGISTRY_REQUEST);
		this.username = username;
		this.password = password;
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
	    return "RegistryRequest{" +
	           "username='" + username + '\'' +
	           ", isInSchool=" + isInSchool +
	           ", major='" + major + '\'' +
	           ", className='" + className + '\'' +
	           ", realName='" + realName + '\'' +
	           ", age=" + age +
	           ", gender=" + gender +
	           ", phoneNumber='" + phoneNumber + '\'' +
	           '}';
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
