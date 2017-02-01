package com.smartSchoolService.pojo;

import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		/*String str="12334";
		if (str.matches("[0-9]+") && str.length() > 2) {
			System.out.println("number");
		}
		else {
			System.out.println("char");
		}*/
		
		Long noOfSlotsUsed= new Long(2);
		
		if(noOfSlotsUsed != null){
			
			//Statement stmt1 = con.createStatement();
			
			String query = "SELECT ";
			String fromTables=" FROM SECTION_SUBJECT_TIME_TABLE sec,";
			String whereClause = " WHERE SECTION_ID = "+23+" AND ";
			
			for (int i =1 ; i<=noOfSlotsUsed; i++){
				query = query+"sec.SLOT"+i+" , sub"+i+".subject_name SLOT"+i+"_NAME ,";
				fromTables=fromTables+" SUBJECTS_DETAILS sub"+i+",";
				whereClause=whereClause+" sec.slot"+i+"= sub"+i+".subject_id AND";
			}
			
			query = query.substring(0, query.length()-1);
			fromTables = fromTables.substring(0, fromTables.length()-1);
			whereClause=whereClause.substring(0,whereClause.length()-3);
			query= query+fromTables+ whereClause;
			
			
			System.out.println(query);
		}
		
	}

}
