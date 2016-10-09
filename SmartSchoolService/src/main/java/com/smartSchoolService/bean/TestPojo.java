package com.smartSchoolService.bean;

import java.io.Serializable;

//@ManagedBean(name = "testServiceBean", eager = true)
//@SessionScoped
public class TestPojo implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String name="Srikanth Service Test";

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

