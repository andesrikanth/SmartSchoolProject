package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import com.smartSchoolService.dao.DatabaseUtility;
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
				stmt.setDate(4, date);
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
	
}
