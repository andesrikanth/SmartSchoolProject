package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;

public class CommonUtil {

	
	public boolean registerStandardDetails(StandardRegisterPojo standardRegisterPojo){
		boolean status = true;
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				int out = stmt.executeUpdate("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('"+standardRegisterPojo.getStandardName()+"', '"+standardRegisterPojo.getStandardDesc()+"','"+standardRegisterPojo.getCreatedByUserName()+"','"+standardRegisterPojo.getCreatedByUserName()+"');" );
		        if(out == 0){
		        	status=false;
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
		
		return status;
	}
	
	public List<ChoiceListPojo.AvailableBranches> getAvailableBranchesList(){
		ChoiceListPojo choiceListPojo =new ChoiceListPojo();
		List<ChoiceListPojo.AvailableBranches> availableBranches = new ArrayList<ChoiceListPojo.AvailableBranches>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select BRANCH_ID, BRANCH_NAME FROM SCHOOL_BRANCHES;");
		        while(rs.next()){
		        	ChoiceListPojo.AvailableBranches bran= choiceListPojo.new AvailableBranches();
		        	bran.setBranchId(rs.getLong("BRANCH_ID"));
		        	bran.setBranchName(rs.getString("BRANCH_NAME"));
		        	availableBranches.add(bran);
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
		
		return availableBranches;
	}
	
	public List<ChoiceListPojo.AvailableStandards> getAvailableStandardsList(Long branchId){
		ChoiceListPojo choiceListPojo =new ChoiceListPojo();
		List<ChoiceListPojo.AvailableStandards> availableStandards = new ArrayList<ChoiceListPojo.AvailableStandards>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select STANDARD_ID, STANDARD_NAME FROM CLASS_AVBL_STANDARDS where BRANCH_ID = "+branchId+ ";");
		        while(rs.next()){
		        	ChoiceListPojo.AvailableStandards stand= choiceListPojo.new AvailableStandards();
		        	stand.setStandardId(rs.getLong("STANDARD_ID"));
		        	stand.setStandardName(rs.getString("STANDARD_NAME"));
		        	availableStandards.add(stand);
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
		
		return availableStandards;
	}
	
	public List<ChoiceListPojo.AvailableSections> getAvailableSectionsList(Long standardId){
		ChoiceListPojo choiceListPojo =new ChoiceListPojo();
		List<ChoiceListPojo.AvailableSections> availableSections = new ArrayList<ChoiceListPojo.AvailableSections>();
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from CLASS_AVBL_SECTIONS where STANDARD_ID="+standardId+";");
		        while(rs.next()){
		        	ChoiceListPojo.AvailableSections stand= choiceListPojo.new AvailableSections();
		        	stand.setSectionId(rs.getLong("SECTION_ID"));
		        	stand.setSectionName(rs.getString("SECTION_NAME"));
		        	availableSections.add(stand);
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
		return availableSections;
	}
	
	public boolean registerStudentDetails(StudentPojo studentPojo){
		boolean status = true;
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			PreparedStatement st=null;
			try{
				java.sql.Date date = new java.sql.Date(studentPojo.getDateOfBirth().getTime());
				System.out.println(date);
				//stmt = con.createStatement();
				//int out = stmt.executeUpdate("INSERT INTO STUDENT_DETAILS(STUDENT_FIRST_NAME,STUDENT_SECOND_NAME,STUDENT_LAST_NAME,DOB,FATHER_NAME, MOTHER_NAME,REGISTERED_STANDARD,REGISTERED_SECTION,ADDRESS, EMAIL,PHONE_NO, SECONDARY_PHONE_NO,CREATED_BY, LAST_UPDATED_BY)  VALUES ('"+studentPojo.getStudentFirstName()+"','"+studentPojo.getStudentMiddleName()+"','"+studentPojo.getStudentLastName()+"',"+date+",'"+studentPojo.getStudentFatherName()+"','"+studentPojo.getStudentMotherName()+"',"+studentPojo.getSelectedStandardId()+","+studentPojo.getSelectedSectionId()+",'"+studentPojo.getAddress()+"','"+studentPojo.getStudentEmail()+"','"+studentPojo.getPhoneNumber()+"','"+studentPojo.getAlternativePhoneNumber()+"','"+studentPojo.getCreatedBy()+"','"+studentPojo.getLastUpdatedBy()+"');");
				st = con.prepareStatement("INSERT INTO STUDENT_DETAILS(STUDENT_FIRST_NAME,STUDENT_SECOND_NAME,STUDENT_LAST_NAME,DOB,FATHER_NAME, MOTHER_NAME,REGISTERED_STANDARD,REGISTERED_SECTION,ADDRESS, EMAIL,PHONE_NO, SECONDARY_PHONE_NO,BRANCH_ID,CREATED_BY, LAST_UPDATED_BY) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
				st.setString(1, studentPojo.getStudentFirstName());
				st.setString(2, studentPojo.getStudentMiddleName());
				st.setString(3, studentPojo.getStudentLastName());
				st.setDate(4, date);
				st.setString(5, studentPojo.getStudentFatherName());
				st.setString(6, studentPojo.getStudentMotherName());
				st.setLong(7, studentPojo.getSelectedStandardId());
				st.setLong(8, studentPojo.getSelectedSectionId());
				st.setString(9, studentPojo.getAddress());
				st.setString(10, studentPojo.getStudentEmail());
				st.setString(11, studentPojo.getPhoneNumber());
				st.setString(12, studentPojo.getAlternativePhoneNumber());
				st.setLong(13, studentPojo.getSelectedBranchId());
				st.setString(14, studentPojo.getCreatedBy());
				st.setString(15, studentPojo.getLastUpdatedBy());
				
				
				int out=st.executeUpdate();
				if(out == 0){
		        	status=false;
		        }
		        con.commit();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				st.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
}
