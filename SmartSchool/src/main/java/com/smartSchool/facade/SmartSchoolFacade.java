package com.smartSchool.facade;

import com.smartSchoolService.login.LoginHelper;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.util.CommonUtil;

public class SmartSchoolFacade {

	public String validateUserLogin(String userName,String password){
		
		LoginHelper loginHelper =new LoginHelper();
		String status=loginHelper.validateLogin(userName,password);
		return status;
		
	}
	
	public boolean registerStandard(StandardRegisterPojo standardRegisterPojo){
		
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.registerStandardDetails(standardRegisterPojo);
	}
}
