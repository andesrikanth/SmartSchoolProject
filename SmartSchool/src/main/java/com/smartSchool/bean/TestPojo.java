package com.smartSchool.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "testBean", eager = true)
@SessionScoped

public class TestPojo implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String name="Srikanth Test";
	
	public String status() {
		return "page1";
	}
	
	public String status1() {
		System.out.println("hiiii");
		return "page2";
	}
	public String status2() {
		return "Home";
	}

	
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
