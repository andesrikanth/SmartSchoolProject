package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;

public class FetchCommonUtil {

	
	public List<StudentPojo> getAvailableStudentsListForDataTable(int startRow, int endRow, String defaultQuery){
		List<StudentPojo> availableStudents = new ArrayList<StudentPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(defaultQuery+" limit "+endRow+" OFFSET "+startRow+";");
		        while(rs.next()){
		        	StudentPojo studentPojo = new StudentPojo();
		        	
		        	studentPojo.setKey(rs.getLong("STUDENT_ID"));
		        	studentPojo.setStudentFirstName(rs.getString("STUDENT_FIRST_NAME"));
		        	studentPojo.setStudentLastName(rs.getString("STUDENT_LAST_NAME"));
		        	String gen = rs.getString("GENDER");
		        	if(gen.equals("M")){
		        		studentPojo.setSelectedStudentGender("Male");
		        	}
		        	else {
		        		studentPojo.setSelectedStudentGender("Female");
		        	}
		        	studentPojo.setBranchName(rs.getString("BRANCH_NAME"));
		        	studentPojo.setStandardName(rs.getString("STANDARD_NAME"));
		        	studentPojo.setSectionName(rs.getString("SECTION_NAME"));
		        	
		        	availableStudents.add(studentPojo);
		        }
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return availableStudents;
	}
	
	public List<SubjectRegisterPojo> getAvailableSubjectsListForDataTable(int startRow, int endRow, String defaultQuery){
		List<SubjectRegisterPojo> availableSubjects = new ArrayList<SubjectRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(defaultQuery+" limit "+endRow+" OFFSET "+startRow+";");
		        while(rs.next()){
		        	SubjectRegisterPojo subjectRegisterPojo = new SubjectRegisterPojo();
		        	subjectRegisterPojo.setKey(rs.getLong("SUBJECT_ID"));
		        	subjectRegisterPojo.setSubjectName(rs.getString("SUBJECT_NAME"));
		        	subjectRegisterPojo.setSubjectDesc(rs.getString("SUBJECT_DESC"));
		        	availableSubjects.add(subjectRegisterPojo);
		        }
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return availableSubjects;
	}
	
	
	public int getRowCountForDataTable(String countQuery){
		int count =0; 
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(countQuery);
		        while(rs.next()){
		        	Long countLong=rs.getLong(1);
		        	if(countLong != null){
		        		count=countLong.intValue();
		        	}
		        }
		        con.commit();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
}
