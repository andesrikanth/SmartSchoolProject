package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.pojo.TeacherRegisterPojo;

public class UpdateCommonUtil {

	
	public String updateSubjectDetails(SubjectRegisterPojo subjectRegisterPojo){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("UPDATE SUBJECTS_DETAILS SET SUBJECT_NAME= ?, SUBJECT_DESC = ?, LAST_UPDATED_BY = ? ,  LAST_UPDATE_DATE = ?  WHERE SUBJECT_ID = ? ;");
				
				stmt.setString(1, subjectRegisterPojo.getSubjectName());
				stmt.setString(2, subjectRegisterPojo.getSubjectDesc());
				stmt.setString(3, subjectRegisterPojo.getLastUpdatedByUserName());
				stmt.setTimestamp(4, new Timestamp(date.getTime()));
				stmt.setLong(5, subjectRegisterPojo.getKey());
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"subjects_details_subject_name_key\"")){
						status="Same subject already exists. Please enter a different Subject Name";
					}
				}
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status="false";
			if(e!=null && e.getCause()!=null) {
				status=e.getCause().toString();
			}
			
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String updateStudentDetails(StudentPojo studentPojo){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("UPDATE STUDENT_DETAILS SET STUDENT_FIRST_NAME= ?, STUDENT_LAST_NAME = ?, GENDER = ?, FATHER_NAME = ? , MOTHER_NAME = ?, ADDRESS= ?, EMAIL = ?, PHONE_NO=?, SECONDARY_PHONE_NO=?, BRANCH_ID = ?, REGISTERED_STANDARD = ?, REGISTERED_SECTION = ?,  LAST_UPDATED_BY = ? ,  LAST_UPDATE_DATE = ?  WHERE STUDENT_ID = ? ;");
				
				stmt.setString(1, studentPojo.getStudentFirstName());
				stmt.setString(2, studentPojo.getStudentLastName());
				stmt.setString(3, studentPojo.getSelectedStudentGender());
				stmt.setString(4, studentPojo.getStudentFatherName());
				stmt.setString(5, studentPojo.getStudentMotherName());
				stmt.setString(6, studentPojo.getAddress());
				
				if(studentPojo.getStudentEmailNotAvail() == null || (studentPojo.getStudentEmailNotAvail()!= null && studentPojo.getStudentEmailNotAvail().length==0) || (studentPojo.getStudentEmailNotAvail()!= null && studentPojo.getStudentEmailNotAvail().length==0 && !studentPojo.getStudentEmailNotAvail()[0].equals("1"))){
					stmt.setString(7, studentPojo.getStudentEmail());
				}
				else{
					stmt.setString(7, "N");
				}

				
				stmt.setString(8, studentPojo.getPhoneNumber());
				stmt.setString(9, studentPojo.getAlternativePhoneNumber());
				stmt.setLong(10, studentPojo.getSelectedBranchId());
				stmt.setLong(11, studentPojo.getSelectedStandardId());
				stmt.setLong(12, studentPojo.getSelectedSectionId());
				stmt.setString(13, studentPojo.getLastUpdatedBy());
				stmt.setTimestamp(14, new Timestamp(date.getTime()));
				stmt.setLong(15, studentPojo.getKey());
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				/*if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"subjects_details_subject_name_key\"")){
						status="Same student already exists. Please enter a different Student First Name and Last Name";
					}
				}*/
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status="false";
			if(e!=null && e.getCause()!=null) {
				status=e.getCause().toString();
			}
			
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String updateTeacherDetails(TeacherRegisterPojo teacherRegisterPojo){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("UPDATE TEACHER_DETAILS SET  TEACHER_FIRST_NAME=?, TEACHER_LAST_NAME=?, DOB=?, GENDER=?, ADDRESS=?, SPECIALIZATION=?, EMAIL=?, PHONE_NO=?, SECONDARY_PHONE_NO=?, BRANCH_ID=?,LAST_UPDATED_BY = ? ,  LAST_UPDATE_DATE = ? WHERE TEACHER_ID = ? ;");
				
				stmt.setString(1, teacherRegisterPojo.getTeacherFirstName());
				stmt.setString(2, teacherRegisterPojo.getTeacherLastName());
				stmt.setDate(3, new java.sql.Date(teacherRegisterPojo.getDateOfBirth().getTime()));
				stmt.setString(4, teacherRegisterPojo.getTeacherGender());
				stmt.setString(5, teacherRegisterPojo.getAddress());
				stmt.setString(6, teacherRegisterPojo.getSpecialization());
				
				if(teacherRegisterPojo.getTeacherEmailNotAvail() == null || (teacherRegisterPojo.getTeacherEmailNotAvail()!= null && teacherRegisterPojo.getTeacherEmailNotAvail().length==0)|| (teacherRegisterPojo.getTeacherEmailNotAvail()!= null && teacherRegisterPojo.getTeacherEmailNotAvail().length>0 && !teacherRegisterPojo.getTeacherEmailNotAvail()[0].equals("1"))){
					stmt.setString(7, teacherRegisterPojo.getTeacherEmail());
				}
				else{
					stmt.setString(7, "N");
				}
				
				stmt.setString(8, teacherRegisterPojo.getPhoneNumber());
				stmt.setString(9, teacherRegisterPojo.getAlternativePhoneNumber());
				stmt.setLong(10, teacherRegisterPojo.getSelectedBranchId());
				stmt.setString(11, teacherRegisterPojo.getLastUpdatedBy());
				stmt.setTimestamp(12, new Timestamp(date.getTime()));
				stmt.setLong(13, teacherRegisterPojo.getKey());
				
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				/*if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"subjects_details_subject_name_key\"")){
						status="Same subject already exists. Please enter a different Subject Name";
					}
				}*/
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status="false";
			if(e!=null && e.getCause()!=null) {
				status=e.getCause().toString();
			}
			
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String updateBranchDetails(BranchRegisterPojo branchRegisterPojo){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("UPDATE SCHOOL_BRANCHES SET BRANCH_NAME= ?, BRANCH_ADDRESS = ?, LAST_UPDATED_BY = ? ,  LAST_UPDATE_DATE = ?  WHERE BRANCH_ID = ? ;");
				
				stmt.setString(1, branchRegisterPojo.getBranchName());
				stmt.setString(2, branchRegisterPojo.getBranchAddress());
				stmt.setString(3, branchRegisterPojo.getLastUpdatedByUserName());
				stmt.setTimestamp(4, new Timestamp(date.getTime()));
				stmt.setLong(5, branchRegisterPojo.getKey());
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				/*if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"subjects_details_subject_name_key\"")){
						status="Same subject already exists. Please enter a different Subject Name";
					}
				}*/
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status="false";
			if(e!=null && e.getCause()!=null) {
				status=e.getCause().toString();
			}
			
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String updateStandardDetails(StandardRegisterPojo standardRegisterPojo){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("UPDATE CLASS_AVBL_STANDARDS SET STANDARD_NAME= ?, DESCRIPTION = ?, BRANCH_ID=?, LAST_UPDATED_BY = ? ,  LAST_UPDATE_DATE = ?  WHERE STANDARD_ID = ? ;");
				
				stmt.setString(1, standardRegisterPojo.getStandardName());
				stmt.setString(2, standardRegisterPojo.getStandardDesc());
				stmt.setLong(3, standardRegisterPojo.getBranchId());
				stmt.setString(4, standardRegisterPojo.getLastUpdatedByUserName());
				stmt.setTimestamp(5, new Timestamp(date.getTime()));
				stmt.setLong(6, standardRegisterPojo.getKey());
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				/*if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"subjects_details_subject_name_key\"")){
						status="Same subject already exists. Please enter a different Subject Name";
					}
				}*/
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status="false";
			if(e!=null && e.getCause()!=null) {
				status=e.getCause().toString();
			}
			
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String updateSectionDetails(SectionRegisterPojo sectionRegisterPojo){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("UPDATE CLASS_AVBL_SECTIONS SET SECTION_NAME= ?, DESCRIPTION = ?, BRANCH_ID=?, STANDARD_ID=?, LAST_UPDATED_BY = ? ,  LAST_UPDATE_DATE = ?  WHERE STANDARD_ID = ? ;");
				
				stmt.setString(1, sectionRegisterPojo.getSectionName());
				stmt.setString(2, sectionRegisterPojo.getSectionDesc());
				stmt.setLong(3, sectionRegisterPojo.getBranchId());
				stmt.setLong(4, sectionRegisterPojo.getStandardId());
				stmt.setString(5, sectionRegisterPojo.getLastUpdatedByUserName());
				stmt.setTimestamp(6, new Timestamp(date.getTime()));
				stmt.setLong(7, sectionRegisterPojo.getKey());
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				/*if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"subjects_details_subject_name_key\"")){
						status="Same subject already exists. Please enter a different Subject Name";
					}
				}*/
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status="false";
			if(e!=null && e.getCause()!=null) {
				status=e.getCause().toString();
			}
			
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	
}

