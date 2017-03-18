package com.smartSchoolService.pojo;

import java.math.BigDecimal;

public class EvaluationScoresPojo implements Cloneable{

	private Long key;
	private String studentName;
	private BigDecimal subjectScore;
	private Long schoolEvaluationScoresDetailsId;
	private Long schoolEvaluationScoresId;
	private String grade;
	private String comments;
	private boolean recordUpdatedStatus;
	private String createdByUserName;
	private String lastUpdatedByUserName;
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public BigDecimal getSubjectScore() {
		return subjectScore;
	}
	public void setSubjectScore(BigDecimal subjectScore) {
		this.subjectScore = subjectScore;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isRecordUpdatedStatus() {
		return recordUpdatedStatus;
	}
	public void setRecordUpdatedStatus(boolean recordUpdatedStatus) {
		this.recordUpdatedStatus = recordUpdatedStatus;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public String getLastUpdatedByUserName() {
		return lastUpdatedByUserName;
	}
	public void setLastUpdatedByUserName(String lastUpdatedByUserName) {
		this.lastUpdatedByUserName = lastUpdatedByUserName;
	}
	public Long getSchoolEvaluationScoresDetailsId() {
		return schoolEvaluationScoresDetailsId;
	}
	public void setSchoolEvaluationScoresDetailsId(Long schoolEvaluationScoresDetailsId) {
		this.schoolEvaluationScoresDetailsId = schoolEvaluationScoresDetailsId;
	}
	public Long getSchoolEvaluationScoresId() {
		return schoolEvaluationScoresId;
	}
	public void setSchoolEvaluationScoresId(Long schoolEvaluationScoresId) {
		this.schoolEvaluationScoresId = schoolEvaluationScoresId;
	}
	
	
	
}