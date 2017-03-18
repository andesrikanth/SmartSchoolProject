package com.smartSchoolService.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.pojo.BranchRegisterPojo;
import com.smartSchoolService.pojo.EvaluationScoresPojo;
import com.smartSchoolService.pojo.ExamSchedulePojo;
import com.smartSchoolService.pojo.SectionRegisterPojo;
import com.smartSchoolService.pojo.SectionTimeTablePojo;
import com.smartSchoolService.pojo.StandardRegisterPojo;
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
	
	public List<StandardRegisterPojo> getAvailableStandardsListForDataTable(int startRow, int endRow, String defaultQuery){
		List<StandardRegisterPojo> availableStandards = new ArrayList<StandardRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(defaultQuery+" limit "+endRow+" OFFSET "+startRow+";");
		        while(rs.next()){
		        	
		        	StandardRegisterPojo standardRegisterPojo = new StandardRegisterPojo();
		        	standardRegisterPojo.setKey(rs.getLong("STANDARD_ID"));
		        	standardRegisterPojo.setStandardName(rs.getString("STANDARD_NAME"));
		        	standardRegisterPojo.setStandardDesc(rs.getString("DESCRIPTION"));
		        	standardRegisterPojo.setBranchId(rs.getLong("BRANCH_ID"));
		        	standardRegisterPojo.setBranchName(rs.getString("BRANCH_NAME"));
		        	availableStandards.add(standardRegisterPojo);
		        	
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
		
		return availableStandards;
	}
	
	public List<SectionRegisterPojo> getAvailableSectionsListForDataTable(int startRow, int endRow, String defaultQuery){
		List<SectionRegisterPojo> availableSections = new ArrayList<SectionRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(defaultQuery+" limit "+endRow+" OFFSET "+startRow+";");
		        while(rs.next()){
		        	SectionRegisterPojo sectionRegisterPojo = new SectionRegisterPojo();
		        	sectionRegisterPojo.setKey(rs.getLong("SECTION_ID"));
		        	sectionRegisterPojo.setSectionName(rs.getString("SECTION_NAME"));
		        	sectionRegisterPojo.setBranchId(rs.getLong("BRANCH_ID"));
		        	sectionRegisterPojo.setBranchName(rs.getString("BRANCH_NAME"));
		        	sectionRegisterPojo.setStandardId(rs.getLong("STANDARD_ID"));
		        	sectionRegisterPojo.setStandardName(rs.getString("STANDARD_NAME"));
		        	sectionRegisterPojo.setSectionDesc(rs.getString("DESCRIPTION"));
		        	availableSections.add(sectionRegisterPojo);
		        	
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
		
		return availableSections;
	}
	
	public List<ChoiceListPojo.AvailableTimetableTemplates> getAvailableTimetableTemplatesList(){
		ChoiceListPojo choiceListPojo =new ChoiceListPojo();
		List<ChoiceListPojo.AvailableTimetableTemplates> availableTimetableTemplates = new ArrayList<ChoiceListPojo.AvailableTimetableTemplates>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select TIME_TABLE_TEMPLATE_ID, TEMPLATE_NAME FROM TIME_TABLE_TEMPLATES;");
		        while(rs.next()){
		        	ChoiceListPojo.AvailableTimetableTemplates templates= choiceListPojo.new AvailableTimetableTemplates();
		        	Long templateId = rs.getLong("TIME_TABLE_TEMPLATE_ID");
		        	
		        	templates.setTemplateId(templateId);
		        	templates.setTemplateName(rs.getString("TEMPLATE_NAME"));
		        	availableTimetableTemplates.add(templates);
		        			        	
		        	
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
		
		return availableTimetableTemplates;
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
		        	Long branId = rs.getLong("BRANCH_ID");
		        	
		        		bran.setBranchId(branId);
		        		bran.setBranchName(rs.getString("BRANCH_NAME"));
		        		availableBranches.add(bran);
		        			        	
		        	
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
	
	public List<ChoiceListPojo.AvailableStandards> getAvailableStandardsList(Long branchId, Long ignoreStandardId){
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
		        	
		        	Long standardId =rs.getLong("STANDARD_ID");
		        	
		        	//We are having the below condition just to skip the Standard name having the same value as input parameter.
		        	if(ignoreStandardId==null || (ignoreStandardId !=null && !ignoreStandardId.equals(standardId))){
		        		stand.setStandardId(standardId);
			        	stand.setStandardName(rs.getString("STANDARD_NAME"));
			        	availableStandards.add(stand);
		        	}
		        	
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
		
		return availableStandards;
	}
	
	public List<ChoiceListPojo.AvailableSections> getAvailableSectionsList(Long selectedBranchId, Long standardId, Long ignoreSectionId){
		ChoiceListPojo choiceListPojo =new ChoiceListPojo();
		List<ChoiceListPojo.AvailableSections> availableSections = new ArrayList<ChoiceListPojo.AvailableSections>();
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select SECTION_ID,SECTION_NAME from CLASS_AVBL_SECTIONS where BRANCH_ID= "+selectedBranchId+" AND STANDARD_ID="+standardId+";");
		        while(rs.next()){
		        	ChoiceListPojo.AvailableSections stand= choiceListPojo.new AvailableSections();
		        	Long secId = rs.getLong("SECTION_ID");
		        	//We are having the below condition just to skip the section name having the same value as input parameter.
		        	if(ignoreSectionId==null || (ignoreSectionId !=null && !ignoreSectionId.equals(secId))){
		        		stand.setSectionId(secId);
			        	stand.setSectionName(rs.getString("SECTION_NAME"));
			        	availableSections.add(stand);
		        	}
		        	
		        	
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
		return availableSections;
	}
	
	public List<TeacherRegisterPojo> getAvailableTeachersListForBranch(Long branchId){
		List<TeacherRegisterPojo> availableTeachers = new ArrayList<TeacherRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select TEACHER_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME  from TEACHER_DETAILS WHERE BRANCH_ID ="+branchId+";");
		        while(rs.next()){
		        	TeacherRegisterPojo teacherRegisterPojo = new TeacherRegisterPojo();
		        	teacherRegisterPojo.setKey(rs.getLong("TEACHER_ID"));
		        	teacherRegisterPojo.setTeacherFirstName(rs.getString("TEACHER_FIRST_NAME"));
		        	teacherRegisterPojo.setTeacherLastName(rs.getString("TEACHER_LAST_NAME"));
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
	
	public List<SubjectRegisterPojo> getAvailableSubjectsListForBranch(Long branchId){
		List<SubjectRegisterPojo> availableSubjects = new ArrayList<SubjectRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select SUBJECT_ID , SUBJECT_NAME from SUBJECTS_DETAILS WHERE BRANCH_ID = "+branchId+";");
		        while(rs.next()){
		        	SubjectRegisterPojo subjectRegisterPojo = new SubjectRegisterPojo();
		        	subjectRegisterPojo.setKey(rs.getLong("SUBJECT_ID"));
		        	subjectRegisterPojo.setSubjectName(rs.getString("SUBJECT_NAME"));
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
	
	public List<SectionTimeTablePojo> getTimeTableTemplateDetailsForId(Long timeTableTemplateId){
		
		List<SectionTimeTablePojo> sectionTimeTableList = new ArrayList<SectionTimeTablePojo>();;
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from TIME_TABLE_TEMPLATES WHERE TIME_TABLE_TEMPLATE_ID = "+timeTableTemplateId+";");
		        while(rs.next()){
		        	Long slotsUsed=rs.getLong("NO_OF_SLOTS_USED");
		        	for (int i=1;i<=slotsUsed.intValue();i++){
		        		SectionTimeTablePojo sectionTimeTablePojo = new SectionTimeTablePojo(new Long(i));
		        		Time fromTime = rs.getTime("SLOT"+i+"_FROM");
		        		Time toTime = rs.getTime("SLOT"+i+"_TO");

		        		Date fromDate=new Date(fromTime.getTime());
		        		DateFormat df=new SimpleDateFormat("hh:mm a");
		        		String fromDateStr=df.format(fromDate);
		        		
		        		Date toDate=new Date(toTime.getTime());
		        		DateFormat df1=new SimpleDateFormat("hh:mm a");
		        		String toDateStr=df.format(toDate);
		        		
		        		
		        		sectionTimeTablePojo.setFromTime(fromTime);
		        		sectionTimeTablePojo.setToTime(toTime);
		        		
		        		sectionTimeTablePojo.setFromTimeString(fromDateStr);
		        		sectionTimeTablePojo.setToTimeString(toDateStr);
		        		
		        		sectionTimeTableList.add(sectionTimeTablePojo);
		        	}
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
		
		return sectionTimeTableList;
		
	}
	
	public boolean checkForExistingTimeTableForSection(Long sectionId){
		
		boolean status = false;
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select SECTION_SUBJECT_TIME_TABLE_ID from SECTION_SUBJECT_TIME_TABLE where section_id = "+sectionId+" and ACTIVE_FLAG = 'Y';");
		        while(rs.next()){
		        	status = true;
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
		
		return status;
		
	}
	
	public List<SubjectRegisterPojo> getAvailableSubjectsListForSection(Long sectionId){
		List<SubjectRegisterPojo> availableSubjects = new ArrayList<SubjectRegisterPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT NO_OF_SLOTS_USED FROM SECTION_SUBJECT_TIME_TABLE WHERE SECTION_ID = "+sectionId+";");
				Long noOfSlotsUsed = null;
						
				while(rs.next()){
					noOfSlotsUsed = rs.getLong("NO_OF_SLOTS_USED");
				}
				
				if(noOfSlotsUsed != null){
					
					Statement stmt1 = con.createStatement();
					
					String query = "SELECT ";
					String fromTables=" FROM SECTION_SUBJECT_TIME_TABLE sec,";
					String whereClause = " WHERE SECTION_ID = "+sectionId+" AND ";
					
					for (int i =1 ; i<=noOfSlotsUsed; i++){
						query = query+"sec.SLOT"+i+" , sub"+i+".subject_name SLOT"+i+"_NAME ,";
						fromTables=fromTables+" SUBJECTS_DETAILS sub"+i+",";
						whereClause=whereClause+" sec.slot"+i+"= sub"+i+".subject_id AND sub"+i+".SUBJECT_TYPE <> 'SPECIAL' AND";
					}
					
					query = query.substring(0, query.length()-1);
					fromTables = fromTables.substring(0, fromTables.length()-1);
					whereClause=whereClause.substring(0,whereClause.length()-3);
					query= query+fromTables+ whereClause;
					
					ResultSet rs1 = stmt1.executeQuery(query+";");
					
					
					
					while(rs1.next()){
						for (int i =1 ; i<=noOfSlotsUsed; i++){
							SubjectRegisterPojo subjectRegisterPojo = new SubjectRegisterPojo();
				        	subjectRegisterPojo.setKey(rs1.getLong("SLOT"+i));
				        	subjectRegisterPojo.setSubjectName(rs1.getString("SLOT"+i+"_NAME"));
				        	availableSubjects.add(subjectRegisterPojo);	
						}
			        	
			        }
				}
				else {
					return availableSubjects;
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
	
	public List<StudentPojo> getAvailableStudentsListForSection(Long sectionId){
		List<StudentPojo> availableStudents = new ArrayList<StudentPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select STUDENT_ID, STUDENT_FIRST_NAME, STUDENT_LAST_NAME from STUDENT_DETAILS WHERE REGISTERED_SECTION = "+sectionId+";");
		        while(rs.next()){
		        	StudentPojo studentPojo = new StudentPojo();
		        	studentPojo.setKey(rs.getLong("STUDENT_ID"));
		        	studentPojo.setStudentFirstName(rs.getString("STUDENT_FIRST_NAME")+" "+rs.getString("STUDENT_LAST_NAME"));
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
	
	public List<ExamSchedulePojo> getAvailableExamsListForSection(Long sectionId){
		List<ExamSchedulePojo> availableExams = new ArrayList<ExamSchedulePojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select SCHOOL_EXAMS_ID, EXAM_DETAILS from SCHOOL_EXAMS WHERE SECTION_ID = "+sectionId+";");
		        while(rs.next()){
		        	ExamSchedulePojo examPojo = new ExamSchedulePojo();
		        	examPojo.setKey(rs.getLong("SCHOOL_EXAMS_ID"));
		        	examPojo.setExamDetails(rs.getString("EXAM_DETAILS"));
		        	availableExams.add(examPojo);
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
		
		return availableExams;
	}
		
	public ExamSchedulePojo checkForExistingExamsScheduleForSection(Long sectionIds, Date startDate, Date endDate){
			
			ExamSchedulePojo examSchedulePojo = null;
			
			try {
				DatabaseUtility databaseUtility =new DatabaseUtility();
				Connection con=databaseUtility.getConnection();
				PreparedStatement stmt = null;
				
				try{
					stmt = con.prepareStatement("select EXAM_DETAILS, EXAM_START_DATE, EXAM_END_DATE from SCHOOL_EXAMS where section_id = ? and ((? BETWEEN EXAM_START_DATE AND EXAM_END_DATE or ? BETWEEN EXAM_START_DATE AND EXAM_END_DATE) or   (? <= EXAM_START_DATE and ? >= EXAM_END_DATE)  ) ;");
					
					stmt.setLong(1, sectionIds);
					stmt.setDate(2, startDate);
					stmt.setDate(3, endDate);
					stmt.setDate(4, startDate);
					stmt.setDate(5, endDate);
					
					ResultSet rs=stmt.executeQuery();
					
					while(rs.next()){
						if(examSchedulePojo==null){
							examSchedulePojo = new ExamSchedulePojo();
						}
						examSchedulePojo.setExamDetails(rs.getString("EXAM_DETAILS"));
						examSchedulePojo.setExamStartDate(rs.getDate("EXAM_START_DATE"));
						examSchedulePojo.setExamEndDate(rs.getDate("EXAM_END_DATE"));
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
			
			return examSchedulePojo;
			
	}	
	
	public List<EvaluationScoresPojo> getAvailableStudentsListForEvaluationScoresDataTable(int startRow, int endRow, String defaultQuery){
		List<EvaluationScoresPojo> availableStudentsForEvaluation = new ArrayList<EvaluationScoresPojo>();
		
		try {
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(defaultQuery+" limit "+endRow+" OFFSET "+startRow+";");
		        while(rs.next()){
		        	EvaluationScoresPojo evaluationScoresPojo = new EvaluationScoresPojo();
		        	
		        	evaluationScoresPojo.setKey(rs.getLong("STUDENT_ID"));
		        	evaluationScoresPojo.setStudentName(rs.getString("STUDENT_FIRST_NAME")+" "+rs.getString("STUDENT_LAST_NAME"));
		        	evaluationScoresPojo.setSubjectScore(rs.getBigDecimal("SUBJECT_SCORE"));
		        	evaluationScoresPojo.setComments(rs.getString("COMMENTS"));
		        	evaluationScoresPojo.setSchoolEvaluationScoresId(rs.getLong("SCHOOL_EVALUATION_SCORES_ID"));
		        	evaluationScoresPojo.setSchoolEvaluationScoresDetailsId(rs.getLong("SCHOOL_EVALUATION_SCORES_DETAILS_ID"));
		        	evaluationScoresPojo.setRecordUpdatedStatus(false);
		        	
		        	availableStudentsForEvaluation.add(evaluationScoresPojo);
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
		
		return availableStudentsForEvaluation;
	}
}
