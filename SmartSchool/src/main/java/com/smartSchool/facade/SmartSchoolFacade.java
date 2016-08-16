package com.smartSchool.facade;

import java.util.HashMap;
import java.util.List;

import com.smartSchoolService.login.LoginHelper;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.TeacherRegisterPojo;
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
	
	public List<ChoiceListPojo.AvailableSections> getAvailableSectionsList(Long selectedBranchId, Long standardId){
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.getAvailableSectionsList(selectedBranchId,standardId);
	}
	
	public HashMap<String,String> registerStudent(StudentPojo studentPojo){
		
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.registerStudentDetails(studentPojo);
	}
	
	public boolean registerBranch(BranchRegisterPojo branchRegisterPojo){
		
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.registerBranchDetails(branchRegisterPojo);
	}
	
	public boolean registerSection(SectionRegisterPojo sectionRegisterPojo){
		
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.registerSectionDetails(sectionRegisterPojo);
	}
	
	public HashMap<String,String> registerTeacher(TeacherRegisterPojo teacherRegisterPojo){
		
		CommonUtil commonUtil =new CommonUtil();
		return commonUtil.registerTeacherDetails(teacherRegisterPojo);
	}
	
}
