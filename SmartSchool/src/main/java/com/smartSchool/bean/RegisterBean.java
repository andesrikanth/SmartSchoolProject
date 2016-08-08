package com.smartSchool.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.StandardRegisterPojo;

@ManagedBean(name = "registerBean", eager = true)
@RequestScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 120L;
	
	private String standardName;
	private String standardDesc;
	private boolean standardRegisterStatus;
	
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private Date dateOfBirth;
	
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
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getStandardDesc() {
		return standardDesc;
	}
	public void setStandardDesc(String standardDesc) {
		this.standardDesc = standardDesc;
	}
	
	
	public boolean isStandardRegisterStatus() {
		return standardRegisterStatus;
	}
	public void setStandardRegisterStatus(boolean standardRegisterStatus) {
		this.standardRegisterStatus = standardRegisterStatus;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void registerStandard(ActionEvent event){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		StandardRegisterPojo standardRegisterPojo = new StandardRegisterPojo();
		standardRegisterPojo.setStandardDesc(standardDesc);
		standardRegisterPojo.setStandardName(standardName);
		standardRegisterPojo.setCreatedByUserName(loggedUserName);
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		standardRegisterStatus=smartSchoolFacade.registerStandard(standardRegisterPojo);
	}
	
	public void registerStudent(ActionEvent event){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
	}

}
