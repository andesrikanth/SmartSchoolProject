package com.smartSchoolService.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseUtility {

	
	/*public static void main( String args[] )
    {
		DatabaseUtility databaseUtility = new DatabaseUtility();
      Connection c = null;
      Statement stmt = null;
      try {
      
    	 c=databaseUtility.getConnection();
        stmt = c.createStatement();
        String userName = "srikanth";
        String hashedPassword = "5a15878df5a959e8e1f5c28bb6354a0b9722838d";
        ResultSet rs = stmt.executeQuery( 
        		"SELECT DISPLAY_NAME FROM LOGIN_DETAILS where USER_NAME='"+userName+"' and PASSWORD = '"+hashedPassword+"';" );
        while ( rs.next() ) {
           int id = rs.getInt("USER_ID");
           String  name = rs.getString("USER_NAME");
           String  pwd = rs.getString("PASSWORD");
           System.out.println( "ID = " + id );
           System.out.println( "NAME = " + name );
           System.out.println( "pwd = " + pwd );
           System.out.println();
        }
        rs.close();
        
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        System.exit(0);
      }
      finally{
    	  try {
			stmt.close();
			databaseUtility.closeConnection(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
          
      }
      System.out.println("Operation done successfully");
    }
	*/
	public Connection getConnection(){
		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
	        con = DriverManager
	           .getConnection("jdbc:postgresql://localhost:5432/postgres",
	           "postgres", "postgres");
	        con.setAutoCommit(false);
	        System.out.println("Opened database successfully");
	        
		}
		 catch ( Exception e ) {
		        System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		        System.exit(0);
		 }
		 
		return con;
	}
	
	public void closeConnection(Connection con){
		try {
			con.close();
			System.out.println("Closed Database successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
