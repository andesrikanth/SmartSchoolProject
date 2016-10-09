package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.SubjectRegisterPojo;

@ManagedBean(name = "subjectRegisterBean", eager = true)
@ViewScoped
public class SubjectRegister  implements Serializable {

	private static final long serialVersionUID = 200L;
	
	private String subjectName;
	private String subjectDesc;
	private boolean subjectRegisterStatus;
	
	
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


	public boolean isSubjectRegisterStatus() {
		return subjectRegisterStatus;
	}


	public void setSubjectRegisterStatus(boolean subjectRegisterStatus) {
		this.subjectRegisterStatus = subjectRegisterStatus;
	}


	public String registerSubject(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		SubjectRegisterPojo subjectRegisterPojo = new SubjectRegisterPojo();
		
		subjectRegisterPojo.setSubjectName(subjectName);
		subjectRegisterPojo.setSubjectDesc(subjectDesc);
		subjectRegisterPojo.setCreatedByUserName(loggedUserName);
		subjectRegisterPojo.setLastUpdatedByUserName(loggedUserName);
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.registerSubject(subjectRegisterPojo);
		if(out !=null && out.equals("true")){
			subjectRegisterStatus=true;
			FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Subject Registration Successful !"));
		}
		else {
			subjectRegisterStatus=false;
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Subject Registration Failed! Please contact product support."));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage(out));
			}
		}
		
		
		return null;
 }
}