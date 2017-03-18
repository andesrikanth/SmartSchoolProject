package com.smartSchoolService.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.login.RandomPasswordGenerator;
import com.smartSchoolService.login.SmartSchoolHash;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.pojo.EvaluationScoresPojo;
import com.smartSchoolService.pojo.ExamSchedulePojo;
import com.smartSchoolService.pojo.HomeworkPojo;
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.SectionTimeTablePojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
import com.smartSchoolService.pojo.StudentPojo;
import com.smartSchoolService.pojo.SubjectRegisterPojo;
import com.smartSchoolService.pojo.TeacherRegisterPojo;
import com.smartSchoolService.pojo.TimeTablePojoBean;
import com.smartSchoolService.pojo.ExamSchedulePojo.ExamScheduleSubjectPojo;

public class RegisterCommonUtil {

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
		        
		        st1 = con.prepareStatement("INSERT INTO LOGIN_DETAILS(USER_NAME, PASSWORD, DISPLAY_NAME, USER_ROLE_TYPE,  CREATED_BY,  LAST_UPDATED_BY,PWD_RESET_FLAG) VALUES(?,?,?,?,?,?,?);");
		        st1.setString(1, "ST"+currentStudentId);
		        st1.setString(2, hashedPassword);
		        st1.setString(3, studentPojo.getStudentFirstName());
		        st1.setString(4, "STUDENT");
		        st1.setString(5, studentPojo.getCreatedBy());
		        st1.setString(6, studentPojo.getLastUpdatedBy());
		        st1.setString(7, "Y");
		        
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
	
