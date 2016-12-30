package com.smartSchool.timeTable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.TimeTablePojoBean;
 
@ManagedBean(name = "timeTableTemplateView")
@ViewScoped
public class TimeTableGridViewBean implements Serializable {
     
    private static final long serialVersionUID = -897417127438276196L;

	private List<TimeTablePojoBean> timeTableBean;
     
    private boolean validationStatus=false;
    private String templateName;

     
    @PostConstruct
    public void init() {
    	timeTableBean = new ArrayList<TimeTablePojoBean>();
        for(int i = 0 ; i < 24 ; i++) {
        	timeTableBean.add(new TimeTablePojoBean(new Long(i+1)));
        }
    	
    }


	public List<TimeTablePojoBean> getTimeTableBean() {
		return timeTableBean;
	}


	public void setTimeTableBean(List<TimeTablePojoBean> timeTableBean) {
		this.timeTableBean = timeTableBean;
	}


	public int validateTimeTableTemplate(){
		int noOfBlockedSlots=0;
		//Below variable is used for comparison to make sure that all dates are given incremental order. So that all timeslots will increase incrementally.
		Date maxDate=null;
		boolean nullTimes=false;
		for(int i=0;i<timeTableBean.size();i++){
			TimeTablePojoBean timeTablePojoBean = this.timeTableBean.get(i);
			if(timeTablePojoBean != null){
				
				//Below is the validation to make sure the Start Time and End Time both are not null.
				if(i==0 && timeTablePojoBean.getFromTime() == null && timeTablePojoBean.getToTime() == null ){
					validationStatus=false;
					FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . There should be atleast one slot defined for creating a Timetable Template. Please provide valid start and end time in slot-1","Info"));
					break;
				}
				//Below is the validation to make sure the Start Time is not null when End Time is provided.
				else if(timeTablePojoBean.getFromTime() == null && timeTablePojoBean.getToTime() != null ){
					validationStatus=false;
					FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . Start Time cannot be null when End Time is defined. Either provide both the Start & End time. Or remove the incorrect End time entry.","Info"));
					break;
				}
				//Below is the validation to make sure the End Time is not null when Start Time is provided.
				else if(timeTablePojoBean.getFromTime() != null && timeTablePojoBean.getToTime() == null ){
					validationStatus=false;
					FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . End Time cannot be null when Start Time is defined. Either provide both the Start & End time. Or remove the incorrect Start time entry.","Info"));
					break;
				}
				
				//Below validation is used for comparison to make sure that all dates are given incremental order. So that all timeslots will increase incrementally.
				else if (maxDate != null && timeTablePojoBean.getFromTime()!= null && timeTablePojoBean.getFromTime().compareTo(maxDate)<=0){
					validationStatus=false;
					FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . Start Time in Slot-"+(i+1)+" is less than End Time in Slot-"+i+". Slots should be created in incremental order of time.","Info"));
					break;
				}
				
				//Below validation is used for comparison to make sure that no time slot is having blank values, where as the next slot to it is assigned with timings. All dates should be incremental order.
				else if (nullTimes && timeTablePojoBean.getFromTime() != null && timeTablePojoBean.getToTime() != null){
					validationStatus=false;
					FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i)+" . Start Time & End Time in Slot-"+(i)+" are blank. Please provide valid values in Slot-"+i+" and then proceed with defining other time slots.","Info"));
					break;
				}
				
				//Below condition is to flag that a complete blank timeslot is present and any timeslots after the current slot shouldn't have values.
				else if(timeTablePojoBean.getFromTime() == null && timeTablePojoBean.getToTime() == null){
					nullTimes=true;
				}
				
				else if(timeTablePojoBean.getFromTime() != null && timeTablePojoBean.getToTime() != null ){
					int compare= timeTablePojoBean.getFromTime().compareTo(timeTablePojoBean.getToTime());
					//Below is the validation to make sure the Start Time is not greater than End Time.
					if (compare > 0){
						validationStatus=false;
						FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . Start Time cannot be greater than End Time.","Info"));
						break;
					}
					//Below is the validation to make sure the Start Time is not equal to End Time.
					else if( compare ==0){
						validationStatus=false;
						FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Validation Failed! Please correct the entry in Slot-"+(i+1)+" . Start Time cannot be equal to End Time.","Info"));
						break;
					}
					else if( compare <0){
						validationStatus=true;
						maxDate=timeTablePojoBean.getToTime();
						noOfBlockedSlots++;
					}
					
				}
			}
			
		}
		
		return noOfBlockedSlots;
		
	}


	public boolean isValidationStatus() {
		return validationStatus;
	}


	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
	}
	
	public String createTimeTableTemplate(){
		validationStatus=false;
		int noOfBlockedSlots =validateTimeTableTemplate();
		if(validationStatus){
			HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			String loggedUserName=(String)session.getAttribute("cur_user_name");
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			String status = smartSchoolFacade.createTimeTableTemplate(timeTableBean, noOfBlockedSlots, loggedUserName,templateName);
			if(status!=null && status.equals("true")){
				FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_INFO,  "Validation Successful! Timetable Template is now created in the system.","Info"));
				
			}
			else if(status!=null && status.equals("false")){
				validationStatus=false;
				FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Timetable Template creation Failed! Please contact product support.","Info"));
			}
			else {
				validationStatus=false;
				FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_ERROR,  status,"Info"));
			}
			
		}
		//FacesContext.getCurrentInstance().addMessage("create", new FacesMessage(FacesMessage.SEVERITY_INFO,  "Validation Successful! Timetable Template is now created in the system.","Info"));
		return null;
	}


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	
	
	
}