package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.smartSchool.facade.SmartSchoolFacade;

@ManagedBean(name = "loginBean", eager = true)
@RequestScoped
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
		return loginAuthStatus;
		
	}
	
	public void SubmitLogin(ActionEvent event){
		
		//CommonBean commonBean=(CommonBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("commonBean");
		//System.out.println(commonBean.loginNavigation());

		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setMaxInactiveInterval(500);
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String status=smartSchoolFacade.validateUserLogin(userName, password);
		if(status != null && !status.equalsIgnoreCase("fail")){
			String[] array=status.split("%%");
			if(array !=null && array.length>2){
				session.setAttribute("cur_user_name", userName);
				session.setAttribute("cur_user_display_name", array[1]);
				session.setAttribute("cur_user_role_type", array[2]);
				this.setLoginAuthStatus(array[0]);
			}
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
	
	
}
