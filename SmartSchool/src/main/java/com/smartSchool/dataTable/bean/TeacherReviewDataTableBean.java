package com.smartSchool.dataTable.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.smartSchool.dataTable.JsfTableDataModel;
import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.TeacherRegisterPojo;

@ManagedBean(name="teacherDataTable")
@ViewScoped
public class TeacherReviewDataTableBean  implements Serializable {

	private static final long serialVersionUID = -8965587051588095031L;

	private LazyDataModel<TeacherRegisterPojo> lazyDataModel;
	private List<TeacherRegisterPojo> list;
	    
	private TeacherRegisterPojo selectedTeacherRegisterPojo;
	private TeacherRegisterPojo backupSelectedTeacherRegisterPojo;
	private boolean teacherUpdateStatus;
	
	@PostConstruct
	   public void init() {
		   String defaultSelectQuery="select TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME, DOB, GENDER, ADDRESS, SPECIALIZATION, EMAIL, PHONE_NO, SECONDARY_PHONE_NO, teach.BRANCH_ID, BRANCH_NAME from TEACHER_DETAILS teach, SCHOOL_BRANCHES bran WHERE teach.BRANCH_ID = bran.BRANCH_ID";
		   String defaultCountQuery="select count(*) as range FROM TEACHER_DETAILS teach ,SCHOOL_BRANCHES bran WHERE teach.BRANCH_ID = bran.BRANCH_ID";
		   //SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		   //int rowCount=smartSchoolFacade.getRowCountForDataTable(defaultCountQuery+";");
		   //list=smartSchoolFacade.getAvailableBranchesListForDataTable(0, 10,defaultSelectQuery);
		   
		   lazyDataModel = new JsfTableDataModel(defaultSelectQuery,defaultCountQuery,"getAvailableTeachersListForDataTable");
	   }

	
	public TeacherRegisterPojo getBackupSelectedTeacherRegisterPojo() {
		return backupSelectedTeacherRegisterPojo;
	}


	public void setBackupSelectedTeacherRegisterPojo(TeacherRegisterPojo backupSelectedTeacherRegisterPojo) {
		this.backupSelectedTeacherRegisterPojo = backupSelectedTeacherRegisterPojo;
	}


	public LazyDataModel<TeacherRegisterPojo> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<TeacherRegisterPojo> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public List<TeacherRegisterPojo> getList() {
		return list;
	}

	public void setList(List<TeacherRegisterPojo> list) {
		this.list = list;
	}

	public TeacherRegisterPojo getSelectedTeacherRegisterPojo() {
		return selectedTeacherRegisterPojo;
	}

	public void setSelectedTeacherRegisterPojo(TeacherRegisterPojo selectedTeacherRegisterPojo) {
		this.selectedTeacherRegisterPojo = selectedTeacherRegisterPojo;
	}

	public boolean isTeacherUpdateStatus() {
		return teacherUpdateStatus;
	}

	public void setTeacherUpdateStatus(boolean teacherUpdateStatus) {
		this.teacherUpdateStatus = teacherUpdateStatus;
	}

	public void onRowSelect(SelectEvent event) {
		
		System.out.println("selected Teacher : "+ this.getBackupSelectedTeacherRegisterPojo().getKey());
		
		try {
			this.setSelectedTeacherRegisterPojo((TeacherRegisterPojo)backupSelectedTeacherRegisterPojo.clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(selectedTeacherRegisterPojo.getAvailableBranches() == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			selectedTeacherRegisterPojo.setAvailableBranches(smartSchoolFacade.getAvailableBranchesList());
			String studEmail  = selectedTeacherRegisterPojo.getTeacherEmail();
			if(studEmail != null && studEmail.equals("N")){
				selectedTeacherRegisterPojo.setTeacherEmail(null);
				String[] str =new String[1];
				str[0]="1";
				selectedTeacherRegisterPojo.setTeacherEmailNotAvail(str);
			}
			
				
		}
		
    }
	
	public void validateTeacherEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null){
			String selectedValue = (String) value;
			if(!selectedValue.contains("@") || !selectedValue.contains(".")){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid email id", "Please enter valid email id"));
			}
		}
		
	}
	
	public void teacherEmailNotAvailValueChange(ValueChangeEvent e){
		if(e.getNewValue() != null){
			selectedTeacherRegisterPojo.setTeacherEmailNotAvail((String[])e.getNewValue());
			if(selectedTeacherRegisterPojo.getTeacherEmailNotAvail().length==0){
				selectedTeacherRegisterPojo.setTeacherEmailNotAvail(null);
			}
		}
		
	}
	
	public void validatePhNo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null){
			String selectedValue = (String) value;
			if(selectedValue.length() !=10){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid Phone Number", "Please enter valid Phone Number"));
			}
			else if (!(selectedValue.matches("[0-9]+") && selectedValue.length() > 2)) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid Phone Number", "Please enter valid Phone Number"));
			}
		}
		
	}
	
	public void validateBranchesLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Branch", "Please select a valid Branch"));
		}
	}

	public void updateTeacher(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		this.getSelectedTeacherRegisterPojo().setLastUpdatedBy(loggedUserName);

		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.updateTeacher(this.getSelectedTeacherRegisterPojo());
		
		if(out !=null && out.equals("true")){
			teacherUpdateStatus=true;
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_INFO, "Teacher Details Updated Successful !", "Info"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Teacher Details Update Failed!! Please contact product support.","Info"));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  out,"Info"));
			}
		}
		
		/*RequestContext context = RequestContext.getCurrentInstance();
		context.update("form");*/
		
	}
	
	public void deleteTeacher(){
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		Long teacherId=this.getSelectedTeacherRegisterPojo().getKey();
		String out=smartSchoolFacade.deleteTeacher(teacherId);
		System.out.println("Teacher Name"+this.getSelectedTeacherRegisterPojo().getTeacherFirstName());
		if(out !=null && out.equals("true")){
			FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Teacher : "+this.getSelectedTeacherRegisterPojo().getTeacherFirstName()+" "+this.getSelectedTeacherRegisterPojo().getTeacherLastName()+" deleted!"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Teacher : "+this.getSelectedTeacherRegisterPojo().getTeacherFirstName()+" "+this.getSelectedTeacherRegisterPojo().getTeacherLastName()+" deletion failed!! Please contact product support."));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", out));
			}
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('teacherDialog').hide();");
		context.update("form:deleteMessage");
		context.update("form");
		
	}
	
	public void closeTeacherDialog(){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('teacherDialog').hide();");
		if(teacherUpdateStatus){
			context.update("form");
		}
		
		teacherUpdateStatus=false;
		
	}

	
}
