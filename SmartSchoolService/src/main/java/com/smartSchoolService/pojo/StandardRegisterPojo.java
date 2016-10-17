package com.smartSchoolService.pojo;

import java.util.List;

import com.smartSchoolService.util.ChoiceListPojo;

public class StandardRegisterPojo implements Cloneable{
	
	private Long key;
	private String standardName;
	private String standardDesc;
	private Long branchId;
	private String createdByUserName;
	private String lastUpdatedByUserName;
	
	//Below columns are being used for rendering sections datatable
	private String branchName;
	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	
	
	public String getLastUpdatedByUserName() {
		return lastUpdatedByUserName;
	}
	public void setLastUpdatedByUserName(String lastUpdatedByUserName) {
		this.lastUpdatedByUserName = lastUpdatedByUserName;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getStandardDesc() {
		return standardDesc;
	}
	public void setStandardDesc(String standardDesc) {
		this.standardDesc = standardDesc;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranches() {
		return availableBranches;
	}
	public void setAvailableBranches(List<ChoiceListPojo.AvailableBranches> availableBranches) {
		this.availableBranches = availableBranches;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	
	@Override
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}
	
}
