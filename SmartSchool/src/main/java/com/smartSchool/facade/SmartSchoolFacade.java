package com.smartSchool.facade;

import java.util.List;

import com.smartSchoolService.login.LoginHelper;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.util.ChoiceListPojo;
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
	
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranchesList(){
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.getAvailableBranchesList();
	}
	
	public List<ChoiceListPojo.AvailableStandards> getAvailableStandardsList(Long branchId){
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.getAvailableStandardsList(branchId);
	}
	
	public List<ChoiceListPojo.AvailableSections> getAvailableSectionsList(Long standardId){
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.getAvailableSectionsList(standardId);
	}
	
	public boolean registerStudent(StudentPojo studentPojo){
		
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.registerStudentDetails(studentPojo);
	}
}
