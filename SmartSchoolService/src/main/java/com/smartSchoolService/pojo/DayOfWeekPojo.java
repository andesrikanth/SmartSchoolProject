package com.smartSchoolService.pojo;

public class DayOfWeekPojo {

	public DayOfWeekPojo(String dayCode, String dayMeaning){
		this.dayCode=dayCode;
		this.dayMeaning=dayMeaning;
	}
	
	private String dayCode;
	private String dayMeaning;
	public String getDayCode() {
		return dayCode;
	}
	public void setDayCode(String dayCode) {
		this.dayCode = dayCode;
	}
	public String getDayMeaning() {
		return dayMeaning;
	}
	public void setDayMeaning(String dayMeaning) {
		this.dayMeaning = dayMeaning;
	}
	
}
