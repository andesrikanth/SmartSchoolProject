package com.smartSchoolService.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.smartSchoolService.dao.DatabaseUtility;
import com.smartSchoolService.mail.MailSender;

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
		        ResultSet rs = stmt.executeQuery( "SELECT DISPLAY_NAME, USER_ROLE_TYPE, PWD_RESET_FLAG FROM LOGIN_DETAILS where USER_NAME='"+userName+"' and PASSWORD = '"+hashedPassword+"';" );
		        while ( rs.next() ) {
		        	String displayName=rs.getString("DISPLAY_NAME");
		        	String userRoleType=rs.getString("USER_ROLE_TYPE");
		        	String pwdResetFlag=rs.getString("PWD_RESET_FLAG");
		        	if(pwdResetFlag != null && pwdResetFlag.equals("Y")){
		        		status="reset%%"+displayName+"%%"+userRoleType+"%%"+pwdResetFlag;
		        	}
		        	else {
		        		status="success%%"+displayName+"%%"+userRoleType+"%%"+pwdResetFlag;
		        	}
		        	
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
	
	public String resetUserPassword(String userName,String password){
		String status = "fail";
		try {
			String hashedPassword=SmartSchoolHash.customHashing(password);
			DatabaseUtility databaseUtility =new DatabaseUtility();
			Connection con=databaseUtility.getConnection();
			PreparedStatement stmt = null;
			java.sql.Date date = new java.sql.Date(new Date().getTime());
			try{
				
				stmt = con.prepareStatement( "UPDATE LOGIN_DETAILS SET PASSWORD=? ,  LAST_UPDATE_DATE = ? , PWD_RESET_FLAG=? WHERE USER_NAME = ? ;" );
				stmt.setString(1,hashedPassword );
				stmt.setTimestamp(2, new Timestamp(date.getTime()));
				stmt.setString(3,"N");
				stmt.setString(4,userName );
				
		        int out=stmt.executeUpdate();
				
		        if(out == 1){
		        	status="success";
		        }
		        
		        con.commit();
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
	
	
	public String submitForgotPassword(String userName, String registeredEmailId, String registeredPhoneNo){
		String status = "Temporary Password generation failed! Please contact Administrator for your temporary password.";
		
		boolean validationStatus = false;
		
		try {
			String emailId=null;
			String phoneNo=null;
			
			
			DatabaseUtility databaseUtility =new DatabaseUtility();
			try {
				
				Connection con=databaseUtility.getConnection();
				Statement stmt = null;
				try{
					
					stmt = con.createStatement();
			        ResultSet rs = stmt.executeQuery( "SELECT EMAIL, PHONE_NO FROM LOGIN_DETAILS where USER_NAME='"+userName+"';" );
			        while ( rs.next() ) {
			        	emailId=rs.getString("EMAIL");
			        	phoneNo=rs.getString("PHONE_NO");
			        }
			        rs.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					databaseUtility.closeConnection(con);
				}
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
			
			if(emailId == null && phoneNo == null){
				status="Seems like you don't have an email and phone number registered with us. Please contact Administrator for your temporary password.";
				return status;
			}
			
			if(emailId != null && registeredEmailId != null && registeredEmailId.equalsIgnoreCase(emailId)){
				validationStatus=true;
			}
			else if(phoneNo != null && registeredPhoneNo != null && registeredPhoneNo.equalsIgnoreCase(phoneNo)){
				validationStatus=true;
			}
			
			if(!validationStatus){
				String emailPhoneValidationStatus="Entered email or phone number is incorrect. Please provide registered email or phone number to reset the password.";
				return emailPhoneValidationStatus;
			}
			
			if(emailId != null){
				
				RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
				String tempPassword = randomPasswordGenerator.generateNewPassword();
				String hashedPassword=SmartSchoolHash.customHashing(tempPassword);
				
				
				System.out.println("random password "+tempPassword);
				
				//Sending email to the users with the temporary password.
				/*MailSender mailSender = new MailSender();
				 ArrayList<String> toList = new ArrayList<String>();
				 toList.add("srikanthande1990@gmail.com");
				 toList.add("lovelysrikanthande@gmail.com");
				mailSender.sendMail(toList,null,null,"Test from UI","Test from UI");
				*/
				
				Connection con1=databaseUtility.getConnection();
				PreparedStatement stmt1 = null;
				java.sql.Date date = new java.sql.Date(new Date().getTime());
				try{
					
					stmt1 = con1.prepareStatement( "UPDATE LOGIN_DETAILS SET PASSWORD = ? ,  LAST_UPDATE_DATE = ? , PWD_RESET_FLAG = ? WHERE USER_NAME = ? ;" );
					stmt1.setString(1,hashedPassword );
					stmt1.setTimestamp(2, new Timestamp(date.getTime()));
					stmt1.setString(3,"Y");
					stmt1.setString(4,userName );
					
			        int out=stmt1.executeUpdate();
					
			        if(out == 1){
			        	status="Temporary Password has been sent to your registered email id. Please check your email and login back.";
			        }
			        
			        con1.commit();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					databaseUtility.closeConnection(con1);
				}
			}
			else {
				status="Seems like you don't have an email registered with us. Please contact Administrator for your temporary password.";
			}
			
			
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return status;
	}
}
