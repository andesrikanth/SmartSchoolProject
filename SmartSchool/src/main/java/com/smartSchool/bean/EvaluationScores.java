package com.smartSchool.bean;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.EvaluationScoresPojo;
import com.smartSchoolService.pojo.ExamSchedulePojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "evaluationScores")
@ViewScoped

public class EvaluationScores implements Serializable {

	private static final long serialVersionUID = 5297827859127377569L;

	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	private List<ChoiceListPojo.AvailableSections> availableSections;
	private List<ChoiceListPojo.AvailableStandards> availableStandards;
	private List<SubjectRegisterPojo> availableSubjects;
	private List<StudentPojo> availableStudents;
	private List<ExamSchedulePojo> availableExams;
	
	private HashMap<Long,EvaluationScoresPojo> modifiedEvaluationScoresEntries = new HashMap<Long,EvaluationScoresPojo>();
	private HashMap<Long,EvaluationScoresPojo> newEvaluationScoresEntries = new HashMap<Long,EvaluationScoresPojo>();
	
	private Long selectedBranchId;
	private Long selectedStandardId;
	private Long selectedSectionId;
	private Long selectedSubjectId;
	private Long selectedStudentId;
	private Long selectedExamId;
	private boolean initializeStatus=false;	
	private boolean validationStatus= false;
	private int availableSubjectsSize=0;
	private String valueStatus;
	
	@PostConstruct
	public void init(){
		if(availableBranches == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableBranches=smartSchoolFacade.getAvailableBranchesList();
		}
	}
	
	public void resetEvaluationScoresTable(ActionEvent event){
		initializeStatus=false;
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("evaluationScoresDataTable");
	}
	
	public void initializeEvaluationScoreTable(ActionEvent event){
		
		initializeStatus = true;
		
	}
	
	public void saveEvaluationScores(ActionEvent event){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		
		String status = "false";
		if(newEvaluationScoresEntries != null && newEvaluationScoresEntries.size()>0){
			status = smartSchoolFacade.createEvaluationScoresForSubject(selectedBranchId, selectedStandardId, selectedSectionId, selectedExamId, selectedSubjectId, newEvaluationScoresEntries, loggedUserName);	
		}
		
		if(modifiedEvaluationScoresEntries != null && modifiedEvaluationScoresEntries.size()>0){
			status = smartSchoolFacade.updateExistingEvaluationScoresDetailsForSubject(modifiedEvaluationScoresEntries, loggedUserName);
		}
		
		
		//EvaluationScoresDataTableBean evaluationScoresDataTableBean = (EvaluationScoresDataTableBean)FacesContext.getCurrentInstance().getViewRoot().getViewMap().get("evaluationScoresDataTable");
		
		//LazyDataModel<EvaluationScoresPojo> lazyDataModel = evaluationScoresDataTableBean.getLazyDataModel();
			
		if(status != null && status.equalsIgnoreCase("true")){
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_INFO,  "Validation Successful! Evaluation Scores are now stored in the system.","Info"));
		}
		else {
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Failed to create Evaluation Scores! Please contact product support.","Info"));
		}
     	
		//return null;
 		
	}
	
	public void onEditLabel(CellEditEvent event) {
	    Object oldValue = event.getOldValue();
	    Object newValue = event.getNewValue();

	    if(newValue != null && !newValue.equals(oldValue)) {
	    	try {
		    	DataTable data = (DataTable) event.getSource();
		    	EvaluationScoresPojo evaluationScoresPojoLocal = (EvaluationScoresPojo) data.getRowData();
		    	if(evaluationScoresPojoLocal != null){
		    		Long studentId = evaluationScoresPojoLocal.getKey();
		    		Long schoolEvaluationScoresDetailsId = evaluationScoresPojoLocal.getSchoolEvaluationScoresDetailsId();
		    		System.out.println("New Score "+evaluationScoresPojoLocal.getSubjectScore());
		    		if(schoolEvaluationScoresDetailsId != null && schoolEvaluationScoresDetailsId.intValue() != 0){
		    			modifiedEvaluationScoresEntries.put(studentId, evaluationScoresPojoLocal);
		    		}
		    		else {
		    			newEvaluationScoresEntries.put(studentId, evaluationScoresPojoLocal);
		    			
		    		}
		    		
		    	}
		    	
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    	
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
	
	public void sectionsLOVChange(ValueChangeEvent e){
		selectedSectionId=(Long)e.getNewValue();
		if(selectedSectionId !=null){
    		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableSubjects=smartSchoolFacade.getAvailableSubjectsListForSection(selectedSectionId);
			availableStudents=smartSchoolFacade.getAvailableStudentsListForSection(selectedSectionId);
			availableExams = smartSchoolFacade.getAvailableExamsListForSection(selectedSectionId);
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
	
	public void validateExamsLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Exam", "Please select a valid Exam"));
		}
		
	}
	
	public void validateSubjectsLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Subject", "Please select a valid Subject"));
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


	public List<SubjectRegisterPojo> getAvailableSubjects() {
		return availableSubjects;
	}

	public void setAvailableSubjects(List<SubjectRegisterPojo> availableSubjects) {
		this.availableSubjects = availableSubjects;
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

	public List<StudentPojo> getAvailableStudents() {
		return availableStudents;
	}

	public void setAvailableStudents(List<StudentPojo> availableStudents) {
		this.availableStudents = availableStudents;
	}

	public Long getSelectedSubjectId() {
		return selectedSubjectId;
	}

	public void setSelectedSubjectId(Long selectedSubjectId) {
		this.selectedSubjectId = selectedSubjectId;
	}

	public Long getSelectedStudentId() {
		return selectedStudentId;
	}

	public void setSelectedStudentId(Long selectedStudentId) {
		this.selectedStudentId = selectedStudentId;
	}

	public Long getSelectedExamId() {
		return selectedExamId;
	}

	public void setSelectedExamId(Long selectedExamId) {
		this.selectedExamId = selectedExamId;
	}

	public List<ExamSchedulePojo> getAvailableExams() {
		return availableExams;
	}

	public void setAvailableExams(List<ExamSchedulePojo> availableExams) {
		this.availableExams = availableExams;
	}
	
	public void evaluationScoreInputValueChange(ValueChangeEvent e){
		System.out.println("score value changed");
	}
	
	public void namedChanged(AjaxBehaviorEvent event) {
		System.out.println("ajax score value changed");
	
	}

	public String getValueStatus() {
		return valueStatus;
	}

	public void setValueStatus(String valueStatus) {
		this.valueStatus = valueStatus;
	}

	public HashMap<Long, EvaluationScoresPojo> getModifiedEvaluationScoresEntries() {
		return modifiedEvaluationScoresEntries;
	}

	public void setModifiedEvaluationScoresEntries(HashMap<Long, EvaluationScoresPojo> modifiedEvaluationScoresEntries) {
		this.modifiedEvaluationScoresEntries = modifiedEvaluationScoresEntries;
	}

	public HashMap<Long, EvaluationScoresPojo> getNewEvaluationScoresEntries() {
		return newEvaluationScoresEntries;
	}

	public void setNewEvaluationScoresEntries(HashMap<Long, EvaluationScoresPojo> newEvaluationScoresEntries) {
		this.newEvaluationScoresEntries = newEvaluationScoresEntries;
	}

	
	
	
}
