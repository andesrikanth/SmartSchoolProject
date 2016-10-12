package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.pojo.TeacherRegisterPojo;

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
		        	studentPojo.setStudentFatherName(rs.getString("FATHER_NAME"));
		        	studentPojo.setStudentMotherName(rs.getString("MOTHER_NAME"));
		        	studentPojo.setAddress(rs.getString("ADDRESS"));
		        	studentPojo.setStudentEmail(rs.getString("EMAIL"));
		        	studentPojo.setPhoneNumber(rs.getString("PHONE_NO"));
		        	studentPojo.setAlternativePhoneNumber(rs.getString("SECONDARY_PHONE_NO"));
		        	
		        	studentPojo.setSelectedBranchId(rs.getLong("BRANCH_ID"));
		        	studentPojo.setSelectedStandardId(rs.getLong("REGISTERED_STANDARD"));
		        	studentPojo.setSelectedSectionId(rs.getLong("REGISTERED_SECTION"));
		        	
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
	
	
	public List<TeacherRegisterPojo> getAvailableTeachersListForDataTable(int startRow, int endRow, String defaultQuery){
		List<TeacherRegisterPojo> availableTeachers = new ArrayList<TeacherRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(defaultQuery+" limit "+endRow+" OFFSET "+startRow+";");
		        while(rs.next()){
		        	TeacherRegisterPojo teacherRegisterPojo = new TeacherRegisterPojo();
		        	teacherRegisterPojo.setKey(rs.getLong("TEACHER_ID"));
		        	teacherRegisterPojo.setTeacherFirstName(rs.getString("TEACHER_FIRST_NAME"));
		        	teacherRegisterPojo.setTeacherLastName(rs.getString("TEACHER_LAST_NAME"));
		        	teacherRegisterPojo.setDateOfBirth(rs.getDate("DOB"));
		        	teacherRegisterPojo.setTeacherGender(rs.getString("GENDER"));
		        	teacherRegisterPojo.setAddress(rs.getString("ADDRESS"));
		        	teacherRegisterPojo.setSpecialization(rs.getString("SPECIALIZATION"));
		        	teacherRegisterPojo.setTeacherEmail(rs.getString("EMAIL"));
		        	teacherRegisterPojo.setPhoneNumber(rs.getString("PHONE_NO"));
		        	teacherRegisterPojo.setAlternativePhoneNumber(rs.getString("SECONDARY_PHONE_NO"));
		        	teacherRegisterPojo.setBranchName(rs.getString("BRANCH_NAME"));
		        	teacherRegisterPojo.setSelectedBranchId(rs.getLong("BRANCH_ID"));
		        	
		        	availableTeachers.add(teacherRegisterPojo);
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
		
		return availableTeachers;
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
	
	
	public List<BranchRegisterPojo> getAvailableBranchesListForDataTable(int startRow, int endRow, String defaultQuery){
		List<BranchRegisterPojo> availableBranches = new ArrayList<BranchRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(defaultQuery+" limit "+endRow+" OFFSET "+startRow+";");
		        while(rs.next()){
		        	BranchRegisterPojo branchRegisterPojo = new BranchRegisterPojo();
		        	branchRegisterPojo.setKey(rs.getLong("BRANCH_ID"));
		        	branchRegisterPojo.setBranchName(rs.getString("BRANCH_NAME"));
		        	branchRegisterPojo.setBranchAddress(rs.getString("BRANCH_ADDRESS"));
		        	availableBranches.add(branchRegisterPojo);
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
		
		return availableBranches;
	}
	
}
