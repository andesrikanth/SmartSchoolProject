package com.smartSchool.bean;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.primefaces.event.SelectEvent;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.ExamSchedulePojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "examSchedule")
@ViewScoped

public class ExamScheduleBean implements Serializable {

	private static final long serialVersionUID = 5297827859127377569L;

	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	private List<ChoiceListPojo.AvailableSections> availableSections;
	private List<ChoiceListPojo.AvailableStandards> availableStandards;
	private List<SubjectRegisterPojo> availableSubjects;
	private List<ExamSchedulePojo.ExamScheduleSubjectPojo> examScheduleSubjectPojoList;
	
	private Long selectedBranchId;
	private Long selectedStandardId;
	private Long selectedSectionId;
	private String examsDetails;
	private Date examsStartDate;
	private Date examsEndDate;
	private boolean initializeStatus=false;	
	private boolean scheduleTableCreationStatus=false;
	private boolean validationStatus= false;
	private int availableSubjectsSize=0;
	
	@PostConstruct
	public void init(){
		if(availableBranches == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableBranches=smartSchoolFacade.getAvailableBranchesList();
		}
	}
	
	public void resetExamScheduleTable(ActionEvent event){
		initializeStatus=false;
		scheduleTableCreationStatus=false;
	}
	
	public void initializeExamScheduleTable(ActionEvent event){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		
		if(examsStartDate != null && examsEndDate !=null && examsStartDate.after(examsEndDate)){
			FacesContext.getCurrentInstance().addMessage("initialize", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Start Date can't be greater than End Date. Please provide valid Start Date.","Info"));
		}
		else {
			ExamSchedulePojo examSchedulePojo = smartSchoolFacade.checkForExistingExamsScheduleForSection(selectedSectionId, examsStartDate, examsEndDate);
			if(examSchedulePojo != null){
				FacesContext.getCurrentInstance().addMessage("initialize", new FacesMessage(FacesMessage.SEVERITY_WARN,"An existing exam schedule \""+examSchedulePojo.getExamDetails()+"\" overlaps with the given start & end dates. You may want to review the existing Exam Schedule before proceeding further.","Info"));
			}
			else {
				availableSubjects=smartSchoolFacade.getAvailableSubjectsListForSection(selectedSectionId);
				
				initializeStatus=false;
				if(availableSubjects == null){
					FacesContext.getCurrentInstance().addMessage("initialize", new FacesMessage(FacesMessage.SEVERITY_ERROR,"There are no subjects registered for this section. Either select a different section or create a timetable for this section and revisit this page.","Info"));
					initializeStatus=false;
				}
				else if(availableSubjects != null && availableSubjects.size()==0){
					FacesContext.getCurrentInstance().addMessage("initialize", new FacesMessage(FacesMessage.SEVERITY_ERROR,"There are no subjects registered for this section. Either select a different section or create a timetable for this section and revisit this page.","Info"));
					initializeStatus=false;
				}
				else if(availableSubjects.size()>0){
					ExamSchedulePojo examSchedulePojoLocal = new ExamSchedulePojo();
					initializeStatus=true;
					availableSubjectsSize=availableSubjects.size();
					examScheduleSubjectPojoList= new ArrayList<ExamSchedulePojo.ExamScheduleSubjectPojo>();
					
					for(int i=0;i<availableSubjects.size();i++){
						ExamSchedulePojo.ExamScheduleSubjectPojo examScheduleSubjectPojo= examSchedulePojoLocal.new ExamScheduleSubjectPojo();
						examScheduleSubjectPojo.setSubjectId(availableSubjects.get(i).getKey());
						examScheduleSubjectPojo.setSubjectName(availableSubjects.get(i).getSubjectName());
						examScheduleSubjectPojo.setCreatedByUserName(loggedUserName);
						examScheduleSubjectPojo.setLastUpdatedByUserName(loggedUserName);
						examScheduleSubjectPojoList.add(examScheduleSubjectPojo);
					}
					
				}
				
			}
		}
		
	}
	
	
	public boolean validateExamScheduleTable(){
		//Below variable is used for comparison to make sure that all dates are given incremental order. So that all timeslots will increase incrementally.
				for(int i=0;i<examScheduleSubjectPojoList.size();i++){
					
					/**
					 * Also needs to make sure that the dates & time combination is unique so that no subject exam overlaps with each other.
					 */
					//TODO
					//Pending work. Need to write the code
					
					ExamSchedulePojo.ExamScheduleSubjectPojo examScheduleSubjectPojo = this.examScheduleSubjectPojoList.get(i);
					if(examScheduleSubjectPojo != null){
						
						
						//Below is the validation to make sure the Start Time is not null.
						if(examScheduleSubjectPojo.getSubjectExamStartTime() == null ){
							validationStatus=false;
							FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry for Subject "+examScheduleSubjectPojo.getSubjectName()+" . Please provide valid exam start time.","Info"));
							break;
						}
						//Below is the validation to make sure the End Time is not null.
						else if(examScheduleSubjectPojo.getSubjectExamEndTime() == null ){
							validationStatus=false;
							FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry for Subject "+examScheduleSubjectPojo.getSubjectName()+" . Please provide valid exam end time.","Info"));
							break;
						}
						
						//Below is the validation to make sure the Exam Date is not null.
						else if(examScheduleSubjectPojo.getSubjectExamDate() == null){
							validationStatus=false;
							FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry for Subject "+examScheduleSubjectPojo.getSubjectName()+" . Please provide valid exam date.","Info"));
							break;
						}
						
						else if(examScheduleSubjectPojo.getSubjectExamStartTime() != null && examScheduleSubjectPojo.getSubjectExamEndTime() != null ){
							int compare= examScheduleSubjectPojo.getSubjectExamStartTime().compareTo(examScheduleSubjectPojo.getSubjectExamEndTime());
							//Below is the validation to make sure the Start Time is not greater than End Time.
							if (compare > 0){
								validationStatus=false;
								FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry for Subject "+examScheduleSubjectPojo.getSubjectName()+" . Exam Start Time cannot be greater than End Time.","Info"));
								break;
							}
							//Below is the validation to make sure the Start Time is not equal to End Time.
							else if( compare ==0){
								validationStatus=false;
								FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry  for Subject "+examScheduleSubjectPojo.getSubjectName()+" . Exam Start Time cannot be equal to End Time.","Info"));
								break;
							}
							else if( compare <0){
								validationStatus=true;
							}
							
						}
					}
					
				}
		
		
		return validationStatus;
	}
	
	public String createExamScheduleTable(){
		scheduleTableCreationStatus=false;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		String status = null;
		
		
		//Below if condition will be successful only if the User is fine with the warning message and agreed to create timetable even after displaying the warning message.
		validationStatus = validateExamScheduleTable();
			
		if(validationStatus){
			
			ExamSchedulePojo examSchedulePojo = new ExamSchedulePojo();
			examSchedulePojo.setBranchId(selectedBranchId);
			examSchedulePojo.setStandardId(selectedStandardId);
			examSchedulePojo.setSectionId(selectedSectionId);
			examSchedulePojo.setExamDetails(examsDetails);
			examSchedulePojo.setExamStartDate(examsStartDate);
			examSchedulePojo.setExamEndDate(examsEndDate);
			examSchedulePojo.setLastUpdatedByUserName(loggedUserName);
			examSchedulePojo.setCreatedByUserName(loggedUserName);
			
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		    status = smartSchoolFacade.createExamScheduleTable(examScheduleSubjectPojoList,  examSchedulePojo );
			
			if(status!=null && status.equals("true")){
				FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_INFO,  "Validation Successful! Exam Schedule is now created in the system.","Info"));
				scheduleTableCreationStatus=true;
				
			}
			else if(status!=null && status.equals("false")){
				scheduleTableCreationStatus=false;
				FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Exam Schedule creation Failed! Please contact product support.","Info"));
			}
			else if(status !=null){
				scheduleTableCreationStatus=false;
				FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  status,"Info"));
			}
		}
		
		
		return null;
	}
	
	public void onStartDateSelect(SelectEvent event) {
        this.setExamsStartDate((Date)event.getObject());
	}
	
	public void oneEndDateSelect(SelectEvent event) {
        this.setExamsEndDate((Date)event.getObject());
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
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
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
	public boolean isInitializeStatus() {
		return initializeStatus;
	}
	public void setInitializeStatus(boolean initializeStatus) {
		this.initializeStatus = initializeStatus;
	}

	public String getExamsDetails() {
		return examsDetails;
	}

	public void setExamsDetails(String examsDetails) {
		this.examsDetails = examsDetails;
	}

	public Date getExamsStartDate() {
		return examsStartDate;
	}

	public void setExamsStartDate(Date examsStartDate) {
		this.examsStartDate = examsStartDate;
	}

	public Date getExamsEndDate() {
		return examsEndDate;
	}

	public void setExamsEndDate(Date examsEndDate) {
		this.examsEndDate = examsEndDate;
	}

	public List<SubjectRegisterPojo> getAvailableSubjects() {
		return availableSubjects;
	}

	public void setAvailableSubjects(List<SubjectRegisterPojo> availableSubjects) {
		this.availableSubjects = availableSubjects;
	}

	public List<ExamSchedulePojo.ExamScheduleSubjectPojo> getExamScheduleSubjectPojoList() {
		return examScheduleSubjectPojoList;
	}

	public void setExamScheduleSubjectPojoList(List<ExamSchedulePojo.ExamScheduleSubjectPojo> examScheduleSubjectPojoList) {
		this.examScheduleSubjectPojoList = examScheduleSubjectPojoList;
	}

	public boolean isScheduleTableCreationStatus() {
		return scheduleTableCreationStatus;
	}

	public void setScheduleTableCreationStatus(boolean scheduleTableCreationStatus) {
		this.scheduleTableCreationStatus = scheduleTableCreationStatus;
	}

	public boolean isValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
	}

	public int getAvailableSubjectsSize() {
		return availableSubjectsSize;
	}

	public void setAvailableSubjectsSize(int availableSubjectsSize) {
		this.availableSubjectsSize = availableSubjectsSize;
	}
	
	
	
}
