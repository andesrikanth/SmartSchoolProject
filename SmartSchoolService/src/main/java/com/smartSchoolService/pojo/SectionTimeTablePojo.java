package com.smartSchoolService.pojo;

import java.sql.Time;

public class SectionTimeTablePojo {

	public SectionTimeTablePojo(Long id){
		this.id = id;
	}
	
	private Long id;
	private Long subjectId;
	private Long teacherId;
	private Time fromTime;
	private Time toTime;
	private String fromTimeString;
	private String toTimeString;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public Time getFromTime() {
		return fromTime;
	}
	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}
	public Time getToTime() {
		return toTime;
	}
	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}
	public String getFromTimeString() {
		return fromTimeString;
	}
	public void setFromTimeString(String fromTimeString) {
		this.fromTimeString = fromTimeString;
	}
	public String getToTimeString() {
		return toTimeString;
	}
	public void setToTimeString(String toTimeString) {
		this.toTimeString = toTimeString;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
