package com.smartSchoolService.pojo;

import java.util.Date;

public class StudentPojo {
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
	
	public String getSelectedStudentGender() {
		return selectedStudentGender;
	}
	public void setSelectedStudentGender(String selectedStudentGender) {
		this.selectedStudentGender = selectedStudentGender;
	}
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
	}
	private String createdBy;
	private String lastUpdatedBy;
	
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
	
}
