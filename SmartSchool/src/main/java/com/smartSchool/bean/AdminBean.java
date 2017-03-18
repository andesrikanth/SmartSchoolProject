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
        
		//<!------------------    Register submenu -----c--->
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
        
        registerSubmenu.setExpanded(true);
        model.addElement(registerSubmenu);
        
		
		//<!-----------------------------    Review  submenu --------------------------->
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

        item = new DefaultMenuItem("Review Standards");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewStandards.xhtml");
        reviewSubmenu.addElement(item);

        item = new DefaultMenuItem("Review Sections");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewSections.xhtml");
        reviewSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Review Section Timetable");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewSections.xhtml");
        reviewSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Review Teacher Timetable");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewSections.xhtml");
        reviewSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Review Fee Defaulters");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "ReviewSections.xhtml");
        reviewSubmenu.addElement(item);
        
        
        reviewSubmenu.setExpanded(false);
        model.addElement(reviewSubmenu);
        
        //<!-----------------------------    Create submenu --------------------------->
        DefaultSubMenu createSubmenu = new DefaultSubMenu("Create");
        
        item = new DefaultMenuItem("Create Timetable Template");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "TimeTableTemplate.xhtml");
        createSubmenu.addElement(item);
       
        item = new DefaultMenuItem("Create Section Timetable");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "SectionTimeTable.xhtml");
        createSubmenu.addElement(item);
       
        item = new DefaultMenuItem("Create Exam Schedule");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "CreateExamSchedule.xhtml");
        createSubmenu.addElement(item);

        item = new DefaultMenuItem("Create Homework");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "CreateHomeWork.xhtml");
        createSubmenu.addElement(item);
        
        
        createSubmenu.setExpanded(false);
        model.addElement(createSubmenu);
        
        
        //Second submenu
        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Actions");
 
        item = new DefaultMenuItem("Evaluation Scores");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "CreateEvaluationScores.xhtml");
        secondSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Student Fee Payments");
        item.setCommand("#{adminBean.selectTargetRenderPage}");
        item.setUpdate("pg1");
        item.setParam("targetPageParam", "TimeTableTemplate.xhtml");
        secondSubmenu.addElement(item);
        
        secondSubmenu.setExpanded(false);
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
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("timeTableTemplateView");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("sectionTimetable");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("homeworkBean");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("examSchedule");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("evaluationScores");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("evaluationScoresDataTable");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("branchDataTable");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("sectionDataTable");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("standardDataTable");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("studentDataTable");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("subjectDataTable");
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("teacherDataTable");
		
		
		Map<String,String> params =	FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Object out=params.get("targetPageParam");
		if(out != null){
			renderUiComponentTarget=out.toString();
		}
		
    }
	
}
