package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "commonBean", eager = true)
@SessionScoped
public class CommonBean implements Serializable {

	private static final long serialVersionUID = 120L;

	public String loginNavigation() {
		return "login";
	}
	
	/*public void Login(ActionEvent event){
		System.out.println("In Action Listener");
	}*/
	
}
