package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;

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
				stmt.setString(7, studentPojo.getStudentEmail());
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
	
}
