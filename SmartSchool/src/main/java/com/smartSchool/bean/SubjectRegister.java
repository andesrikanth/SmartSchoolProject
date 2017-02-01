package com.smartSchool.bean;

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

import com.smartSchool.facade.SmartSchoolFacade;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;

@ManagedBean(name = "subjectRegisterBean", eager = true)
@ViewScoped
public class SubjectRegister  implements Serializable {

	private static final long serialVersionUID = 200L;
	
	private String subjectName;
	private String subjectDesc;
	private boolean subjectRegisterStatus;
	private Long selectedBranchId;
	public String[] subjectType;
	private List<ChoiceListPojo.AvailableBranches> availableBranches;
	
	//This method is the first method called when a jsf page is loaded. Since it is ViewScoped, the bean/data will pertian only for this jsf page. 
	@PostConstruct
	public void init(){
		if(availableBranches == null){
			SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
			availableBranches=smartSchoolFacade.getAvailableBranchesList();
		}
	}	
	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public String getSubjectDesc() {
		return subjectDesc;
	}


	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}


	public boolean isSubjectRegisterStatus() {
		return subjectRegisterStatus;
	}


	public void setSubjectRegisterStatus(boolean subjectRegisterStatus) {
		this.subjectRegisterStatus = subjectRegisterStatus;
	}


	public List<ChoiceListPojo.AvailableBranches> getAvailableBranches() {
		return availableBranches;
	}
	public void setAvailableBranches(List<ChoiceListPojo.AvailableBranches> availableBranches) {
		this.availableBranches = availableBranches;
	}
	
	
	
	public Long getSelectedBranchId() {
		return selectedBranchId;
	}
	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
	}
	
	public void validateBranchesLOV(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value ==null || (Long)value == 0){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid Branch", "Please select a valid Branch"));
		}
	}
	
	public void subjectTypeValueChange(ValueChangeEvent e){
		if(e.getNewValue() != null){
			subjectType = (String[])e.getNewValue();
			if(subjectType.length==0){
				subjectType=null;
			}
			
		}
		
	}
	
	public String[] getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String[] subjectType) {
		this.subjectType = subjectType;
	}
	
	public String registerSubject(){
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		String loggedUserName=(String)session.getAttribute("cur_user_name");
		SubjectRegisterPojo subjectRegisterPojo = new SubjectRegisterPojo();
		
		subjectRegisterPojo.setSubjectName(subjectName);
		subjectRegisterPojo.setSubjectDesc(subjectDesc);
		if(subjectType != null && subjectType.length>0){
			subjectRegisterPojo.setSubjectType(subjectType[0]);
		}
		else {
			subjectRegisterPojo.setSubjectType("NORMAL");
		}
		
		subjectRegisterPojo.setSelectedBranchId(selectedBranchId);
		subjectRegisterPojo.setCreatedByUserName(loggedUserName);
		subjectRegisterPojo.setLastUpdatedByUserName(loggedUserName);
		
		SmartSchoolFacade smartSchoolFacade = new SmartSchoolFacade();
		String out=smartSchoolFacade.registerSubject(subjectRegisterPojo);
		if(out !=null && out.equals("true")){
			subjectRegisterStatus=true;
			FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Subject Registration Successful !"));
		}
		else {
			subjectRegisterStatus=false;
			if(out !=null && out.equals("false")){
				// Show default failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Subject Registration Failed! Please contact product support."));
			}
			else {
				// Show custom failure message.
				FacesContext.getCurrentInstance().addMessage("register", new FacesMessage(out));
			}
		}
		
		
		return null;
 }
}