package com.smartSchool.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.TeacherRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "teacherBean", eager = true)
@ViewScoped
public class TeacherRegisterBean  implements Serializable {

	private static final long serialVersionUID = 250L;

		//This method is the first method called when a jsf page is loaded. Since it is ViewScoped, the bean/data will pertian only for this jsf page. 
		@PostConstruct
		public void init(){
			if(availableBranches == null){
				SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
				availableBranches=smartSchoolFacade.getAvailableBranchesList();
			}
		}
		
		private List<ChoiceListPojo.AvailableBranches> availableBranches;
		private String teacherFirstName;
		private String teacherMiddleName;
		private String teacherLastName;
		private Date dateOfBirth = new Date();
		private String teacherGender="M";
		private String address;
		private String specialization;
		private Long selectedBranchId;
		private String teacherEmail;
		public String[] teacherEmailNotAvail;
		private String phoneNumber;
		private String alternativePhoneNumber;
		private boolean teacherRegistrationStatus;
		
		private String generatedUserName;
		private String generatedPwd;

		
		public String getGeneratedUserName() {
			return generatedUserName;
		}
		public void setGeneratedUserName(String generatedUserName) {
			this.generatedUserName = generatedUserName;
		}
		public String getGeneratedPwd() {
			return generatedPwd;
		}
		public void setGeneratedPwd(String generatedPwd) {
			this.generatedPwd = generatedPwd;
		}
		public List<ChoiceListPojo.AvailableBranches> getAvailableBranches() {
			return availableBranches;
		}
		public void setAvailableBranches(List<ChoiceListPojo.AvailableBranches> availableBranches) {
			this.availableBranches = availableBranches;
		}
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
		public String[] getTeacherEmailNotAvail() {
			return teacherEmailNotAvail;
		}
		public void setTeacherEmailNotAvail(String[] teacherEmailNotAvail) {
			this.teacherEmailNotAvail = teacherEmailNotAvail;
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
		public boolean isTeacherRegistrationStatus() {
			return teacherRegistrationStatus;
		}
		public void setTeacherRegistrationStatus(boolean teacherRegistrationStatus) {
			this.teacherRegistrationStatus = teacherRegistrationStatus;
		}
		
		
		public void emailNotAvailValueChange(ValueChangeEvent e){
			if(e.getNewValue() != null){
				teacherEmailNotAvail = (String[])e.getNewValue();
				if(teacherEmailNotAvail.length==0){
					teacherEmailNotAvail=null;
				}
			}
			
		}
		public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
			if(value != null){
				String selectedValue = (String) value;
				if(!selectedValue.contains("@") || !selectedValue.contains(".")){
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid email id", "Please enter valid email id"));
				}
			}
			
		}
		
		public void validatePhNo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
			if(value != null){
				String selectedValue = (String) value;
				if(selectedValue.length() !=10){
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid Phone Number", "Please enter valid Phone Number"));
				}
				else if (!(selectedValue.matches("[0-9]+") && selectedValue.length() > 2)) {
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid Phone Number", "Please enter valid Phone Number"));
				}
			}
			
		}
		
		public void validateBranchesLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
			
			if(value ==null || (Long)value == 0){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Branch", "Please select a valid Branch"));
			}
		}
		
		public String registerTeacher() {

		 	HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String loggedUserName=(String)session.getAttribute("cur_user_name");
			
			TeacherRegisterPojo teacherRegisterPojo = new TeacherRegisterPojo();
			teacherRegisterPojo.setAddress(address);
			teacherRegisterPojo.setAlternativePhoneNumber(alternativePhoneNumber);
			teacherRegisterPojo.setCreatedBy(loggedUserName);
			teacherRegisterPojo.setLastUpdatedBy(loggedUserName);
			teacherRegisterPojo.setDateOfBirth(dateOfBirth);
			teacherRegisterPojo.setPhoneNumber(phoneNumber);
			teacherRegisterPojo.setSelectedBranchId(selectedBranchId);
			teacherRegisterPojo.setSpecialization(specialization);
			
			teacherRegisterPojo.setTeacherFirstName(teacherFirstName);
			teacherRegisterPojo.setTeacherGender(teacherGender);
			teacherRegisterPojo.setTeacherLastName(teacherLastName);
			teacherRegisterPojo.setTeacherMiddleName(teacherMiddleName);
			
			if(teacherEmailNotAvail == null || (teacherEmailNotAvail!= null && teacherEmailNotAvail.length==0)){
				teacherRegisterPojo.setTeacherEmail(teacherEmail);
			}
			else{
				teacherRegisterPojo.setTeacherEmail("N");
			}
			
			
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			
			HashMap<String,String> output=smartSchoolFacade.registerTeacher(teacherRegisterPojo);
			String status=output.get("DB_STATUS");
			generatedUserName=output.get("USER_NAME");
			generatedPwd=output.get("PWD");
			if(status!= null && status.equalsIgnoreCase("true")){
				teacherRegistrationStatus=true;
				// Show success message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Teacher Registration Successful !"));
			}
			else {
				// Show failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Teacher Registration Failed!! Please contact product support."));
			}
	        
	        return null;
	  }
		
		
	public void onDateSelect(SelectEvent event) {
	     this.setDateOfBirth((Date)event.getObject());
	 }

		
}
