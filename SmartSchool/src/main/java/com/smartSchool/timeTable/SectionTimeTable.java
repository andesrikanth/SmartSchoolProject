package com.smartSchool.timeTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.smartSchoolService.pojo.DayOfWeekPojo;
import com.smartSchoolService.pojo.SectionTimeTablePojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.pojo.TeacherRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "sectionTimetable")
@ViewScoped
public class SectionTimeTable  implements Serializable {

	private static final long serialVersionUID = -4559426515487292705L;

	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	private List<ChoiceListPojo.AvailableSections> availableSections;
	private List<ChoiceListPojo.AvailableStandards> availableStandards;
	private List<ChoiceListPojo.AvailableTimetableTemplates> availableTimetableTemplates;
	private List<TeacherRegisterPojo> availableTeachers;
	private List<SubjectRegisterPojo> availableSubjects;
	private List<DayOfWeekPojo> dayOfWeekList;
	
	private Long selectedBranchId;
	private Long selectedStandardId;
	private Long selectedSectionId;
	private Long selectedTimetableTemplateId;
	private String[] selectedDayOfWeek={"MON","TUE","WED","THU","FRI","SAT"};
	
	
	private boolean initializeStatus=false;
	private List<SectionTimeTablePojo> sectionTimeTableList;
	private boolean validationWarningStatus=false;
	private boolean timeTableCreationStatus=false;
	private boolean timeTableValidationStatus=false;
	
	
	@PostConstruct
	public void init(){
		if(availableBranches == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableBranches=smartSchoolFacade.getAvailableBranchesList();
		}
		if(availableTimetableTemplates == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableTimetableTemplates=smartSchoolFacade.getAvailableTimetableTemplatesList();
		}
		
		dayOfWeekList =  new ArrayList<DayOfWeekPojo>();
		dayOfWeekList.add(new DayOfWeekPojo("MON","Monday"));
		dayOfWeekList.add(new DayOfWeekPojo("TUE","Tuesday"));
		dayOfWeekList.add(new DayOfWeekPojo("WED","Wednesday"));
		dayOfWeekList.add(new DayOfWeekPojo("THU","Thursday"));
		dayOfWeekList.add(new DayOfWeekPojo("FRI","Friday"));
		dayOfWeekList.add(new DayOfWeekPojo("SAT","Saturday"));
		dayOfWeekList.add(new DayOfWeekPojo("SUN","Sunday"));
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
	
	public void validateTimetableTemplateLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Timetable Template.", "Please select a valid Timetable Template."));
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

	public Long getSelectedTimetableTemplateId() {
		return selectedTimetableTemplateId;
	}

	public void setSelectedTimetableTemplateId(Long selectedTimetableTemplateId) {
		this.selectedTimetableTemplateId = selectedTimetableTemplateId;
	}

	public List<ChoiceListPojo.AvailableTimetableTemplates> getAvailableTimetableTemplates() {
		return availableTimetableTemplates;
	}

	public void setAvailableTimetableTemplates(
			List<ChoiceListPojo.AvailableTimetableTemplates> availableTimetableTemplates) {
		this.availableTimetableTemplates = availableTimetableTemplates;
	}
	
	public boolean isInitializeStatus() {
		return initializeStatus;
	}

	public void setInitializeStatus(boolean initializeStatus) {
		this.initializeStatus = initializeStatus;
	}

	
	public List<SectionTimeTablePojo> getSectionTimeTableList() {
		return sectionTimeTableList;
	}

	public void setSectionTimeTableList(List<SectionTimeTablePojo> sectionTimeTableList) {
		this.sectionTimeTableList = sectionTimeTableList;
	}

	
	public void initializeTimetable(ActionEvent event){
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		boolean status = smartSchoolFacade.checkForExistingTimeTableForSection(selectedSectionId);
		if(status){
			FacesContext.getCurrentInstance().addMessage("initialize", new FacesMessage(FacesMessage.SEVERITY_ERROR,"An active timetable already exists for this section. Please review the existing timetable for this section and you can update/delete it from \"Review Section Timetable\".","Info"));
		}
		else {
			availableTeachers=smartSchoolFacade.getAvailableTeachersListForBranch(selectedBranchId);
			availableSubjects=smartSchoolFacade.getAvailableSubjectsListForBranch(selectedBranchId);
			
			if(availableTeachers == null){
				FacesContext.getCurrentInstance().addMessage("initialize", new FacesMessage(FacesMessage.SEVERITY_ERROR,"There are no teachers registered for this branch. Either select a different branch or Register a new teacher for this branch and revisit this page.","Info"));
			}
			else if(availableSubjects == null){
				FacesContext.getCurrentInstance().addMessage("initialize", new FacesMessage(FacesMessage.SEVERITY_ERROR,"There are no subjects registered for this branch. Either select a different branch or Register a new subject for this branch and revisit this page.","Info"));
			}
			else {
				sectionTimeTableList=smartSchoolFacade.getTimeTableTemplateDetailsForId(selectedTimetableTemplateId);
				initializeStatus=true;
			}
		}
		
		
		
	}
	
	public void resetTimetable(ActionEvent event){
		initializeStatus=false;
		validationWarningStatus=false;
		timeTableValidationStatus=false;
		timeTableCreationStatus=false;
			
	}
	
	
	public List<TeacherRegisterPojo> getAvailableTeachers() {
		return availableTeachers;
	}

	public void setAvailableTeachers(List<TeacherRegisterPojo> availableTeachers) {
		this.availableTeachers = availableTeachers;
	}

	public List<SubjectRegisterPojo> getAvailableSubjects() {
		return availableSubjects;
	}

	public void setAvailableSubjects(List<SubjectRegisterPojo> availableSubjects) {
		this.availableSubjects = availableSubjects;
	}
	
	

	public boolean isValidationWarningStatus() {
		return validationWarningStatus;
	}

	public void setValidationWarningStatus(boolean validationWarningStatus) {
		this.validationWarningStatus = validationWarningStatus;
	}
	
	

	public boolean isTimeTableValidationStatus() {
		return timeTableValidationStatus;
	}

	public void setTimeTableValidationStatus(boolean timeTableValidationStatus) {
		this.timeTableValidationStatus = timeTableValidationStatus;
	}

	public List<DayOfWeekPojo> getDayOfWeekList() {
		return dayOfWeekList;
	}

	public void setDayOfWeekList(List<DayOfWeekPojo> dayOfWeekList) {
		this.dayOfWeekList = dayOfWeekList;
	}

	public String[] getSelectedDayOfWeek() {
		return selectedDayOfWeek;
	}

	public void setSelectedDayOfWeek(String[] selectedDayOfWeek) {
		this.selectedDayOfWeek = selectedDayOfWeek;
	}

	
	public boolean isTimeTableCreationStatus() {
		return timeTableCreationStatus;
	}

	public void setTimeTableCreationStatus(boolean timeTableCreationStatus) {
		this.timeTableCreationStatus = timeTableCreationStatus;
	}
	
	public boolean validateSectionTimeTable(){
		timeTableValidationStatus=false;
		validationWarningStatus =  false;
		HashMap<Long, Integer> track = new HashMap<Long, Integer>(); 
		for(int i=0;i<sectionTimeTableList.size();i++){
			SectionTimeTablePojo sectionTimeTablePojo = sectionTimeTableList.get(i);
			Long subjectId = sectionTimeTablePojo.getSubjectId();
			//Long teacherId = sectionTimeTablePojo.getTeacherId();
			
			if(subjectId == null || (subjectId != null && subjectId ==0)){
				FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . Subject in Slot-"+(i+1)+" is blank. All time slots must be defined with a valid subject and teacher. Please correct the entry and try again.","Info"));
				timeTableValidationStatus=false;
				validationWarningStatus=false;
				break;
			}
			else {
					/*if(teacherId == null || (teacherId != null && teacherId ==0)){
						FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . Teacher in Slot-"+(i+1)+" is blank. All time slots must be defined with a valid subject and teacher. Please correct the entry and try again.","Info"));
						timeTableValidationStatus=false;
						validationWarningStatus=false;
						break;
					}
				*/
					Integer temp = track.get(subjectId);
					if(temp == null){
						track.put(subjectId, i+1);
						timeTableValidationStatus=true;
					}
					else {
						FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_WARN,  "Warning! The subjects in Slot-"+(i+1)+" and Slot-"+temp+" are same. If this is intentional, please click on below \"Validate and Create\" button again to create the timetable.","Info"));
						timeTableValidationStatus=true;
						validationWarningStatus=true;
					}
					
				
			}
				
		}
		return timeTableValidationStatus;
	}
	
	
	public String createSectionTimeTable(){
		timeTableCreationStatus=false;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		String status = null;
		//Below if condition will be successful only if the User is fine with the warning message and agreed to create timetable even after displaying the warning message.
		if(timeTableValidationStatus && validationWarningStatus){
			System.out.println("Warning approved");
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			status = smartSchoolFacade.createSectionTimeTableTemplate(sectionTimeTableList,selectedTimetableTemplateId , selectedSectionId, selectedDayOfWeek, loggedUserName);
		}
		else {
			timeTableValidationStatus = validateSectionTimeTable();
			
			if(timeTableValidationStatus && !validationWarningStatus){
				System.out.println("Insert Data");
				SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
				status = smartSchoolFacade.createSectionTimeTableTemplate(sectionTimeTableList,selectedTimetableTemplateId , selectedSectionId, selectedDayOfWeek, loggedUserName);
			}
		}
		
		if(status!=null && status.equals("true")){
			FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_INFO,  "Validation Successful! Section Timetable is now created in the system.","Info"));
			timeTableCreationStatus=true;
			
		}
		else if(status!=null && status.equals("false")){
			timeTableValidationStatus=false;
			FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Section Timetable creation Failed! Please contact product support.","Info"));
		}
		else if(status !=null){
			timeTableValidationStatus=false;
			FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  status,"Info"));
		}
		
		return null;
	}
	
}
