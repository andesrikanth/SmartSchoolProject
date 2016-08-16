package com.smartSchoolService.util;

public class ChoiceListPojo {
	
	public class AvailableBranches{
		private String branchName;
		private Long branchId;
		
		public String getBranchName() {
			return branchName;
		}
		public void setBranchName(String branchName) {
			this.branchName = branchName;
		}
		public Long getBranchId() {
			return branchId;
		}
		public void setBranchId(Long branchId) {
			this.branchId = branchId;
		}
		
		
		
	}
	public class AvailableStandards{
		private String standardName;
		private Long standardId;
		
		public String getStandardName() {
			return standardName;
		}
		public void setStandardName(String standardName) {
			this.standardName = standardName;
		}
		public Long getStandardId() {
			return standardId;
		}
		public void setStandardId(Long standardId) {
			this.standardId = standardId;
		}
	}
	public class AvailableSections{
		private String sectionName;
		private Long sectionId;
		public String getSectionName() {
			return sectionName;
		}
		public void setSectionName(String sectionName) {
			this.sectionName = sectionName;
		}
		public Long getSectionId() {
			return sectionId;
		}
		public void setSectionId(Long sectionId) {
			this.sectionId = sectionId;
		}
		
	}
	
}
