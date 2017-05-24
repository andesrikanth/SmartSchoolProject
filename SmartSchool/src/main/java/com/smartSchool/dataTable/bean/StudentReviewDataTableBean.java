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
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name="studentDataTable")
@ViewScoped
public class StudentReviewDataTableBean implements Serializable {

	private static final long serialVersionUID = -8965587051588095031L;

	private LazyDataModel<StudentPojo> lazyDataModel;
	private List<StudentPojo> list;
	    
	private StudentPojo selectedStudentRegisterPojo;
	private StudentPojo backupSelectedStudentRegisterPojo;
	
	private boolean studentUpdateStatus;
	
	 @PostConstruct
	   public void init() {
		   String defaultSelectQuery="select STUDENT_ID,STUDENT_FIRST_NAME, STUDENT_LAST_NAME, ROLL_NO, GENDER, FATHER_NAME, MOTHER_NAME, ADDRESS, EMAIL, PHONE_NO, SECONDARY_PHONE_NO, st.BRANCH_ID, st.REGISTERED_STANDARD, st.REGISTERED_SECTION, BRANCH_NAME, STANDARD_NAME, SECTION_NAME from STUDENT_DETAILS st ,SCHOOL_BRANCHES bran,CLASS_AVBL_STANDARDS stand,CLASS_AVBL_SECTIONS sec  where st.BRANCH_ID = bran.BRANCH_ID  and st.REGISTERED_STANDARD = stand.STANDARD_ID  and st.REGISTERED_SECTION = sec.section_id";
		   String defaultCountQuery="select count(*) as range FROM STUDENT_DETAILS st ,SCHOOL_BRANCHES bran,CLASS_AVBL_STANDARDS stand,CLASS_AVBL_SECTIONS sec  where st.BRANCH_ID = bran.BRANCH_ID  and st.REGISTERED_STANDARD = stand.STANDARD_ID  and st.REGISTERED_SECTION = sec.section_id";
		   //SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		   //int rowCount=smartSchoolFacade.getRowCountForDataTable(defaultCountQuery+";");
		   //list=smartSchoolFacade.getAvailableBranchesListForDataTable(0, 10,defaultSelectQuery);
		   
		   
		   lazyDataModel = new JsfTableDataModel(defaultSelectQuery,defaultCountQuery,"getAvailableStudentsListForDataTable");
	   }

	 
	 
	public LazyDataModel<StudentPojo> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<StudentPojo> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public List<StudentPojo> getList() {
		return list;
	}

	public void setList(List<StudentPojo> list) {
		this.list = list;
	}

	
	
	public StudentPojo getSelectedStudentRegisterPojo() {
		return selectedStudentRegisterPojo;
	}



	public void setSelectedStudentRegisterPojo(StudentPojo selectedStudentRegisterPojo) {
		this.selectedStudentRegisterPojo = selectedStudentRegisterPojo;
	}


