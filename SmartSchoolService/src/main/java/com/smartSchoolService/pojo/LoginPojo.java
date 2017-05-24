package com.smartSchoolService.pojo;

public class LoginPojo {

	private String currentUserName;
	private String currentUserDisplayName;
	private String loginValidationStatus;
	private String currentUserRoleType;
	private String userPasswordResetFlag;
	private String currentFiscalYear;
	
	
	public String getCurrentUserName() {
		return currentUserName;
	}
	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}
	public String getCurrentUserDisplayName() {
		return currentUserDisplayName;
	}
	public void setCurrentUserDisplayName(String currentUserDisplayName) {
		this.currentUserDisplayName = currentUserDisplayName;
	}
	
	public String getCurrentUserRoleType() {
		return currentUserRoleType;
	}
	public void setCurrentUserRoleType(String currentUserRoleType) {
		this.currentUserRoleType = currentUserRoleType;
	}
	
	public String getCurrentFiscalYear() {
		return currentFiscalYear;
	}
	public void setCurrentFiscalYear(String currentFiscalYear) {
		this.currentFiscalYear = currentFiscalYear;
	}
	public String getLoginValidationStatus() {
		return loginValidationStatus;
	}
	public void setLoginValidationStatus(String loginValidationStatus) {
		this.loginValidationStatus = loginValidationStatus;
	}
	public String getUserPasswordResetFlag() {
		return userPasswordResetFlag;
	}
	public void setUserPasswordResetFlag(String userPasswordResetFlag) {
		this.userPasswordResetFlag = userPasswordResetFlag;
	}
	
}
