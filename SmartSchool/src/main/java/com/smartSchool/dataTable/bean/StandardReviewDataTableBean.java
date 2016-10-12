package com.smartSchool.dataTable.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.smartSchool.dataTable.JsfTableDataModel;
import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.StandardRegisterPojo;

@ManagedBean(name="standardDataTable")
@ViewScoped
public class StandardReviewDataTableBean   implements Serializable {
    
	private static final long serialVersionUID = 730746309023128959L;
	
	private LazyDataModel<StandardRegisterPojo> lazyDataModel;
	private List<StandardRegisterPojo> list;
	    
	private StandardRegisterPojo selectedStandardRegisterPojo;
	private boolean standardUpdateStatus;
	
	@PostConstruct
	   public void init() {
		   
		standardUpdateStatus=false;
		   
		   String defaultSelectQuery="select STANDARD_ID , STANDARD_NAME, std.DESCRIPTION, std.BRANCH_ID, bran.BRANCH_NAME FROM CLASS_AVBL_STANDARDS std, SCHOOL_BRANCHES bran WHERE std.BRANCH_ID = bran.BRANCH_ID";
		   
		   String defaultCountQuery="select count(*) as range FROM CLASS_AVBL_STANDARDS";
		   //SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		   //int rowCount=smartSchoolFacade.getRowCountForDataTable(defaultCountQuery+";");
		   //list=smartSchoolFacade.getAvailableBranchesListForDataTable(0, 10,defaultSelectQuery);
		   lazyDataModel = new JsfTableDataModel(defaultSelectQuery,defaultCountQuery,"getAvailableStandardsListForDataTable");
	   }

	public LazyDataModel<StandardRegisterPojo> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<StandardRegisterPojo> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public List<StandardRegisterPojo> getList() {
		return list;
	}

	public void setList(List<StandardRegisterPojo> list) {
		this.list = list;
	}

	public StandardRegisterPojo getSelectedStandardRegisterPojo() {
		return selectedStandardRegisterPojo;
	}

	public void setSelectedStandardRegisterPojo(StandardRegisterPojo selectedStandardRegisterPojo) {
		this.selectedStandardRegisterPojo = selectedStandardRegisterPojo;
	}

	public boolean isStandardUpdateStatus() {
		return standardUpdateStatus;
	}

	public void setStandardUpdateStatus(boolean standardUpdateStatus) {
		this.standardUpdateStatus = standardUpdateStatus;
	}
	
	public void onRowSelect(SelectEvent event) {
		
		System.out.println("selected Standard : "+ this.getSelectedStandardRegisterPojo().getKey());
		
		if(selectedStandardRegisterPojo.getAvailableBranches() == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			selectedStandardRegisterPojo.setAvailableBranches(smartSchoolFacade.getAvailableBranchesList());
		}
    }

	public void updateStandard(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		this.getSelectedStandardRegisterPojo().setLastUpdatedByUserName(loggedUserName);

		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.updateStandard(this.getSelectedStandardRegisterPojo());
		
		if(out !=null && out.equals("true")){
			standardUpdateStatus=true;
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_INFO, "Standard Details Updated Successful !", "Info"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Standard Details Update Failed!! Please contact product support.","Info"));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  out,"Info"));
			}
		}
		
		/*RequestContext context = RequestContext.getCurrentInstance();
		context.update("form");*/
		
	}
	
	public void deleteStandard(){
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		Long subjectId=this.getSelectedStandardRegisterPojo().getKey();
		String out=smartSchoolFacade.deleteSubject(subjectId);
		System.out.println("Standard Name"+this.getSelectedStandardRegisterPojo().getStandardName());
		if(out !=null && out.equals("true")){
			FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Standard : "+this.getSelectedStandardRegisterPojo().getStandardName()+" deleted!"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Standard : "+this.getSelectedStandardRegisterPojo().getStandardName()+" deletion failed!! Please contact product support."));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", out));
			}
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('standardDialog').hide();");
		context.update("form:deleteMessage");
		context.update("form");
		
	}
	
	public void closeStandardDialog(){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('standardDialog').hide();");
		if(standardUpdateStatus){
			context.update("form");
		}
		
		standardUpdateStatus=false;
		
	}
	
public void validateBranchesLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Branch", "Please select a valid Branch"));
		}
	}
	
}
