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
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name="sectionDataTable")
@ViewScoped
public class SectionReviewDataTableBean  implements Serializable {
    
	private static final long serialVersionUID = 730746309023128959L;
	
	private LazyDataModel<SectionRegisterPojo> lazyDataModel;
	private List<SectionRegisterPojo> list;
	    
	private SectionRegisterPojo selectedSectionRegisterPojo;
	private SectionRegisterPojo backupSelectedSectionRegisterPojo;
	private boolean sectionUpdateStatus;
	
	
	 @PostConstruct
	   public void init() {
		   
		 sectionUpdateStatus=false;
		   
		   String defaultSelectQuery="select SECTION_ID , SECTION_NAME, sec.BRANCH_ID, sec.DESCRIPTION, bran.BRANCH_NAME, sec.STANDARD_ID, std.STANDARD_NAME FROM CLASS_AVBL_SECTIONS sec, CLASS_AVBL_STANDARDS std, SCHOOL_BRANCHES bran WHERE sec.BRANCH_ID = bran.BRANCH_ID AND sec.STANDARD_ID = std.STANDARD_ID AND sec.ACTIVE_FLAG='Y'  ";
		   
		   String defaultCountQuery="select count(*) as range FROM CLASS_AVBL_SECTIONS  WHERE ACTIVE_FLAG='Y'";
		   //SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		   //int rowCount=smartSchoolFacade.getRowCountForDataTable(defaultCountQuery+";");
		   //list=smartSchoolFacade.getAvailableBranchesListForDataTable(0, 10,defaultSelectQuery);
		   lazyDataModel = new JsfTableDataModel(defaultSelectQuery,defaultCountQuery,"getAvailableSectionsListForDataTable");
	   }
	 
	 
	public SectionRegisterPojo getBackupSelectedSectionRegisterPojo() {
		return backupSelectedSectionRegisterPojo;
	}

	public void setBackupSelectedSectionRegisterPojo(SectionRegisterPojo backupSelectedSectionRegisterPojo) {
		this.backupSelectedSectionRegisterPojo = backupSelectedSectionRegisterPojo;
	}

	public LazyDataModel<SectionRegisterPojo> getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyDataModel<SectionRegisterPojo> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	public List<SectionRegisterPojo> getList() {
		return list;
	}
	public void setList(List<SectionRegisterPojo> list) {
		this.list = list;
	}
	public SectionRegisterPojo getSelectedSectionRegisterPojo() {
		return selectedSectionRegisterPojo;
	}
	public void setSelectedSectionRegisterPojo(SectionRegisterPojo selectedSectionRegisterPojo) {
		this.selectedSectionRegisterPojo = selectedSectionRegisterPojo;
	}
	public boolean isSectionUpdateStatus() {
		return sectionUpdateStatus;
	}
	public void setSectionUpdateStatus(boolean sectionUpdateStatus) {
		this.sectionUpdateStatus = sectionUpdateStatus;
	}
	
	public void onRowSelect(SelectEvent event) {
		
		System.out.println("selected Section : "+ this.getBackupSelectedSectionRegisterPojo().getKey());
		
		try {
			this.setSelectedSectionRegisterPojo((SectionRegisterPojo)backupSelectedSectionRegisterPojo.clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(selectedSectionRegisterPojo.getAvailableBranches() == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			selectedSectionRegisterPojo.setAvailableBranches(smartSchoolFacade.getAvailableBranchesList());
		}
    }
	
	public void updateSection(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		
		this.getSelectedSectionRegisterPojo().setLastUpdatedByUserName(loggedUserName);

		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.updateSection(this.getSelectedSectionRegisterPojo());
		
		if(out !=null && out.equals("true")){
			sectionUpdateStatus=true;
			FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_INFO, "Section Details Updated Successful !", "Info"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Section Details Update Failed!! Please contact product support.","Info"));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("save", new FacesMessage(FacesMessage.SEVERITY_ERROR,  out,"Info"));
			}
		}
		
		/*RequestContext context = RequestContext.getCurrentInstance();
		context.update("form");*/
		
	}
	
	public void deleteSection(){
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		Long sectionId=this.getSelectedSectionRegisterPojo().getKey();
		String out=smartSchoolFacade.deleteSection(sectionId);
		System.out.println("Section Name"+this.getSelectedSectionRegisterPojo().getSectionName());
		if(out !=null && out.equals("true")){
			FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful", "Section : "+this.getSelectedSectionRegisterPojo().getSectionName()+" deleted!"));
		}
		else {
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", "Section : "+this.getSelectedSectionRegisterPojo().getSectionName()+" deletion failed!! Please contact product support."));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("deleteConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed", out));
			}
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('sectionDialog').hide();");
		context.update("form:deleteMessage");
		context.update("form");
		
	}
	
	public void closeSectionDialog(){
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('sectionDialog').hide();");
		if(sectionUpdateStatus){
			context.update("form");
		}
		
		sectionUpdateStatus=false;
		
	}

	public void branchesLOVChange(ValueChangeEvent e){
		selectedSectionRegisterPojo.setBranchId((Long)e.getNewValue());
		if(selectedSectionRegisterPojo.getBranchId() !=null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			List<ChoiceListPojo.AvailableStandards> avblStandards = smartSchoolFacade.getAvailableStandardsList(selectedSectionRegisterPojo.getBranchId(),selectedSectionRegisterPojo.getStandardId());
			selectedSectionRegisterPojo.setAvailableStandards(avblStandards);
			if(avblStandards.size()==0){
				selectedSectionRegisterPojo.setStandardId(null);
				selectedSectionRegisterPojo.setStandardName(null);
			}
			else {
				selectedSectionRegisterPojo.setStandardId(avblStandards.get(0).getStandardId());
			}

		}	
	}
	
	public void validateStandardsLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Standard", "Please select a valid Standard"));
		}
	}
	
	public void validateBranchesLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Branch", "Please select a valid Branch"));
		}
	}

}
