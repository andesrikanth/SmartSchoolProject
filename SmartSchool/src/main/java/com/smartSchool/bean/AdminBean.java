package com.smartSchool.bean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

//http://www.primefaces.org/showcase/ui/menu/menu.xhtml

@ManagedBean(name = "adminBean")
@ViewScoped
public class AdminBean  implements Serializable {

	private MenuModel model;
	
	private String renderUiComponentTarget;
	
	@PostConstruct
    public void init() {
		
		model = new DefaultMenuModel();
        
		//Register submenu
		DefaultSubMenu registerSubmenu = new DefaultSubMenu("Register");
        
        DefaultMenuItem item = new DefaultMenuItem("Branch Register");
        //item.setIcon("ui-icon-disk");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "BranchRegister.xhtml");
        registerSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Standard Register");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "StandardRegister.xhtml");
        registerSubmenu.addElement(item);
         
        item = new DefaultMenuItem("Section Register");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "SectionRegister.xhtml");
        registerSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Subject Register");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "SubjectRegister.xhtml");
        registerSubmenu.addElement(item);
        
        
        item = new DefaultMenuItem("Student Register");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "StudentRegister.xhtml");
        registerSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Teacher Register");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "TeacherRegister.xhtml");
        registerSubmenu.addElement(item);
       
        model.addElement(registerSubmenu);
        
		
		
        DefaultSubMenu reviewSubmenu = new DefaultSubMenu("Review");
         
        item = new DefaultMenuItem("Review Subjects");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewSubjects.xhtml");
        reviewSubmenu.addElement(item);
         
        item = new DefaultMenuItem("Review Students");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewStudents.xhtml");
        reviewSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Review Teachers");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewTeachers.xhtml");
        reviewSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Review Branches");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewBranches.xhtml");
        reviewSubmenu.addElement(item);
        
        model.addElement(reviewSubmenu);
        
      //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Dynamic Actions");
 
        item = new DefaultMenuItem("Save");
        //item.setIcon("ui-icon-home");
        item.setUrl("http://www.primefaces.org");
        item.setParam("targetPageParam", "");
        secondSubmenu.addElement(item);
         
        item = new DefaultMenuItem("Delete");
        //item.setIcon("ui-icon-close");
        item.setCommand("#{menuView.delete}");
        item.setAjax(false);
        item.setParam("targetPageParam", "");
        secondSubmenu.addElement(item);
        
        model.addElement(secondSubmenu);
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	
	public String getRenderUiComponentTarget() {
		return renderUiComponentTarget;
	}

	public void setRenderUiComponentTarget(String renderUiComponentTarget) {
		this.renderUiComponentTarget = renderUiComponentTarget;
	}

	public void selectTargetRenderPage(ActionEvent event) {
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("registerBean");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("branchRegisterBean");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("subjectRegisterBean");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("teacherBean");
		
		Map<String,String> params =	FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Object out=params.get("targetPageParam");
		if(out != null){
			renderUiComponentTarget=out.toString();
		}
		
    }
	
}
