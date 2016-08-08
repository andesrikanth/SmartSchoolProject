package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "commonBean", eager = true)
@SessionScoped
public class CommonBean implements Serializable {

	private static final long serialVersionUID = 120L;
	
			
	/*public void Login(ActionEvent event){
		System.out.println("In Action Listener");
	}*/
	
	public String sessionStatusCheck() {
		String status=null;
		HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		if(session == null){
			status="return_home";
		}
		else {
			try{
				String cur_user_name=(String)session.getAttribute("cur_user_name");
				if(cur_user_name == null){
					status="return_home";
				}
				else{
					status=null;
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
				status="return_home";
			}
			
		}
		System.out.println("Session Status Check :: "+status);
		return status;
		
	}
}
