package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 120L;

	private String userName;
	private String password;
	private String loginAuthStatus;//possible values are success and fail
	
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
	
	
	
	public String LoginAuthenticationStatus(){
		return "success";
		
	}
	
	public void SubmitLogin(ActionEvent event){
		System.out.println("In authen");
	}
	
	
}
