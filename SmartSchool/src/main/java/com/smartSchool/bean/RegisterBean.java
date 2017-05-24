package com.smartSchool.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "registerBean", eager = true)
//@RequestScoped
@ViewScoped
public class RegisterBean implements Serializable {
	
	
	//This method is the first method called when a jsf page is loaded. Since it is ViewScoped, the bean/data will pertian only for this jsf page. 
	@PostConstruct
	public void init(){
		if(availableBranches == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableBranches=smartSchoolFacade.getAvailableBranchesList();
			/*if(availableBranches!= null && availableBranches.size()>0){
				availableStandards=smartSchoolFacade.getAvailableStandardsList(availableBranches.get(0).getBranchId());
				if(availableStandards!= null && availableStandards.size()>0){
					Long firstStandardId=availableStandards.get(0).getStandardId();
					availableSections=smartSchoolFacade.getAvailableSectionsList(firstStandardId);
				}
				else {
					availableSections=null;
				}
				
			}*/
				
		}
	}
	
	private static final long serialVersionUID = 120L;
	
	//Below variables are for Standard Registration.
	private String standardName;
	private String standardDesc;
	private boolean standardRegisterStatus;
	
	//Below variables are for Student Registration.
	private String studentFirstName;
	private String studentMiddleName;
	private String studentLastName;
	private String rollNo;
	private Date dateOfBirth = new Date();
	private String studentFatherName;
	private String studentMotherName;
	private String address;
	private String studentEmail;
	public String[] studentEmailNotAvail;
	private String phoneNumber;
	private String alternativePhoneNumber;
	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	private List<ChoiceListPojo.AvailableSections> availableSections;
	private List<ChoiceListPojo.AvailableStandards> availableStandards;
	private Long selectedStandardId;
	private Long selectedSectionId;
	private Long selectedBranchId;
	private String selectedStudentGender="M";
	private boolean studentRegistrationStatus;
	private String generatedUserName;
	private String generatedPwd;
	
   
	//Below variables are for Student Registration.
	private String sectionName;
	private String sectionDesc;
	private Long branchId;
	private Long standardId;
	private boolean sectionRegisterStatus;
	
	
	
