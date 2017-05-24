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
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.HomeworkPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "homeworkBean", eager = true)
@ViewScoped
public class HomeworkBean implements Serializable {
	
	private static final long serialVersionUID = 1921905681979129840L;

	@PostConstruct
	public void init(){
		if(availableBranches == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableBranches=smartSchoolFacade.getAvailableBranchesList();
		}
	}
	
	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	private List<ChoiceListPojo.AvailableSections> availableSections;
	private List<ChoiceListPojo.AvailableStandards> availableStandards;
	private List<SubjectRegisterPojo> availableSubjects;
	private List<StudentPojo> availableStudents;
	
	private Long selectedStandardId;
	private Long selectedSectionId;
	private Long selectedBranchId;
	private Long selectedSubjectId;
	private Long selectedStudentId;
	private Date assignmentDate=new Date();
	private String assignmentDetails;
	private boolean homeworkCreationStatus=false;
	
	public String createHomework(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		String currentFiscalPeriod = (String)session.getAttribute("current_fiscal_year");
		
		HomeworkPojo homeworkPojo=new HomeworkPojo();
		
		homeworkPojo.setBranchId(selectedBranchId);
		homeworkPojo.setStandardId(selectedStandardId);
		homeworkPojo.setSectionId(selectedSectionId);
		homeworkPojo.setSubjectId(selectedSubjectId);
		homeworkPojo.setStudentId(selectedStudentId);
		homeworkPojo.setAssignmentDate(assignmentDate);
		homeworkPojo.setAssignmentDetails(assignmentDetails);
		homeworkPojo.setCreatedByUserName(loggedUserName);
		homeworkPojo.setLastUpdatedByUserName(loggedUserName);
		
		String creationStatus="false";
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		creationStatus=smartSchoolFacade.createHomework(homeworkPojo,currentFiscalPeriod);
		
		if(creationStatus!=null && creationStatus.equals("true")){
			// Show succes message.
			FacesContext.getCurrentInstance().addMessage("register", new FacesMessage(FacesMessage.SEVERITY_INFO,"Assignment/Homework created successfully!!","Info"));
			homeworkCreationStatus=true;
		}
		else {
			// Show failure message.
			FacesContext.getCurrentInstance().addMessage("register", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Assignment/Homework creation Failed!! Please contact product support.","Error"));
			homeworkCreationStatus=false;
		}
		return null;
    }
	
	public void onDateSelect(SelectEvent event) {
        this.setAssignmentDate((Date)event.getObject());
	}
	
	public void sectionsLOVChange(ValueChangeEvent e){
		selectedSectionId=(Long)e.getNewValue();
		if(selectedSectionId !=null){
    		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableSubjects=smartSchoolFacade.getAvailableSubjectsListForSection(selectedSectionId);
			availableStudents=smartSchoolFacade.getAvailableStudentsListForSection(selectedSectionId);
		}
	}
	
	public void branchesLOVChange(ValueChangeEvent e){
		selectedBranchId=(Long)e.getNewValue();
		if(selectedBranchId !=null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableStandards=smartSchoolFacade.getAvailableStandardsList(selectedBranchId,null);
			availableSections=null;
		}	
	}
	
	public void standardsLOVChange(ValueChangeEvent e){
		selectedStandardId=(Long)e.getNewValue();
		if(selectedStandardId !=null){
    		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableSections=smartSchoolFacade.getAvailableSectionsList(selectedBranchId,selectedStandardId,null);
		}
	}
	
	
	
	public void validateSubjectsLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Subject", "Please select a valid Subject"));
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
	
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranches() {
		return availableBranches;
	}
	public void setAvailableBranches(List<ChoiceListPojo.AvailableBranches> availableBranches) {
		this.availableBranches = availableBranches;
	}
	public List<ChoiceListPojo.AvailableSections> getAvailableSections() {
		return availableSections;
	}
	public void setAvailableSections(List<ChoiceListPojo.AvailableSections> availableSections) {
		this.availableSections = availableSections;
	}
	public List<ChoiceListPojo.AvailableStandards> getAvailableStandards() {
		return availableStandards;
	}
	public void setAvailableStandards(List<ChoiceListPojo.AvailableStandards> availableStandards) {
		this.availableStandards = availableStandards;
	}
	public List<SubjectRegisterPojo> getAvailableSubjects() {
		return availableSubjects;
	}
	public void setAvailableSubjects(List<SubjectRegisterPojo> availableSubjects) {
		this.availableSubjects = availableSubjects;
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
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
	}
	public Long getSelectedSubjectId() {
		return selectedSubjectId;
	}
	public void setSelectedSubjectId(Long selectedSubjectId) {
		this.selectedSubjectId = selectedSubjectId;
	}

	public Date getAssignmentDate() {
		return assignmentDate;
	}

	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public String getAssignmentDetails() {
		return assignmentDetails;
	}

	public void setAssignmentDetails(String assignmentDetails) {
		this.assignmentDetails = assignmentDetails;
	}

	public boolean isHomeworkCreationStatus() {
		return homeworkCreationStatus;
	}

	public void setHomeworkCreationStatus(boolean homeworkCreationStatus) {
		this.homeworkCreationStatus = homeworkCreationStatus;
	}

	public Long getSelectedStudentId() {
		return selectedStudentId;
	}

	public void setSelectedStudentId(Long selectedStudentId) {
		this.selectedStudentId = selectedStudentId;
	}

	public List<StudentPojo> getAvailableStudents() {
		return availableStudents;
	}

	public void setAvailableStudents(List<StudentPojo> availableStudents) {
		this.availableStudents = availableStudents;
	}
	
	
}

