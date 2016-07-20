package com.smartSchool.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "testBean", eager = true)
@SessionScoped
public class TestPojo implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String name="Srikanth";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSayWelcome(){
		//check if null?
		if("".equals(name) || name ==null){
			return "";
		}else{
			return "Ajax message : Welcome " + name;
		}
	}
}
