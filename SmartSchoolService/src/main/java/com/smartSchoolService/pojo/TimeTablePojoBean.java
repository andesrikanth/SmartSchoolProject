package com.smartSchoolService.pojo;

import java.sql.Time;
import java.util.Date;

public class TimeTablePojoBean {
	
	public TimeTablePojoBean(Long id){
		this.id = id;
		/*this.time6 = new Date();*/  
	}
	private Date fromTime;
	private Date toTime;
	private Long id;
	private Date time6; 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getToTime() {
		return toTime;
	}
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	
	
	
}