	public void onRowSelect(SelectEvent event) {
		
		System.out.println("selected Student : "+ this.getBackupSelectedStudentRegisterPojo().getKey());
		
		try {
			this.setSelectedStudentRegisterPojo((StudentPojo)backupSelectedStudentRegisterPojo.clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(selectedStudentRegisterPojo.getAvailableBranches() == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			selectedStudentRegisterPojo.setAvailableBranches(smartSchoolFacade.getAvailableBranchesList());
			String studEmail  = selectedStudentRegisterPojo.getStudentEmail();
			if(studEmail != null && studEmail.equals("N")){
				selectedStudentRegisterPojo.setStudentEmail(null);
				String[] str =new String[1];
				str[0]="1";
				selectedStudentRegisterPojo.setStudentEmailNotAvail(str);
			}
			/*if(availableBranches!= null && availableBranches.size()>0){
				availableStandards=smartSchoolFacade.getAvailableStandardsList(availableBranches.get(0).getBranchId());
				if(availableStandards!= null && availableStandards.size()>0){
					Long firstStandardId=availableStandards.get(0).getStandardId();
					availableSections=smartSchoolFacade.getAvailableSectionsList(firstStandardId);
				}
				else {
					availableSections=null;
				}
				
			}*/
				
		}
        //FacesMessage msg = new FacesMessage("Student Selected");
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void validateStudentEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null){
			String selectedValue = (String) value;
			if(!selectedValue.contains("@") || !selectedValue.contains(".")){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter valid email id", "Please enter valid email id"));
			}
		}
		
	}
	
	public void studentEmailNotAvailValueChange(ValueChangeEvent e){
		if(e.getNewValue() != null){
			selectedStudentRegisterPojo.setStudentEmailNotAvail((String[])e.getNewValue());
			if(selectedStudentRegisterPojo.getStudentEmailNotAvail().length==0){
				selectedStudentRegisterPojo.setStudentEmailNotAvail(null);
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
	
	
	public boolean isStudentUpdateStatus() {
		return studentUpdateStatus;
	}



	public void setStudentUpdateStatus(boolean studentUpdateStatus) {
		this.studentUpdateStatus = studentUpdateStatus;
	}


	public void branchesLOVChange(ValueChangeEvent e){
		selectedStudentRegisterPojo.setSelectedBranchId((Long)e.getNewValue());
		if(selectedStudentRegisterPojo.getSelectedBranchId() !=null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			List<ChoiceListPojo.AvailableStandards> avblStandards = smartSchoolFacade.getAvailableStandardsList(selectedStudentRegisterPojo.getSelectedBranchId(),selectedStudentRegisterPojo.getSelectedStandardId());
			selectedStudentRegisterPojo.setAvailableStandards(avblStandards);
			
			/*if(avblStandards.size()==0){
				selectedStudentRegisterPojo.setSelectedStandardId(null);
				selectedStudentRegisterPojo.setStandardName(null);
			}
			else {
				selectedStudentRegisterPojo.setSelectedStandardId(avblStandards.get(0).getStandardId());
			}
			*/
			selectedStudentRegisterPojo.setSelectedStandardId(null);
			selectedStudentRegisterPojo.setStandardName(null);
			selectedStudentRegisterPojo.setAvailableSections(null);
			selectedStudentRegisterPojo.setSelectedSectionId(null);
			selectedStudentRegisterPojo.setSectionName(null);
			
			//selectedStandardId=availableStandards.get(0).getStandardId();
			
			/*if(availableStandards!= null && availableStandards.size()>0){
				Long firstStandardId=availableStandards.get(0).getStandardId();
				availableSections=smartSchoolFacade.getAvailableSectionsList(firstStandardId);	
			}
			else {
				availableSections=null;
			}*/
			
		}	
	}
	
	public void standardsLOVChange(ValueChangeEvent e){
		selectedStudentRegisterPojo.setSelectedStandardId((Long)e.getNewValue());
		if(selectedStudentRegisterPojo.getSelectedStandardId() !=null){
    		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
    		List<ChoiceListPojo.AvailableSections> avblSections =smartSchoolFacade.getAvailableSectionsList(selectedStudentRegisterPojo.getSelectedBranchId(),selectedStudentRegisterPojo.getSelectedStandardId(),selectedStudentRegisterPojo.getSelectedSectionId()); 
    		selectedStudentRegisterPojo.setAvailableSections(avblSections);
    		if(avblSections.size() == 0){
    			selectedStudentRegisterPojo.setSelectedSectionId(null);
    			selectedStudentRegisterPojo.setSectionName(null);
    		}
    		else {
    			selectedStudentRegisterPojo.setSelectedSectionId(avblSections.get(0).getSectionId());
    		}
		}
	}
	
	public void validateSectionsLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Section", "Please select a valid Section"));
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
	
	public void updateStudent(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		this.getSelectedStudentRegisterPojo().setLastUpdatedBy(loggedUserName);

		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.updateStudent(this.getSelectedStudentRegisterPojo());
		
		if(out !=null && out.equals("true")){
			studentUpdateStatus=true;
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_INFO, "Student Details Updated Successful !", "Info"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Student Details Update Failed!! Please contact product support.","Info"));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  out,"Info"));
			}
		}
		
		/*RequestContext context = RequestContext.getCurrentInstance();
		context.update("form");*/
		
	}
	
	public void deleteStudent(){
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		Long studentId=this.getSelectedStudentRegisterPojo().getKey();
		String out=smartSchoolFacade.deleteStudent(studentId);
		System.out.println("Student Name"+this.getSelectedStudentRegisterPojo().getStudentFirstName());
		if(out !=null && out.equals("true")){
			FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Student : "+this.getSelectedStudentRegisterPojo().getStudentFirstName()+" "+this.getSelectedStudentRegisterPojo().getStudentLastName()+" deleted!"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Student : "+this.getSelectedStudentRegisterPojo().getStudentFirstName()+" "+this.getSelectedStudentRegisterPojo().getStudentLastName()+" deletion failed!! Please contact product support."));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", out));
			}
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('studentDialog').hide();");
		context.update("form:deleteMessage");
		context.update("form");
		
	}
	
	public void closeStudentDialog(){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('studentDialog').hide();");
		if(studentUpdateStatus){
			context.update("form");
		}
		
		studentUpdateStatus=false;
		
	}



	public StudentPojo getBackupSelectedStudentRegisterPojo() {
		return backupSelectedStudentRegisterPojo;
	}



	public void setBackupSelectedStudentRegisterPojo(StudentPojo backupSelectedStudentRegisterPojo) {
		this.backupSelectedStudentRegisterPojo = backupSelectedStudentRegisterPojo;
	}
	
	
	
}
