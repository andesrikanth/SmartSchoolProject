package com.smartSchool.dataTable.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.smartSchool.dataTable.JsfTableDataModel;
import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.SubjectRegisterPojo;

@ManagedBean(name="subjectDataTable")
@ViewScoped
public class SubjectReviewDataTableBean implements Serializable {
    
	private static final long serialVersionUID = 730746309023128959L;
	
	private LazyDataModel<SubjectRegisterPojo> lazyDataModel;
	private List<SubjectRegisterPojo> list;
	    
	private SubjectRegisterPojo selectedSubjectRegisterPojo;
	
	
	
   
   @PostConstruct
   public void init() {
	   String defaultSelectQuery="select SUBJECT_ID , SUBJECT_NAME, SUBJECT_DESC FROM SUBJECTS_DETAILS";
	   String defaultCountQuery="select count(*) as range FROM SUBJECTS_DETAILS";
	   //SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
	   //int rowCount=smartSchoolFacade.getRowCountForDataTable(defaultCountQuery+";");
	   //list=smartSchoolFacade.getAvailableBranchesListForDataTable(0, 10,defaultSelectQuery);
	   lazyDataModel = new JsfTableDataModel(defaultSelectQuery,defaultCountQuery,"getAvailableSubjectsListForDataTable");
   }

	
	public LazyDataModel<SubjectRegisterPojo> getLazyDataModel() {
		return lazyDataModel;
	}
	
	
	public void setLazyDataModel(LazyDataModel<SubjectRegisterPojo> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
	
	public List<SubjectRegisterPojo> getList() {
		return list;
	}
	
	
	public void setList(List<SubjectRegisterPojo> list) {
		this.list = list;
	}


	public SubjectRegisterPojo getSelectedSubjectRegisterPojo() {
		return selectedSubjectRegisterPojo;
	}
	
	public void setSelectedSubjectRegisterPojo(SubjectRegisterPojo selectedSubjectRegisterPojo) {
		this.selectedSubjectRegisterPojo = selectedSubjectRegisterPojo;
	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("selected Subject : "+ this.getSelectedSubjectRegisterPojo().getKey());
		
		//FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":form:test1");
		//RequestContext context = RequestContext.getCurrentInstance();
		//context.update("form:subjectDetail");
		//context.execute("PF('subjectDialog').show();");
		//context.execute("subjectDialog.show()");
		
		//FacesMessage msg = new FacesMessage("Subject Selected", ((SubjectRegisterPojo) event.getObject()).getKey().toString());
		//FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
		
	public void updateSubject(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		this.getSelectedSubjectRegisterPojo().setLastUpdatedByUserName(loggedUserName);

		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.updateSubject(this.getSelectedSubjectRegisterPojo());
		
		if(out !=null && out.equals("true")){
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_INFO, "Subject Details Updated Successful !", "Info"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Subject Details Update Failed!! Please contact product support.","Info"));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  out,"Info"));
			}
		}
		
	}
	
	public void deleteSubject(){
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		Long subjectId=this.getSelectedSubjectRegisterPojo().getKey();
		String out=smartSchoolFacade.deleteSubject(subjectId);
		
		if(out !=null && out.equals("true")){
			FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Subject : "+subjectId+" deleted successfully !", "Info"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Subject : "+subjectId+" deletion failed!! Please contact product support.","Info"));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR,  out,"Info"));
			}
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('subjectDialog').hide();");
		context.update("form:deleteMessage");
		context.update("form");
		
	}
	
	
}