	public boolean isSectionRegisterStatus() {
		return sectionRegisterStatus;
	}
	public void setSectionRegisterStatus(boolean sectionRegisterStatus) {
		this.sectionRegisterStatus = sectionRegisterStatus;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getSectionDesc() {
		return sectionDesc;
	}
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
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
	public String[] getStudentEmailNotAvail() {
		return studentEmailNotAvail;
	}
	public void setStudentEmailNotAvail(String[] studentEmailNotAvail) {
		this.studentEmailNotAvail = studentEmailNotAvail;
	}
	public String getSelectedStudentGender() {
		return selectedStudentGender;
	}
	public void setSelectedStudentGender(String selectedStudentGender) {
		this.selectedStudentGender = selectedStudentGender;
	}
	public boolean isStudentRegistrationStatus() {
		return studentRegistrationStatus;
	}
	public void setStudentRegistrationStatus(boolean studentRegistrationStatus) {
		this.studentRegistrationStatus = studentRegistrationStatus;
	}
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranches() {
    
		return availableBranches;
	}
	public void setAvailableBranches(List<ChoiceListPojo.AvailableBranches> availableBranches) {
		this.availableBranches = availableBranches;
	}
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
	}
	public List<ChoiceListPojo.AvailableStandards> getAvailableStandards() {
		return availableStandards;
	}
	public void setAvailableStandards(List<ChoiceListPojo.AvailableStandards> availableStandards) {
		this.availableStandards = availableStandards;
	}
	public List<ChoiceListPojo.AvailableSections> getAvailableSections() {
		return availableSections;
	}
	public void setAvailableSections(List<ChoiceListPojo.AvailableSections> availableSections) {
		this.availableSections = availableSections;
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
	
	
	public void branchesLOVChange(ValueChangeEvent e){
		selectedBranchId=(Long)e.getNewValue();
		if(selectedBranchId !=null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableStandards=smartSchoolFacade.getAvailableStandardsList(selectedBranchId,null);
			availableSections=null;
			//selectedStandardId=availableStandards.get(0).getStandardId();
			
			/*if(availableStandards!= null && availableStandards.size()>0){
				Long firstStandardId=availableStandards.get(0).getStandardId();
				availableSections=smartSchoolFacade.getAvailableSectionsList(firstStandardId);	
			}
			else {
				availableSections=null;
			}*/
			
		}	
	}
	
	public void standardsLOVChange(ValueChangeEvent e){
		selectedStandardId=(Long)e.getNewValue();
		if(selectedStandardId !=null){
    		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableSections=smartSchoolFacade.getAvailableSectionsList(selectedBranchId,selectedStandardId,null);
		}
	}
	
	public void studentEmailNotAvailValueChange(ValueChangeEvent e){
		if(e.getNewValue() != null){
			studentEmailNotAvail = (String[])e.getNewValue();
			if(studentEmailNotAvail.length==0){
				studentEmailNotAvail=null;
			}
		}
		
	}
	public void validateStudentEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
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
	
	public void validateStandardsLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Standard", "Please select a valid Standard"));
		}
	}
	
	public void validateSectionsLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Section", "Please select a valid Section"));
		}
		
	}
	
	
	 public String registerStudent() {

		 	HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String loggedUserName=(String)session.getAttribute("cur_user_name");
			
			StudentPojo studentPojo = new StudentPojo();
			studentPojo.setAddress(address);
			studentPojo.setAlternativePhoneNumber(alternativePhoneNumber);
			studentPojo.setDateOfBirth(dateOfBirth);
			studentPojo.setPhoneNumber(phoneNumber);
			studentPojo.setSelectedSectionId(selectedSectionId);
			studentPojo.setSelectedStandardId(selectedStandardId);
			studentPojo.setStudentFatherName(studentFatherName);
			studentPojo.setStudentFirstName(studentFirstName);
			studentPojo.setStudentLastName(studentLastName);
			studentPojo.setStudentMiddleName(studentMiddleName);
			studentPojo.setStudentMotherName(studentMotherName);
			studentPojo.setCreatedBy(loggedUserName);
			studentPojo.setLastUpdatedBy(loggedUserName);
			studentPojo.setSelectedBranchId(selectedBranchId);
			studentPojo.setSelectedStudentGender(selectedStudentGender);
			studentPojo.setRollNo(rollNo);
			if(studentEmailNotAvail == null || (studentEmailNotAvail!= null && studentEmailNotAvail.length==0)){
				studentPojo.setStudentEmail(studentEmail);
			}
			else{
				studentPojo.setStudentEmail("N");
			}
			
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			HashMap<String,String> output=smartSchoolFacade.registerStudent(studentPojo);
			String status=output.get("DB_STATUS");
			generatedUserName=output.get("USER_NAME");
			generatedPwd=output.get("PWD");
			if(status!= null && status.equalsIgnoreCase("true")){
				studentRegistrationStatus=true;
				
				// Show success message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Student Registration Successful !"));
			}
			else {
				// Show failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Student Registration Failed!! Please contact product support."));
			}
	        
	        return null;
	  }

	 public String registerStandard(){
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String loggedUserName=(String)session.getAttribute("cur_user_name");
			StandardRegisterPojo standardRegisterPojo = new StandardRegisterPojo();
			standardRegisterPojo.setStandardDesc(standardDesc);
			standardRegisterPojo.setStandardName(standardName);
			standardRegisterPojo.setBranchId(selectedBranchId);
			standardRegisterPojo.setCreatedByUserName(loggedUserName);
			standardRegisterPojo.setLastUpdatedByUserName(loggedUserName);
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			standardRegisterStatus=smartSchoolFacade.registerStandard(standardRegisterPojo);
			
			if(standardRegisterStatus){
				// Show succes message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Standard Registration Successful !"));
			}
			else {
				// Show failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Standard Registration Failed!! Please contact product support."));
			}
			return null;
	 }
	 
	 public String registerSection(){
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String loggedUserName=(String)session.getAttribute("cur_user_name");
			SectionRegisterPojo sectionRegisterPojo=new SectionRegisterPojo();
			sectionRegisterPojo.setBranchId(selectedBranchId);
			sectionRegisterPojo.setStandardId(selectedStandardId);
			sectionRegisterPojo.setSectionName(sectionName);
			sectionRegisterPojo.setSectionDesc(sectionDesc);
			sectionRegisterPojo.setCreatedByUserName(loggedUserName);
			sectionRegisterPojo.setLastUpdatedByUserName(loggedUserName);
			
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			sectionRegisterStatus=smartSchoolFacade.registerSection(sectionRegisterPojo);
			
			if(sectionRegisterStatus){
				// Show succes message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Section Registration Successful !"));
			}
			else {
				// Show failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Section Registration Failed!! Please contact product support."));
			}
			return null;
	 }
	 
	 public void onDateSelect(SelectEvent event) {
	        this.setDateOfBirth((Date)event.getObject());
	 }
	 
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	 
}
