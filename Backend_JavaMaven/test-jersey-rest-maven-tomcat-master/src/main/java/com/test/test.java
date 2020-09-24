package com.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class test {
	public static void main(String[] args) {
		 
        Connection conn = null;
 
        try {
 
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CFM";
            String user = "test";
            String pass = "test";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
 
            Statement statement = conn.createStatement();
            
            ResultSet results = statement.executeQuery("SELECT * FROM TABLE_NAME");
            
            System.out.println("----------------");
            
            JSONArray lockdetail1 = new JSONArray();
            
            JSONObject lockdetail = new JSONObject();
            
            while (results.next()) {
            	 
            	
				lockdetail.put("id", results.getString("id"));
				lockdetail.put("avalue", results.getString("avalue"));
				lockdetail.put("dimension", results.getString("dimension"));
				
				lockdetail1.add(lockdetail);
				
            	}
            
            System.out.println(lockdetail1.size());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}//end JDBCExample