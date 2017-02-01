package com.smartSchoolService.pojo;

public class SubjectRegisterPojo implements Cloneable{

	private Long key;
	private String subjectName;
	private String subjectDesc;
	private String subjectType;
	private Long selectedBranchId;
	private String createdByUserName;
	private String lastUpdatedByUserName;
	
	
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
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
	
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
	}
	
	
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	@Override
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}  

}
