package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.smartSchoolService.dao.DatabaseUtility;

public class DeleteCommonUtil {

	
	public String deleteSubject(Long subjectId){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			//java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("DELETE FROM SUBJECTS_DETAILS WHERE SUBJECT_ID = ? ;");
				stmt.setLong(1, subjectId);
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
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
	
	public String deleteStudent(Long studentId){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			//java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("DELETE FROM STUDENT_DETAILS WHERE STUDENT_ID = ? ;");
				stmt.setLong(1, studentId);
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
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
	
	public String deleteTeacher(Long teacherId){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			//java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("DELETE FROM TEACHER_DETAILS WHERE TEACHER_ID = ? ;");
				stmt.setLong(1, teacherId);
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
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
	
	public String deleteBranch(Long branchId){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			//java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("DELETE FROM SCHOOL_BRANCHES WHERE BRANCH_ID = ? ;");
				stmt.setLong(1, branchId);
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
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
	
	public String deleteStandard(Long standardId){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			//java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("DELETE FROM CLASS_AVBL_STANDARDS WHERE STANDARD_ID = ? ;");
				stmt.setLong(1, standardId);
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
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
	
	public String deleteSection(Long sectionId){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			//java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement("DELETE FROM CLASS_AVBL_SECTIONS WHERE SECTION_ID = ? ;");
				stmt.setLong(1, sectionId);
							
				int out=stmt.executeUpdate();
				
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
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
