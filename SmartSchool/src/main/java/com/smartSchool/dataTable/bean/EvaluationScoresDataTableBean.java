package com.smartSchool.dataTable.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;

import com.smartSchool.bean.EvaluationScores;
import com.smartSchool.dataTable.JsfTableDataModel;
import com.smartSchoolService.pojo.EvaluationScoresPojo;

@ManagedBean(name="evaluationScoresDataTable")
@ViewScoped
public class EvaluationScoresDataTableBean implements Serializable {

	private static final long serialVersionUID = 1104533986763387258L;

	private LazyDataModel<EvaluationScoresPojo> lazyDataModel;
	private List<EvaluationScoresPojo> list;
	    
	private EvaluationScoresPojo selectedEvaluationScoresPojo;
	
	
	 @PostConstruct
	   public void init() {
		 
		 
			 EvaluationScores evaluationScores = (EvaluationScores)FacesContext.getCurrentInstance().getViewRoot().getViewMap().get("evaluationScores");
			 
			 if(evaluationScores != null){
					 Long sectionId = evaluationScores.getSelectedSectionId();
					 Long subjectId = evaluationScores.getSelectedSubjectId();
					 Long examId = evaluationScores.getSelectedExamId();
					 Long studentId = evaluationScores.getSelectedStudentId();
					 
					 if((sectionId != null && !sectionId.equals(0)) && (subjectId != null && !subjectId.equals(0)) && (examId != null &&!examId.equals(0))){
						 
						 String defaultSelectQuery="select STUD.STUDENT_ID, STUD.STUDENT_FIRST_NAME, STUD.STUDENT_LAST_NAME , EVAL.SCHOOL_EVALUATION_SCORES_ID, EVAL_DETAILS.SUBJECT_SCORE,  EVAL_DETAILS.SCHOOL_EVALUATION_SCORES_DETAILS_ID, EVAL_DETAILS.COMMENTS FROM STUDENT_DETAILS STUD LEFT OUTER JOIN SCHOOL_EVALUATION_SCORES EVAL ON (STUD.STUDENT_ID =  EVAL.STUDENT_ID AND EVAL.SCHOOL_EXAMS_ID = "+examId+" AND EVAL.SECTION_ID = "+sectionId+") LEFT OUTER JOIN SCHOOL_EVALUATION_SCORES_DETAILS EVAL_DETAILS ON  (EVAL.SCHOOL_EVALUATION_SCORES_ID = EVAL_DETAILS.SCHOOL_EVALUATION_SCORES_ID AND EVAL_DETAILS.SUBJECT_ID = "+subjectId+") WHERE  STUD.REGISTERED_SECTION = "+sectionId;
						 String defaultCountQuery="select count(*) as range FROM STUDENT_DETAILS STUD LEFT OUTER JOIN SCHOOL_EVALUATION_SCORES EVAL ON (STUD.STUDENT_ID =  EVAL.STUDENT_ID AND EVAL.SCHOOL_EXAMS_ID = "+examId+" AND EVAL.SECTION_ID = "+sectionId+") LEFT OUTER JOIN SCHOOL_EVALUATION_SCORES_DETAILS EVAL_DETAILS ON  (EVAL.SCHOOL_EVALUATION_SCORES_ID = EVAL_DETAILS.SCHOOL_EVALUATION_SCORES_ID AND EVAL_DETAILS.SUBJECT_ID = "+subjectId+") WHERE  STUD.REGISTERED_SECTION = "+sectionId;
						   
						 if(studentId != null && studentId.intValue()!=0){
							 System.out.println("studentId" +studentId);
							 defaultSelectQuery = defaultSelectQuery + " AND STUD.STUDENT_ID = "+studentId;
							 defaultCountQuery = defaultCountQuery + " AND STUD.STUDENT_ID = "+studentId;
						 }
						 
					     lazyDataModel = new JsfTableDataModel(defaultSelectQuery,defaultCountQuery,"getAvailableStudentsListForEvaluationScoresDataTable");
					     
					 }
			 } 
		 
	 }

	 
	public LazyDataModel<EvaluationScoresPojo> getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyDataModel<EvaluationScoresPojo> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	public List<EvaluationScoresPojo> getList() {
		return list;
	}
	public void setList(List<EvaluationScoresPojo> list) {
		this.list = list;
	}
	public EvaluationScoresPojo getSelectedEvaluationScoresPojo() {
		return selectedEvaluationScoresPojo;
	}
	public void setSelectedEvaluationScoresPojo(EvaluationScoresPojo selectedEvaluationScoresPojo) {
		this.selectedEvaluationScoresPojo = selectedEvaluationScoresPojo;
	}
	
	
}
