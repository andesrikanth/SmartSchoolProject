package com.smartSchoolService.pojo;

import java.util.Date;
import java.util.List;

import com.smartSchoolService.util.ChoiceListPojo;

public class TeacherRegisterPojo implements Cloneable{

	private Long key;
	private String teacherFirstName;
	private String teacherMiddleName;
	private String teacherLastName;
	private Date dateOfBirth ;
	private String teacherGender;
	private String address;
	private String specialization;
	private Long selectedBranchId;
	private String teacherEmail;
	private String phoneNumber;
	private String alternativePhoneNumber;
	private String createdBy;
	private String lastUpdatedBy;
	
	//Below columns are being used for rendering students datatable
	private String branchName;
	public String[] teacherEmailNotAvail;
	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	
	public String getTeacherFirstName() {
		return teacherFirstName;
	}
	public void setTeacherFirstName(String teacherFirstName) {
		this.teacherFirstName = teacherFirstName;
	}
	public String getTeacherMiddleName() {
		return teacherMiddleName;
	}
	public void setTeacherMiddleName(String teacherMiddleName) {
		this.teacherMiddleName = teacherMiddleName;
	}
	public String getTeacherLastName() {
		return teacherLastName;
	}
	public void setTeacherLastName(String teacherLastName) {
		this.teacherLastName = teacherLastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getTeacherGender() {
		return teacherGender;
	}
	public void setTeacherGender(String teacherGender) {
		this.teacherGender = teacherGender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAlternativePhoneNumber() {
		return alternativePhoneNumber;
	}
	public void setAlternativePhoneNumber(String alternativePhoneNumber) {
		this.alternativePhoneNumber = alternativePhoneNumber;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String[] getTeacherEmailNotAvail() {
		return teacherEmailNotAvail;
	}
	public void setTeacherEmailNotAvail(String[] teacherEmailNotAvail) {
		this.teacherEmailNotAvail = teacherEmailNotAvail;
	}
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranches() {
		return availableBranches;
	}
	public void setAvailableBranches(List<ChoiceListPojo.AvailableBranches> availableBranches) {
		this.availableBranches = availableBranches;
	}
	
	@Override
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}
}
