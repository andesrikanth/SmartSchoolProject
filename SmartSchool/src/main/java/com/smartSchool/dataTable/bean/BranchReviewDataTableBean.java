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
import org.primefaces.model.LazyDataModel;

import com.smartSchool.dataTable.JsfTableDataModel;
import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name="branchDataTable")
@ViewScoped
public class BranchReviewDataTableBean  implements Serializable {
    
	private static final long serialVersionUID = 730746309023128959L;
	
	private LazyDataModel<BranchRegisterPojo> lazyDataModel;
	private List<BranchRegisterPojo> list;
	    
	private BranchRegisterPojo selectedBranchRegisterPojo;
	private boolean branchUpdateStatus;
	
	
	 @PostConstruct
	   public void init() {
		   
		 branchUpdateStatus=false;
		   
		   String defaultSelectQuery="select BRANCH_ID , BRANCH_NAME, BRANCH_ADDRESS FROM SCHOOL_BRANCHES";
		   String defaultCountQuery="select count(*) as range FROM SCHOOL_BRANCHES";
		   //SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		   //int rowCount=smartSchoolFacade.getRowCountForDataTable(defaultCountQuery+";");
		   //list=smartSchoolFacade.getAvailableBranchesListForDataTable(0, 10,defaultSelectQuery);
		   lazyDataModel = new JsfTableDataModel(defaultSelectQuery,defaultCountQuery,"getAvailableBranchesListForDataTable");
	   }
	 
	public LazyDataModel<BranchRegisterPojo> getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyDataModel<BranchRegisterPojo> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	public List<BranchRegisterPojo> getList() {
		return list;
	}
	public void setList(List<BranchRegisterPojo> list) {
		this.list = list;
	}
	public BranchRegisterPojo getSelectedBranchRegisterPojo() {
		return selectedBranchRegisterPojo;
	}
	public void setSelectedBranchRegisterPojo(BranchRegisterPojo selectedBranchRegisterPojo) {
		this.selectedBranchRegisterPojo = selectedBranchRegisterPojo;
	}
	public boolean isBranchUpdateStatus() {
		return branchUpdateStatus;
	}
	public void setBranchUpdateStatus(boolean branchUpdateStatus) {
		this.branchUpdateStatus = branchUpdateStatus;
	}
	
	public void updateBranch(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		this.getSelectedBranchRegisterPojo().setLastUpdatedByUserName(loggedUserName);

		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.updateBranch(this.getSelectedBranchRegisterPojo());
		
		if(out !=null && out.equals("true")){
			branchUpdateStatus=true;
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_INFO, "Branch Details Updated Successful !", "Info"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Branch Details Update Failed!! Please contact product support.","Info"));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  out,"Info"));
			}
		}
		
		/*RequestContext context = RequestContext.getCurrentInstance();
		context.update("form");*/
		
	}
	
	public void deleteBranch(){
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		Long branchId=this.getSelectedBranchRegisterPojo().getKey();
		String out=smartSchoolFacade.deleteBranch(branchId);
		System.out.println("Branch Name"+this.getSelectedBranchRegisterPojo().getBranchName());
		if(out !=null && out.equals("true")){
			FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Branch : "+this.getSelectedBranchRegisterPojo().getBranchName()+" deleted!"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Branch : "+this.getSelectedBranchRegisterPojo().getBranchName()+" deletion failed!! Please contact product support."));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", out));
			}
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('branchDialog').hide();");
		context.update("form:deleteMessage");
		context.update("form");
		
	}
	
	public void closeBranchDialog(){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('branchDialog').hide();");
		if(branchUpdateStatus){
			context.update("form");
		}
		
		branchUpdateStatus=false;
		
	}


}
