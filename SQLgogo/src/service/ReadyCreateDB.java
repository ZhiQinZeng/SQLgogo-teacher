package service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public  class ReadyCreateDB {
	// JDBC driver name and database URL
	
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "970128";
	   
	 
	public  String create(String dbname) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	     // System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4: Execute a query
	      //System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	      String sql = "create database "+dbname;
	      stmt.executeUpdate(sql);
	      String sql1 = "use "+dbname;
	      stmt.executeUpdate(sql1);
	   }catch(SQLException se){
		  // se.printStackTrace();
	      //Handle errors for JDBC
		   StringWriter sw = new StringWriter();  
		   PrintWriter pw = new PrintWriter(sw);  
		   se.printStackTrace(pw);  
		   String msg=sw.toString();
	      String [] str = msg.split("\n");
	      String str1 = str[0].substring(0,str[0].indexOf(":"));
	      //System.out.println(str1[0]);
	      String str2 = str[0].substring(str[0].indexOf(":")+1);
	      return str2;
	      //System.out.println(str2);
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	  // System.out.println("Goodbye!");
	   return "dbyes";
	}
	}

