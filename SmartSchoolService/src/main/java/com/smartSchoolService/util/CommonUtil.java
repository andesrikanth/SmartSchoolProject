package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.login.RandomPasswordGenerator;
import com.smartSchoolService.login.SmartSchoolHash;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;

public class CommonUtil {

	
	public boolean registerStandardDetails(StandardRegisterPojo standardRegisterPojo){
		boolean status = true;
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, BRANCH_ID, CREATED_BY,  LAST_UPDATED_BY) VALUES(?,?,?,?,?);");
				stmt.setString(1, standardRegisterPojo.getStandardName());
				stmt.setString(2, standardRegisterPojo.getStandardDesc());
				stmt.setLong(3, standardRegisterPojo.getBranchId());
				stmt.setString(4, standardRegisterPojo.getCreatedByUserName());
				stmt.setString(5, standardRegisterPojo.getLastUpdatedByUserName());
				
				int out=stmt.executeUpdate();
				//int out = stmt.executeUpdate("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('"+standardRegisterPojo.getStandardName()+"', '"+standardRegisterPojo.getStandardDesc()+"','"+standardRegisterPojo.getCreatedByUserName()+"','"+standardRegisterPojo.getCreatedByUserName()+"');" );
		        if(out == 0){
		        	status=false;
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status=false;
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			status=false;
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
	
	public List<ChoiceListPojo.AvailableSections> getAvailableSectionsList(Long selectedBranchId, Long standardId){
		ChoiceListPojo choiceListPojo =new ChoiceListPojo();
		List<ChoiceListPojo.AvailableSections> availableSections = new ArrayList<ChoiceListPojo.AvailableSections>();
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from CLASS_AVBL_SECTIONS where BRANCH_ID= "+selectedBranchId+" AND STANDARD_ID="+standardId+";");
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
	
	public HashMap<String,String> registerStudentDetails(StudentPojo studentPojo){
		String status = "true";
		HashMap<String,String> output = new HashMap<String,String>();
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			PreparedStatement st=null;
			PreparedStatement st1=null;
			try{
				java.sql.Date date = new java.sql.Date(studentPojo.getDateOfBirth().getTime());
				//stmt = con.createStatement();
				//int out = stmt.executeUpdate("INSERT INTO STUDENT_DETAILS(STUDENT_FIRST_NAME,STUDENT_SECOND_NAME,STUDENT_LAST_NAME,DOB,FATHER_NAME, MOTHER_NAME,REGISTERED_STANDARD,REGISTERED_SECTION,ADDRESS, EMAIL,PHONE_NO, SECONDARY_PHONE_NO,CREATED_BY, LAST_UPDATED_BY)  VALUES ('"+studentPojo.getStudentFirstName()+"','"+studentPojo.getStudentMiddleName()+"','"+studentPojo.getStudentLastName()+"',"+date+",'"+studentPojo.getStudentFatherName()+"','"+studentPojo.getStudentMotherName()+"',"+studentPojo.getSelectedStandardId()+","+studentPojo.getSelectedSectionId()+",'"+studentPojo.getAddress()+"','"+studentPojo.getStudentEmail()+"','"+studentPojo.getPhoneNumber()+"','"+studentPojo.getAlternativePhoneNumber()+"','"+studentPojo.getCreatedBy()+"','"+studentPojo.getLastUpdatedBy()+"');");
				st = con.prepareStatement("INSERT INTO STUDENT_DETAILS(STUDENT_FIRST_NAME,STUDENT_SECOND_NAME,STUDENT_LAST_NAME,DOB,FATHER_NAME, MOTHER_NAME,REGISTERED_STANDARD,REGISTERED_SECTION,ADDRESS, EMAIL,PHONE_NO, SECONDARY_PHONE_NO,BRANCH_ID,CREATED_BY, LAST_UPDATED_BY,GENDER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
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
				st.setString(16, studentPojo.getSelectedStudentGender());
				
				int out=st.executeUpdate();
				if(out == 0){
		        	status="false";
		        }
				
				Long currentStudentId=new Long(0);
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select currval('STUDENT_ID_SEQ') as STUDENT_ID;");
		        while(rs.next()){
		        	currentStudentId=rs.getLong("STUDENT_ID");
		        }
		        
		        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
		        String randomPwd=randomPasswordGenerator.generateNewPassword();
		        String hashedPassword=SmartSchoolHash.customHashing(randomPwd);
		        
		        st1 = con.prepareStatement("INSERT INTO LOGIN_DETAILS(USER_NAME, PASSWORD, DISPLAY_NAME, USER_ROLE_TYPE, CREATED_BY,  LAST_UPDATED_BY) VALUES(?,?,?,?,?,?);");
		        st1.setString(1, "ST"+currentStudentId);
		        st1.setString(2, hashedPassword);
		        st1.setString(3, studentPojo.getStudentFirstName());
		        st1.setString(4, "STUDENT");
		        st1.setString(5, studentPojo.getCreatedBy());
		        st1.setString(6, studentPojo.getLastUpdatedBy());
		        
		        int out1=st1.executeUpdate();
				if(out1 == 0){
		        	status="false";
		        }
				output.put("USER_NAME", "ST"+currentStudentId);
				output.put("PWD", randomPwd);
				output.put("DB_STATUS", status);
				
		        con.commit();
			}
			catch(Exception e){
				status="false";
				e.printStackTrace();
			}
			finally{
				st.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			status="false";
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	public boolean registerBranchDetails(BranchRegisterPojo branchRegisterPojo){
		boolean status = true;
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement("INSERT INTO SCHOOL_BRANCHES(BRANCH_NAME, BRANCH_ADDRESS, CREATED_BY,  LAST_UPDATED_BY) VALUES(?,?,?,?);");
				
				stmt.setString(1, branchRegisterPojo.getBranchName());
				stmt.setString(2, branchRegisterPojo.getBranchAddress());
				stmt.setString(3, branchRegisterPojo.getCreatedByUserName());
				stmt.setString(4, branchRegisterPojo.getLastUpdatedByUserName());
				
				int out=stmt.executeUpdate();
				//int out = stmt.executeUpdate("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('"+standardRegisterPojo.getStandardName()+"', '"+standardRegisterPojo.getStandardDesc()+"','"+standardRegisterPojo.getCreatedByUserName()+"','"+standardRegisterPojo.getCreatedByUserName()+"');" );
		        if(out == 0){
		        	status=false;
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status=false;
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status=false;
			e.printStackTrace();
		}
		
		return status;
	}
	
	public boolean registerSectionDetails(SectionRegisterPojo sectionRegisterPojo){
		boolean status = true;
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement("INSERT INTO CLASS_AVBL_SECTIONS(SECTION_NAME, BRANCH_ID, STANDARD_ID, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES(?,?,?,?,?,?);");
				
				stmt.setString(1, sectionRegisterPojo.getSectionName());
				stmt.setLong(2, sectionRegisterPojo.getBranchId());
				stmt.setLong(3, sectionRegisterPojo.getStandardId());
				stmt.setString(4, sectionRegisterPojo.getSectionDesc());
				stmt.setString(5, sectionRegisterPojo.getCreatedByUserName());
				stmt.setString(6, sectionRegisterPojo.getLastUpdatedByUserName());
				
				int out=stmt.executeUpdate();
				//int out = stmt.executeUpdate("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('"+standardRegisterPojo.getStandardName()+"', '"+standardRegisterPojo.getStandardDesc()+"','"+standardRegisterPojo.getCreatedByUserName()+"','"+standardRegisterPojo.getCreatedByUserName()+"');" );
		        if(out == 0){
		        	status=false;
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				System.out.println("e.getMessage() "+ e.getMessage());
				System.out.println("e.getCause() "+e.getCause());
				System.out.println("e.getLocalizedMessage() "+e.getLocalizedMessage());
				status=false;
				e.printStackTrace();
			}
			finally{
				stmt.close();
				databaseUtility.closeConnection(con);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			status=false;
			e.printStackTrace();
		}
		
		return status;
	}

}
