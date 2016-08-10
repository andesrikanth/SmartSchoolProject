package com.smartSchool.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "registerBean", eager = true)
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
	private Date dateOfBirth = new Date();
	private String studentFatherName;
	private String studentMotherName;
	private String address;
	private String studentEmail;
	private String phoneNumber;
	private String alternativePhoneNumber;
	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	private List<ChoiceListPojo.AvailableSections> availableSections;
	private List<ChoiceListPojo.AvailableStandards> availableStandards;
	private Long selectedStandardId;
	private Long selectedSectionId;
	private Long selectedBranchId;
	private boolean studentRegistrationStatus;
	
	
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
	
	public void branchesLOVChange(ValueChangeEvent e){
		selectedBranchId=(Long)e.getNewValue();
		if(selectedBranchId !=null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableStandards=smartSchoolFacade.getAvailableStandardsList(selectedBranchId);
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
			availableSections=smartSchoolFacade.getAvailableSectionsList(selectedStandardId);
		}
	}
	
	public void validateStudentEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String selectedValue = (String) value;
		if(!selectedValue.contains("@") || !selectedValue.contains(".")){
			System.out.println("In");
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid email id", "Please enter valid email id"));
		}
		
	}
	
	public void validatePhNo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String selectedValue = (String) value;
		if(selectedValue == null || selectedValue.length() !=10){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid Phone Number", "Please enter valid Phone Number"));
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
			studentPojo.setStudentEmail(studentEmail);
			studentPojo.setStudentFatherName(studentFatherName);
			studentPojo.setStudentFirstName(studentFirstName);
			studentPojo.setStudentLastName(studentLastName);
			studentPojo.setStudentMiddleName(studentMiddleName);
			studentPojo.setStudentMotherName(studentMotherName);
			studentPojo.setCreatedBy(loggedUserName);
			studentPojo.setLastUpdatedBy(loggedUserName);
			studentPojo.setSelectedBranchId(selectedBranchId);
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			boolean status=smartSchoolFacade.registerStudent(studentPojo);
			studentRegistrationStatus=status;
	        // Show succes message.
			if(status){
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Student Registration Successful !"));
			}
	        
	        return null;
	    }

	

}
