package com.smartSchoolService.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.smartSchoolService.dao.DatabaseUtility;

public class LoginHelper {

	
	public String validateLogin(String userName,String password){
		String status = "fail";
		try {
			String hashedPassword=SmartSchoolHash.customHashing(password);
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			Statement stmt = null;
			try{
				
				stmt = con.createStatement();
		        ResultSet rs = stmt.executeQuery( "SELECT DISPLAY_NAME,USER_ROLE_TYPE FROM LOGIN_DETAILS where USER_NAME='"+userName+"' and PASSWORD = '"+hashedPassword+"';" );
		        while ( rs.next() ) {
		        	String displayName=rs.getString("DISPLAY_NAME");
		        	String userRoleType=rs.getString("USER_ROLE_TYPE");
		        	status="success%%"+displayName+"%%"+userRoleType;
		        }
		        rs.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				databaseUtility.closeConnection(con);
			}
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
}
