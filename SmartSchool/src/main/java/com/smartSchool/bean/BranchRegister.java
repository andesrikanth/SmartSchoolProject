package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.BranchRegisterPojo;

@ManagedBean(name = "branchRegisterBean", eager = true)
@ViewScoped
public class BranchRegister  implements Serializable {

	private static final long serialVersionUID = 200L;
	
	private String branchName;
	private String branchAddress;
	private boolean branchRegisterStatus;
	
	
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
	public boolean isBranchRegisterStatus() {
		return branchRegisterStatus;
	}
	public void setBranchRegisterStatus(boolean branchRegisterStatus) {
		this.branchRegisterStatus = branchRegisterStatus;
	}
	
	public String registerBranch(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		BranchRegisterPojo branchRegisterPojo = new BranchRegisterPojo();
		
		branchRegisterPojo.setBranchName(branchName);
		branchRegisterPojo.setBranchAddress(branchAddress);
		branchRegisterPojo.setCreatedByUserName(loggedUserName);
		branchRegisterPojo.setLastUpdatedByUserName(loggedUserName);
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		branchRegisterStatus=smartSchoolFacade.registerBranch(branchRegisterPojo);
		
		if(branchRegisterStatus){
			// Show success message.
			FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Branch Registration Successful !"));
		}
		else {
			// Show failure message.
			FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Branch Registration Failed!! Please contact product support."));
		}
		
		return null;
 }


}
