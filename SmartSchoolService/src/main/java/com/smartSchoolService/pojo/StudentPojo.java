package com.smartSchoolService.pojo;

import java.util.Date;
import java.util.List;

import com.smartSchoolService.util.ChoiceListPojo;

public class StudentPojo implements Cloneable{
	
	private Long key;
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private Date dateOfBirth;
	private String studentFatherName;
	private String studentMotherName;
	private String address;
	private String studentEmail;
	private String phoneNumber;
	private String alternativePhoneNumber;
	private Long selectedStandardId;
	private Long selectedSectionId;
	private Long selectedBranchId;
	private String selectedStudentGender;
	private String createdBy;
	private String lastUpdatedBy;
	
	//Below columns are being used for rendering students datatable
	private String branchName;
	private String standardName;
	private String sectionName;
	public String[] studentEmailNotAvail;
	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	private List<ChoiceListPojo.AvailableSections> availableSections;
	private List<ChoiceListPojo.AvailableStandards> availableStandards;
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getSelectedStudentGender() {
		return selectedStudentGender;
	}
	public void setSelectedStudentGender(String selectedStudentGender) {
		if(selectedStudentGender != null){
			if(selectedStudentGender.equals("Male")){
				selectedStudentGender="M";
			}
			else {
				selectedStudentGender="F";
			}
		}
		this.selectedStudentGender = selectedStudentGender;
	}
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
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
	public String getStudentFirstName() {
		return studentFirstName;
	}
	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}
	public String getStudentMiddleName() {
		return studentMiddleName;
	}
	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}
	public String getStudentLastName() {
		return studentLastName;
	}
	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getStudentFatherName() {
		return studentFatherName;
	}
	public void setStudentFatherName(String studentFatherName) {
		this.studentFatherName = studentFatherName;
	}
	public String getStudentMotherName() {
		return studentMotherName;
	}
	public void setStudentMotherName(String studentMotherName) {
		this.studentMotherName = studentMotherName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
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
	public Long getSelectedStandardId() {
		return selectedStandardId;
	}
	public void setSelectedStandardId(Long selectedStandardId) {
		this.selectedStandardId = selectedStandardId;
	}
	public Long getSelectedSectionId() {
		return selectedSectionId;
	}
	public void setSelectedSectionId(Long selectedSectionId) {
		this.selectedSectionId = selectedSectionId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String[] getStudentEmailNotAvail() {
		return studentEmailNotAvail;
	}
	public void setStudentEmailNotAvail(String[] studentEmailNotAvail) {
		this.studentEmailNotAvail = studentEmailNotAvail;
	}
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranches() {
		return availableBranches;
	}
	public void setAvailableBranches(List<ChoiceListPojo.AvailableBranches> availableBranches) {
		this.availableBranches = availableBranches;
	}
	public List<ChoiceListPojo.AvailableSections> getAvailableSections() {
		return availableSections;
	}
	public void setAvailableSections(List<ChoiceListPojo.AvailableSections> availableSections) {
		this.availableSections = availableSections;
	}
	public List<ChoiceListPojo.AvailableStandards> getAvailableStandards() {
		return availableStandards;
	}
	public void setAvailableStandards(List<ChoiceListPojo.AvailableStandards> availableStandards) {
		this.availableStandards = availableStandards;
	}
	
	@Override
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}  
}
