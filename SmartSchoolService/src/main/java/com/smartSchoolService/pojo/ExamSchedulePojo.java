package com.smartSchoolService.pojo;

import java.util.Date;

public class ExamSchedulePojo {

	private Long key;
	private Date examStartDate;
	private Date examEndDate;
	private Long standardId;
	private Long sectionId;
	private Long branchId;
	private String examDetails;
	private String createdByUserName;
	private String lastUpdatedByUserName;
	
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public Date getExamStartDate() {
		return examStartDate;
	}
	public void setExamStartDate(Date examStartDate) {
		this.examStartDate = examStartDate;
	}
	public Date getExamEndDate() {
		return examEndDate;
	}
	public void setExamEndDate(Date examEndDate) {
		this.examEndDate = examEndDate;
	}
	public Long getStandardId() {
		return standardId;
	}
	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getExamDetails() {
		return examDetails;
	}
	public void setExamDetails(String examDetails) {
		this.examDetails = examDetails;
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

	
	
	public class ExamScheduleSubjectPojo{
		
		private Long key;
		private Long subjectId;
		private String subjectName;
		private Date subjectExamDate;
		private Date subjectExamStartTime;
		private Date subjectExamEndTime;
		private String createdByUserName;
		private String lastUpdatedByUserName;
		
		
		public Long getKey() {
			return key;
		}
		public void setKey(Long key) {
			this.key = key;
		}
		public String getSubjectName() {
			return subjectName;
		}
		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}
		public Long getSubjectId() {
			return subjectId;
		}
		public void setSubjectId(Long subjectId) {
			this.subjectId = subjectId;
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
		public Date getSubjectExamDate() {
			return subjectExamDate;
		}
		public void setSubjectExamDate(Date subjectExamDate) {
			this.subjectExamDate = subjectExamDate;
		}
		public Date getSubjectExamStartTime() {
			return subjectExamStartTime;
		}
		public void setSubjectExamStartTime(Date subjectExamStartTime) {
			this.subjectExamStartTime = subjectExamStartTime;
		}
		public Date getSubjectExamEndTime() {
			return subjectExamEndTime;
		}
		public void setSubjectExamEndTime(Date subjectExamEndTime) {
			this.subjectExamEndTime = subjectExamEndTime;
		}
		
	}
	
	
}


