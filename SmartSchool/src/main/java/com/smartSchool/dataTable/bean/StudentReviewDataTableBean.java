package com.smartSchool.dataTable.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.smartSchool.dataTable.JsfTableDataModel;
import com.smartSchoolService.pojo.StudentPojo;

@ManagedBean(name="studentDataTable")
@ViewScoped
public class StudentReviewDataTableBean implements Serializable {

	private static final long serialVersionUID = -8965587051588095031L;

	private LazyDataModel<StudentPojo> lazyDataModel;
	private List<StudentPojo> list;
	    
	private StudentPojo selectedStudentRegisterPojo;

	
	 @PostConstruct
	   public void init() {
		   String defaultSelectQuery="select STUDENT_ID,STUDENT_FIRST_NAME, STUDENT_LAST_NAME, GENDER, BRANCH_NAME, STANDARD_NAME, SECTION_NAME from STUDENT_DETAILS st ,SCHOOL_BRANCHES bran,CLASS_AVBL_STANDARDS stand,CLASS_AVBL_SECTIONS sec  where st.BRANCH_ID = bran.BRANCH_ID  and st.REGISTERED_STANDARD = stand.STANDARD_ID  and st.REGISTERED_SECTION = sec.section_id";
		   String defaultCountQuery="select count(*) as range FROM STUDENT_DETAILS";
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
		
		System.out.println("selected Student : "+ this.getSelectedStudentRegisterPojo().getKey());
		
        //FacesMessage msg = new FacesMessage("Student Selected");
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}