	public HashMap<String,String> registerTeacherDetails(TeacherRegisterPojo teacherRegisterPojo){
		String status = "true";
		HashMap<String,String> output = new HashMap<String,String>();
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			PreparedStatement st1 = null;
			Statement stmt1 = null;
			try{
				
				java.sql.Date date = new java.sql.Date(teacherRegisterPojo.getDateOfBirth().getTime());
				
				stmt = con.prepareStatement("INSERT INTO TEACHER_DETAILS(TEACHER_FIRST_NAME,TEACHER_SECOND_NAME,TEACHER_LAST_NAME,DOB ,ADDRESS, GENDER, SPECIALIZATION, BRANCH_ID, EMAIL, PHONE_NO, SECONDARY_PHONE_NO, CREATED_BY, LAST_UPDATED_BY) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
				
				stmt.setString(1, teacherRegisterPojo.getTeacherFirstName());
				stmt.setString(2, teacherRegisterPojo.getTeacherMiddleName());
				stmt.setString(3, teacherRegisterPojo.getTeacherLastName());
				stmt.setDate(4, date);
				stmt.setString(5, teacherRegisterPojo.getAddress());
				stmt.setString(6, teacherRegisterPojo.getTeacherGender());
				stmt.setString(7, teacherRegisterPojo.getSpecialization());
				stmt.setLong(8, teacherRegisterPojo.getSelectedBranchId());
				stmt.setString(9, teacherRegisterPojo.getTeacherEmail());
				stmt.setString(10, teacherRegisterPojo.getPhoneNumber());
				stmt.setString(11, teacherRegisterPojo.getAlternativePhoneNumber());
				stmt.setString(12, teacherRegisterPojo.getCreatedBy());
				stmt.setString(13, teacherRegisterPojo.getLastUpdatedBy());
				
				int out=stmt.executeUpdate();
				
				if(out == 0){
		        	status="false";
		        }
				
				Long currentTeacherId=new Long(0);
				stmt1 = con.createStatement();
				ResultSet rs = stmt1.executeQuery("select currval('TEACHER_ID_SEQ') as TEACHER_ID;");
		        while(rs.next()){
		        	currentTeacherId=rs.getLong("TEACHER_ID");
		        }
		        
		        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
		        String randomPwd=randomPasswordGenerator.generateNewPassword();
		        String hashedPassword=SmartSchoolHash.customHashing(randomPwd);
		        
		        st1 = con.prepareStatement("INSERT INTO LOGIN_DETAILS(USER_NAME, PASSWORD, DISPLAY_NAME, USER_ROLE_TYPE, CREATED_BY,  LAST_UPDATED_BY,PWD_RESET_FLAG) VALUES(?,?,?,?,?,?,?);");
		        st1.setString(1, "TE"+currentTeacherId);
		        st1.setString(2, hashedPassword);
		        st1.setString(3, teacherRegisterPojo.getTeacherFirstName());
		        st1.setString(4, "TEACHER");
		        st1.setString(5, teacherRegisterPojo.getCreatedBy());
		        st1.setString(6, teacherRegisterPojo.getLastUpdatedBy());
		        st1.setString(7, "Y");
		        
		        int out1=st1.executeUpdate();
				if(out1 == 0){
		        	status="false";
		        }
				output.put("USER_NAME", "TE"+currentTeacherId);
				output.put("PWD", randomPwd);
				output.put("DB_STATUS", status);
		        
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
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	public String registerSubjectDetails(SubjectRegisterPojo subjectRegisterPojo){
		String status = "true";
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement("INSERT INTO SUBJECTS_DETAILS(SUBJECT_NAME, SUBJECT_DESC, SUBJECT_TYPE, BRANCH_ID, CREATED_BY,  LAST_UPDATED_BY) VALUES(?,?,?,?,?,?);");
				
				stmt.setString(1, subjectRegisterPojo.getSubjectName());
				stmt.setString(2, subjectRegisterPojo.getSubjectDesc());
				stmt.setString(3, subjectRegisterPojo.getSubjectType());
				stmt.setLong(4, subjectRegisterPojo.getSelectedBranchId());
				stmt.setString(5, subjectRegisterPojo.getCreatedByUserName());
				stmt.setString(6, subjectRegisterPojo.getLastUpdatedByUserName());
				
				int out=stmt.executeUpdate();
				//int out = stmt.executeUpdate("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('"+standardRegisterPojo.getStandardName()+"', '"+standardRegisterPojo.getStandardDesc()+"','"+standardRegisterPojo.getCreatedByUserName()+"','"+standardRegisterPojo.getCreatedByUserName()+"');" );
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"subjects_details_branch_id_subject_name_key\"")){
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
	
	public String createTimeTableTemplate(List<TimeTablePojoBean> timeTableTemplateEntries,int noOfBlockedSlots, String userName,String templateName){
		String status = "true";
		try {
			StringBuilder dynamicQuery = new StringBuilder("INSERT INTO TIME_TABLE_TEMPLATES (NO_OF_SLOTS_USED, CREATED_BY, LAST_UPDATED_BY,TEMPLATE_NAME ");
			for(int i =1;i<=noOfBlockedSlots;i++){
				dynamicQuery.append(", SLOT"+i+"_FROM, SLOT"+i+"_TO");
			}
			
			dynamicQuery.append(") VALUES(?,?,?,?");
			
			for(int i =1;i<=noOfBlockedSlots;i++){
				dynamicQuery.append(",?,?");
			}
			dynamicQuery.append(");");
			
			System.out.println("Generated dynamicQuery "+dynamicQuery);
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement(dynamicQuery.toString());
				stmt.setLong(1, new Long(noOfBlockedSlots));
				stmt.setString(2, userName);
				stmt.setString(3, userName);
				stmt.setString(4, templateName);
				
				int backupValue=4;
				for(int i =1;i<=noOfBlockedSlots;i++){
					
					Date fromDate=timeTableTemplateEntries.get(i-1).getFromTime();
					Date toDate = timeTableTemplateEntries.get(i-1).getToTime();
					
					
					Time fromTime = new Time(fromDate.getTime());
					Time toTime = new Time(toDate.getTime());
					
					stmt.setTime(backupValue+i,fromTime );
					stmt.setTime(backupValue+1+i,toTime );
					backupValue=backupValue+1;
				}
				
				
				int out=stmt.executeUpdate();
				//int out = stmt.executeUpdate("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('"+standardRegisterPojo.getStandardName()+"', '"+standardRegisterPojo.getStandardDesc()+"','"+standardRegisterPojo.getCreatedByUserName()+"','"+standardRegisterPojo.getCreatedByUserName()+"');" );
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				status="false";
				if(e !=null && e.getLocalizedMessage()!=null){
					if(e.getLocalizedMessage().contains("violates unique constraint \"time_table_templates_template_name_key\"")){
						status="Same Template Name already exists. Please enter a different Template Name";
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
	
	public Long getNextValueForSequence(String sequenceName)
	{
		Long nextval = new Long(1);
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select NEXTVAL('"+sequenceName+"');");
		        while(rs.next()){
		        	nextval=rs.getLong(1);
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
		
		
		return nextval;
	}
	public String createSectionTimeTableTemplate(List<SectionTimeTablePojo> sectionTimeTableList, Long selectedTimeTableTemplateId, Long selectedSectionId, String[] dayOfWeek, String userName){
		
		String status = "true";
		if(sectionTimeTableList != null && sectionTimeTableList.size()>0){
			int noOfBlockedSlots= sectionTimeTableList.size();
			try {
				String dayOfWeekStr="";
				
				if(dayOfWeek != null){
					for (int k=0;k<dayOfWeek.length;k++){
						if(k==0){
							dayOfWeekStr=dayOfWeek[k];
						}
						else {
							dayOfWeekStr=dayOfWeekStr+","+dayOfWeek[k];
						}
						
					}
				}
				
				Long sequenceNextVal = this.getNextValueForSequence("SECTION_SUBJECT_TIME_TABLE_ID_SEQ");
				
				boolean localStatus = createSectionTimeTableTemplateChildMethod(sectionTimeTableList,  selectedTimeTableTemplateId,  selectedSectionId, dayOfWeek,  userName,noOfBlockedSlots, sequenceNextVal,dayOfWeekStr);
				if(!localStatus){
					sequenceNextVal = this.getNextValueForSequence("SECTION_SUBJECT_TIME_TABLE_ID_SEQ");
					localStatus = createSectionTimeTableTemplateChildMethod(sectionTimeTableList,  selectedTimeTableTemplateId,  selectedSectionId, dayOfWeek,  userName,noOfBlockedSlots, sequenceNextVal,dayOfWeekStr);
				}
				if(localStatus){
					String sqlQuery = "INSERT INTO TEACHER_SUBJECT_TIME_TABLE (TIME_TABLE_TEMPLATE_ID, SECTION_SUBJECT_TIME_TABLE_ID, TEACHER_ID, SLOT_NUMBER, CREATED_BY, LAST_UPDATED_BY) VALUES (?,?,?,?,?,?);";
					DatabaseUtility databaseUtility =new DatabaseUtility();
					Connection con=databaseUtility.getConnection();
					PreparedStatement stmt = null;
					try{
						
						stmt = con.prepareStatement(sqlQuery);
						
						for (int i=1;i<=noOfBlockedSlots;i++){
							
							Long localTeacherId = sectionTimeTableList.get(i-1).getTeacherId();
							stmt.setLong(1, selectedTimeTableTemplateId);
							stmt.setLong(2, sequenceNextVal);
							if(localTeacherId != null && localTeacherId == 0){
								stmt.setNull(3, java.sql.Types.NULL);
							}
							else {
								stmt.setLong(3, localTeacherId);
							}
							
							stmt.setLong(4, i);
							stmt.setString(5, userName);
							stmt.setString(6, userName);
							
							stmt.addBatch();
						}
						
						
						stmt.executeBatch();
				        
				        con.commit();
					}
					catch(Exception e){
						status="false";
						//e.printStackTrace();
						if (e instanceof java.sql.SQLException) {
						       java.sql.SQLException ne = ((java.sql.SQLException) e).getNextException();
						       if (ne != null) {
						    	   ne.printStackTrace();
						       } 
						   } else {
						      e.printStackTrace();
						   }
						
					}
					finally{
						stmt.close();
						databaseUtility.closeConnection(con);
					}
				}
				else {
					status="false";
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				status="false";
				if(e!=null && e.getCause()!=null) {
					status=e.getCause().toString();
				}
				
				e.printStackTrace();
			}
		}
		else {
			status ="There are no timeslots defined in the timetable. Please contact product support for assistance.";
		}
		
		return status;
	}
	
	public boolean createSectionTimeTableTemplateChildMethod(List<SectionTimeTablePojo> sectionTimeTableList, Long selectedTimeTableTemplateId, Long selectedSectionId, String[] dayOfWeek, String userName, int noOfBlockedSlots, Long sequenceNextVal, String dayOfWeekStr){
		boolean status = true;
		
		try {
			
			StringBuilder dynamicQuery = new StringBuilder("INSERT INTO SECTION_SUBJECT_TIME_TABLE (SECTION_SUBJECT_TIME_TABLE_ID,TEMPLATE_ID, NO_OF_SLOTS_USED, SECTION_ID, DAY_OF_WEEK, CREATED_BY, LAST_UPDATED_BY ");
			for(int i =1;i<=noOfBlockedSlots;i++){
				dynamicQuery.append(", SLOT"+i);
			}
			
			dynamicQuery.append(") VALUES(?,?,?,?,?,?,?");
			
			for(int i =1;i<=noOfBlockedSlots;i++){
				dynamicQuery.append(",?");
			}
			dynamicQuery.append(");");
			
			System.out.println("Generated dynamicQuery "+dynamicQuery);
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement(dynamicQuery.toString());
				stmt.setLong(1, sequenceNextVal);
				stmt.setLong(2, selectedTimeTableTemplateId);
				stmt.setLong(3, new Long(noOfBlockedSlots));
				stmt.setLong(4, selectedSectionId);
				stmt.setString(5, dayOfWeekStr);
				stmt.setString(6, userName);
				stmt.setString(7, userName);
				
				for(int i =1;i<=noOfBlockedSlots;i++){
					
					Long subjectId = sectionTimeTableList.get(i-1).getSubjectId();
					
					stmt.setLong(7+i,subjectId);
				}
				
				
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

	public String createHomework(HomeworkPojo homeworkPojo){
		String status="true";
		try {
			
			java.sql.Date date = new java.sql.Date(homeworkPojo.getAssignmentDate().getTime());
			
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement("INSERT INTO SCHOOL_HOMEWORK(BRANCH_ID, STANDARD_ID, SECTION_ID, ASSIGNMENT_DATE, SUBJECT_ID,  STUDENT_ID, HOMEWORK_DETAILS, CREATED_BY,  LAST_UPDATED_BY) VALUES(?,?,?,?,?,?,?,?,?);");
				
				stmt.setLong(1, homeworkPojo.getBranchId());
				stmt.setLong(2, homeworkPojo.getStandardId());
				stmt.setLong(3, homeworkPojo.getSectionId());
				stmt.setDate(4, date);
				stmt.setLong(5, homeworkPojo.getSubjectId());
				stmt.setLong(6, homeworkPojo.getStudentId());
				stmt.setString(7, homeworkPojo.getAssignmentDetails());
				stmt.setString(8, homeworkPojo.getCreatedByUserName());
				stmt.setString(9, homeworkPojo.getLastUpdatedByUserName());
				
				int out=stmt.executeUpdate();
				//int out = stmt.executeUpdate("INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('"+standardRegisterPojo.getStandardName()+"', '"+standardRegisterPojo.getStandardDesc()+"','"+standardRegisterPojo.getCreatedByUserName()+"','"+standardRegisterPojo.getCreatedByUserName()+"');" );
		        if(out == 0){
		        	status="false";
		        }
		        
		        con.commit();
			}
			catch(Exception e){
				System.out.println("e.getMessage() "+ e.getMessage());
				System.out.println("e.getCause() "+e.getCause());
				System.out.println("e.getLocalizedMessage() "+e.getLocalizedMessage());
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
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String createExamScheduleTable(List<ExamSchedulePojo.ExamScheduleSubjectPojo> examScheduleSubjectPojoList, ExamSchedulePojo examSchedulePojo){
		String status = "true";
		int noOfSubjects=0;
		if(examScheduleSubjectPojoList != null){
			noOfSubjects=examScheduleSubjectPojoList.size();
		}
		Long sequenceNextVal = this.getNextValueForSequence("SCHOOL_EXAMS_ID_SEQ");
		
		boolean localStatus = createExamScheduleTableChildMethod(examSchedulePojo, sequenceNextVal);
		if(!localStatus){
			sequenceNextVal = this.getNextValueForSequence("SCHOOL_EXAMS_ID_SEQ");
			localStatus = createExamScheduleTableChildMethod(examSchedulePojo, sequenceNextVal);
		}
		
		try{
			if(localStatus && noOfSubjects >0){
				String sqlQuery = "INSERT INTO SCHOOL_EXAMS_DETAILS (SCHOOL_EXAMS_ID, SUBJECT_ID, SUB_EXAM_DATE, SUB_EXAM_START_TIME, SUB_EXAM_END_TIME, CREATED_BY, LAST_UPDATED_BY) VALUES (?,?,?,?,?,?,?);";
				DatabaseUtility databaseUtility =new DatabaseUtility();
				Connection con=databaseUtility.getConnection();
				PreparedStatement stmt = null;
				try{
					
					stmt = con.prepareStatement(sqlQuery);
					
					for (int i=0;i<noOfSubjects;i++){
						ExamSchedulePojo.ExamScheduleSubjectPojo examScheduleSubjectPojo = examScheduleSubjectPojoList.get(i); 
						
						java.sql.Date examDate = new java.sql.Date(examScheduleSubjectPojo.getSubjectExamDate().getTime());
						
						Time startTime = new Time(examScheduleSubjectPojo.getSubjectExamStartTime().getTime());
						Time endTime = new Time(examScheduleSubjectPojo.getSubjectExamEndTime().getTime());

						stmt.setLong(1, sequenceNextVal);
						stmt.setLong(2, examScheduleSubjectPojo.getSubjectId());
						stmt.setDate(3, examDate);
						stmt.setTime(4,startTime);
						stmt.setTime(5,endTime);
						stmt.setString(6, examScheduleSubjectPojo.getCreatedByUserName());
						stmt.setString(7, examScheduleSubjectPojo.getLastUpdatedByUserName());
						
						stmt.addBatch();
					}
					
					
					stmt.executeBatch();
			        
			        con.commit();
				}
				catch(Exception e){
					status="false";
					//e.printStackTrace();
					if (e instanceof java.sql.SQLException) {
					       java.sql.SQLException ne = ((java.sql.SQLException) e).getNextException();
					       if (ne != null) {
					    	   ne.printStackTrace();
					       } 
					   } else {
					      e.printStackTrace();
					   }
					
				}
				finally{
					stmt.close();
					databaseUtility.closeConnection(con);
				}
			}
			else {
				if(noOfSubjects==0){
					status="There are no subjects defined for the Exam Schedule. Please provide atleast one subject for the timetable. If you need assistance, please contact product support.";
				}
				else {
					status="false";
				}
				
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			status="false";
			e.printStackTrace();
		}
		
		return status;
	}
	
	public boolean createExamScheduleTableChildMethod( ExamSchedulePojo examSchedulePojo, Long sequenceNextVal){
		boolean status = true;
		
		try {
			
			java.sql.Date startDate = new java.sql.Date(examSchedulePojo.getExamStartDate().getTime());
			java.sql.Date endDate = new java.sql.Date(examSchedulePojo.getExamEndDate().getTime());
			
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			try{
				
				stmt = con.prepareStatement("INSERT INTO SCHOOL_EXAMS(SCHOOL_EXAMS_ID,BRANCH_ID, STANDARD_ID, SECTION_ID, EXAM_START_DATE, EXAM_END_DATE, EXAM_DETAILS, CREATED_BY,  LAST_UPDATED_BY) VALUES(?,?,?,?,?,?,?,?,?);");
				
				stmt.setLong(1, sequenceNextVal);
				stmt.setLong(2, examSchedulePojo.getBranchId());
				stmt.setLong(3, examSchedulePojo.getStandardId());
				stmt.setLong(4, examSchedulePojo.getSectionId());
				stmt.setDate(5, startDate);
				stmt.setDate(6, endDate);
				stmt.setString(7, examSchedulePojo.getExamDetails());
				stmt.setString(8, examSchedulePojo.getCreatedByUserName());
				stmt.setString(9, examSchedulePojo.getLastUpdatedByUserName());
				
				int out=stmt.executeUpdate();
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
	
	
	public String createEvaluationScoresForSubject(Long branchId, Long standardId, Long sectionId,Long schoolExamId, Long subjectId, HashMap<Long,EvaluationScoresPojo> newEvaluationScoresEntries, String loggedUserName){
		
		String status = "true";
		     try{
		    	 
		    	 boolean localStatus = true;
		    	 Set<Long> keySet = newEvaluationScoresEntries.keySet();
					Iterator<Long> it = keySet.iterator();
					DatabaseUtility databaseUtility =new DatabaseUtility();
					Connection con=databaseUtility.getConnection();
					//Connection con1=databaseUtility.getConnection();
					
					PreparedStatement stmt = null;
					PreparedStatement stmt1 = null;
					
					String query = "INSERT INTO SCHOOL_EVALUATION_SCORES (SCHOOL_EVALUATION_SCORES_ID,BRANCH_ID, STANDARD_ID, SECTION_ID, STUDENT_ID,SCHOOL_EXAMS_ID, CREATED_BY, LAST_UPDATED_BY )  VALUES(?,?,?,?,?,?,?,?);";
					String sqlQuery = "INSERT INTO SCHOOL_EVALUATION_SCORES_DETAILS (SCHOOL_EVALUATION_SCORES_ID, SUBJECT_ID, SUBJECT_SCORE, GRADE, COMMENTS, CREATED_BY, LAST_UPDATED_BY) VALUES (?,?,?,?,?,?,?);";
					
					stmt = con.prepareStatement(query);
					stmt1 = con.prepareStatement(sqlQuery);
					
					boolean parentTableInsertStatus = true;
					
					while(it.hasNext()){
						Long sequenceNextVal = null;
						EvaluationScoresPojo evaluationScoresPojoLocal = newEvaluationScoresEntries.get(it.next());
						Long schoolEvaluationScoresId = evaluationScoresPojoLocal.getSchoolEvaluationScoresId();
						
						if(schoolEvaluationScoresId != null && schoolEvaluationScoresId.intValue() != 0){
							parentTableInsertStatus = false;
							sequenceNextVal = schoolEvaluationScoresId;
						}
						
						if(parentTableInsertStatus){
							try{
								sequenceNextVal = this.getNextValueForSequence("SCHOOL_EVALUATION_SCORES_ID_SEQ");
								stmt.setLong(1, sequenceNextVal);
								stmt.setLong(2, branchId);
								stmt.setLong(3, standardId);
								stmt.setLong(4, sectionId);
								stmt.setLong(5, evaluationScoresPojoLocal.getKey());
								stmt.setLong(6, schoolExamId);
								stmt.setString(7, loggedUserName);
								stmt.setString(8, loggedUserName);
								
								stmt.addBatch();
						        					        
							}
							catch(Exception e){
								localStatus=false;
								e.printStackTrace();
							}	
						}
						
							try{
									stmt1.setLong(1, sequenceNextVal);
									stmt1.setLong(2, subjectId);
									stmt1.setBigDecimal(3, evaluationScoresPojoLocal.getSubjectScore());
									stmt1.setString(4, evaluationScoresPojoLocal.getGrade());
									stmt1.setString(5, evaluationScoresPojoLocal.getComments());
									stmt1.setString(6, loggedUserName);
									stmt1.setString(7, loggedUserName);
									
									stmt1.addBatch();
						        
							}
							catch(Exception e){
								localStatus=false;
								e.printStackTrace();
							}
						
					}
					
					try{
						
						if(localStatus){
							if(parentTableInsertStatus){
								stmt.executeBatch();	
							}
							
							stmt1.executeBatch();
							con.commit();
					        //con1.commit();
						}
						else {
							status = "false";
						}
						
					}
					catch(Exception e){
						status="false";
						//e.printStackTrace();
						if (e instanceof java.sql.SQLException) {
						       java.sql.SQLException ne = ((java.sql.SQLException) e).getNextException();
						       if (ne != null) {
						    	   ne.printStackTrace();
						       } 
						   } else {
						      e.printStackTrace();
						   }
						
					}
					finally{
						stmt.close();
						stmt1.close();
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
