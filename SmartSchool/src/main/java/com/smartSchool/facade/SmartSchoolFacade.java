package com.smartSchool.facade;

import java.util.HashMap;
import java.util.List;

import com.smartSchoolService.login.LoginHelper;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.pojo.TeacherRegisterPojo;
import com.smartSchoolService.util.ChoiceListPojo;
import com.smartSchoolService.util.DeleteCommonUtil;
import com.smartSchoolService.util.FetchCommonUtil;
import com.smartSchoolService.util.RegisterCommonUtil;
import com.smartSchoolService.util.UpdateCommonUtil;

public class SmartSchoolFacade {

	public String submitForgotPassword (String userName, String registeredEmailId, String registeredPhoneNo){
		LoginHelper loginHelper =new LoginHelper();
		String status=loginHelper.submitForgotPassword(userName,registeredEmailId,registeredPhoneNo);
		return status;
	}
	public String validateUserLogin(String userName,String password){
		
		LoginHelper loginHelper =new LoginHelper();
		String status=loginHelper.validateLogin(userName,password);
		return status;
		
	}
	
public String resetUserPassword(String userName,String password){
		
		LoginHelper loginHelper =new LoginHelper();
		String status=loginHelper.resetUserPassword(userName,password);
		return status;
		
	}
	
	public boolean registerStandard(StandardRegisterPojo standardRegisterPojo){
		
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.registerStandardDetails(standardRegisterPojo);
	}
	
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranchesList(){
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.getAvailableBranchesList();
	}
	
	public List<ChoiceListPojo.AvailableStandards> getAvailableStandardsList(Long branchId, Long ignoreStandardId){
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.getAvailableStandardsList(branchId,ignoreStandardId);
	}
	
	public List<ChoiceListPojo.AvailableSections> getAvailableSectionsList(Long selectedBranchId, Long standardId, Long ignoreSectionId){
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.getAvailableSectionsList(selectedBranchId,standardId,ignoreSectionId);
	}
	
	public HashMap<String,String> registerStudent(StudentPojo studentPojo){
		
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.registerStudentDetails(studentPojo);
	}
	
	public boolean registerBranch(BranchRegisterPojo branchRegisterPojo){
		
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.registerBranchDetails(branchRegisterPojo);
	}
	
	public boolean registerSection(SectionRegisterPojo sectionRegisterPojo){
		
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.registerSectionDetails(sectionRegisterPojo);
	}
	
	public HashMap<String,String> registerTeacher(TeacherRegisterPojo teacherRegisterPojo){
		
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.registerTeacherDetails(teacherRegisterPojo);
	}
	
	public String registerSubject(SubjectRegisterPojo subjectRegisterPojo){
		
		RegisterCommonUtil commonUtil =new RegisterCommonUtil();
		return commonUtil.registerSubjectDetails(subjectRegisterPojo);
	}
	
	public List<SubjectRegisterPojo> getAvailableSubjectsListForDataTable(Integer startRow, Integer endRow, String defaultSelectQuery /*query should NOT end with semi-colon ';'*/){
		FetchCommonUtil fetchCommonUtil = new FetchCommonUtil();
		return fetchCommonUtil.getAvailableSubjectsListForDataTable(startRow, endRow,defaultSelectQuery);
	}
	
	public int getRowCountForDataTable(String countQuery /*query must end with semi-colon ';'*/){
		FetchCommonUtil fetchCommonUtil = new FetchCommonUtil();
		return fetchCommonUtil.getRowCountForDataTable(countQuery);
	}
	
	public List<StudentPojo> getAvailableStudentsListForDataTable(Integer startRow, Integer endRow, String defaultSelectQuery /*query should NOT end with semi-colon ';'*/){
		FetchCommonUtil fetchCommonUtil = new FetchCommonUtil();
		return fetchCommonUtil.getAvailableStudentsListForDataTable(startRow, endRow,defaultSelectQuery);
	}
	
	public String updateSubject(SubjectRegisterPojo subjectRegisterPojo){
		UpdateCommonUtil commonUtil =new UpdateCommonUtil();
		return commonUtil.updateSubjectDetails(subjectRegisterPojo);
	}
	
	public String deleteSubject(Long subjectId){
		DeleteCommonUtil commonUtil =new DeleteCommonUtil();
		return commonUtil.deleteSubject(subjectId);
	}
	
	public String deleteStudent(Long studentId){
		DeleteCommonUtil commonUtil =new DeleteCommonUtil();
		return commonUtil.deleteStudent(studentId);
	}
	
	public String updateStudent(StudentPojo studentPojo){
		UpdateCommonUtil commonUtil =new UpdateCommonUtil();
		return commonUtil.updateStudentDetails(studentPojo);
	}
}
