package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.LoginPojo;

@ManagedBean(name = "loginBean", eager = true)
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 120L;

	private String userName;
	private String password;
	private String loginAuthStatus;//possible values are success and fail
	
	//Reset Password
	private String resetPwd;
	private String reneterResetPwd;
	private String resetPasswordStatus;
	
	//Forgot password
	private String registeredEmailId;
	private String registeredPhoneNo;
	private boolean forgotPasswordStatus;
	
	public String getLoginAuthStatus() {
		return loginAuthStatus;
	}
	public void setLoginAuthStatus(String loginAuthStatus) {
		this.loginAuthStatus = loginAuthStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRegisteredEmailId() {
		return registeredEmailId;
	}
	public void setRegisteredEmailId(String registeredEmailId) {
		this.registeredEmailId = registeredEmailId;
	}
	public String getRegisteredPhoneNo() {
		return registeredPhoneNo;
	}
	public void setRegisteredPhoneNo(String registeredPhoneNo) {
		this.registeredPhoneNo = registeredPhoneNo;
	}
	public String getResetPwd() {
		return resetPwd;
	}
	public void setResetPwd(String resetPwd) {
		this.resetPwd = resetPwd;
	}
	public String getReneterResetPwd() {
		return reneterResetPwd;
	}
	public void setReneterResetPwd(String reneterResetPwd) {
		this.reneterResetPwd = reneterResetPwd;
	}
	public String getResetPasswordStatus() {
		return resetPasswordStatus;
	}
	public void setResetPasswordStatus(String resetPasswordStatus) {
		this.resetPasswordStatus = resetPasswordStatus;
	}
	public String LoginAuthenticationStatus(){
		return loginAuthStatus;
		
	}
	
	public String ResetPasswordSubmitStatus(){
		return resetPasswordStatus;
		
	}

	public boolean isForgotPasswordStatus() {
		return forgotPasswordStatus;
	}
	public void setForgotPasswordStatus(boolean forgotPasswordStatus) {
		this.forgotPasswordStatus = forgotPasswordStatus;
	}
	
	public void SubmitLogin(ActionEvent event){
		
		//CommonBean commonBean=(CommonBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("commonBean");
		//System.out.println(commonBean.loginNavigation());

		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setMaxInactiveInterval(500);
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		if(userName != null){
			userName=userName.toUpperCase();
		}
		LoginPojo status=smartSchoolFacade.validateUserLogin(userName, password);
		if(status != null && !status.getLoginValidationStatus().equals("fail")){
			
				session.setAttribute("cur_user_name", userName);
				session.setAttribute("cur_user_display_name", status.getCurrentUserDisplayName());
				session.setAttribute("cur_user_role_type", status.getCurrentUserRoleType());
				session.setAttribute("cur_user_pwd_reset", status.getUserPasswordResetFlag());
				session.setAttribute("current_fiscal_year", status.getCurrentFiscalYear());
				
				if(status.getLoginValidationStatus()!= null && status.getLoginValidationStatus().equals("success")){
					this.setLoginAuthStatus(status.getCurrentUserRoleType());
				}
				
		}
		else {
			FacesContext.getCurrentInstance().addMessage("validationMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed. Invalid credentials.", "Info"));
		}
		
		System.out.println("LoginBean ::  Logged-in username: "+session.getAttribute("cur_user_name"));
		System.out.println("LoginBean ::  Logged-in user role : "+session.getAttribute("cur_user_role_type"));
	}
	
	public void logout(ActionEvent event){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(session !=null){
			session.invalidate();
		}
		System.out.println("Logged-out");
	}
	
	public void SubmitResetPassword(ActionEvent event){
		
		//CommonBean commonBean=(CommonBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("commonBean");
		//System.out.println(commonBean.loginNavigation());

		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setMaxInactiveInterval(500);
		
		if(resetPwd!= null && reneterResetPwd!= null){
			if(!resetPwd.equals(reneterResetPwd)){
				FacesContext.getCurrentInstance().addMessage("validationMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Both password's must match each other !", "Info"));
			}
			else {
				SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
				userName=(String)session.getAttribute("cur_user_name");
				String status=smartSchoolFacade.resetUserPassword(userName, resetPwd);
				if(status!=null){
					if(status.equals("success")){
						session.setAttribute("cur_user_pwd_reset", "N");
					}
					else if(status.equals("fail")){
						FacesContext.getCurrentInstance().addMessage("validationMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Some error occured while updating password! Please contact product support.", "Info"));
					}
				}
				this.setResetPasswordStatus(status);
				
				
			}
		}
		else {
			FacesContext.getCurrentInstance().addMessage("validationMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password can't be blank !", "Info"));
		}
		
	}
	
	public void SubmitForgotPassword(ActionEvent event){
		forgotPasswordStatus =false;
		if((registeredEmailId == null && registeredPhoneNo == null) || ((registeredEmailId != null && registeredEmailId.equals("")) && (registeredPhoneNo != null && registeredPhoneNo.equals(""))) ){
			FacesContext.getCurrentInstance().addMessage("validationMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Either Registered Email Id or Registered Phone Number is required for changing the password.", "Info"));
		}
		else {
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			
			if(userName != null){
				userName=userName.toUpperCase();
			}
			
			String status = smartSchoolFacade.submitForgotPassword(userName,registeredEmailId,registeredPhoneNo);
			
			if(status != null && status.contains("Temporary Password has been sent to your registered email id")){
				forgotPasswordStatus=true;
				FacesContext.getCurrentInstance().addMessage("validationMessages", new FacesMessage(FacesMessage.SEVERITY_INFO, status, "Info"));
			}
			else {
				FacesContext.getCurrentInstance().addMessage("validationMessages", new FacesMessage(FacesMessage.SEVERITY_ERROR, status, "Info"));
			}
			
		}
		
		
		
		
	}
	
}
