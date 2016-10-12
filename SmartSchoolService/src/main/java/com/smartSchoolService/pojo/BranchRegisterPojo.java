package com.smartSchoolService.pojo;

public class BranchRegisterPojo {

	private Long key;
	private String branchName;
	private String branchAddress;
	private String createdByUserName;
	private String lastUpdatedByUserName;
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public String getLastUpdatedByUserName() {
		return lastUpdatedByUserName;
	}
	public void setLastUpdatedByUserName(String lastUpdatedByUserName) {
		this.lastUpdatedByUserName = lastUpdatedByUserName;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	
	
}
