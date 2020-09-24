package com.test;
 
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

@Path("/testservice")
public class TestService {

	@POST
	@Path("/test12/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
  public String  getTestService() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 JSONArray lockdetail1 = new JSONArray();
        try {
            String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=CFM";
            String user = "test";
            String pass = "test";
            conn = DriverManager.getConnection(dbURL, user, pass);
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM TABLE_NAME");
            JSONObject lockdetail = new JSONObject();

        	while (results.next()) {
				LinkedHashMap lhm = new LinkedHashMap();
				for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
					String field = results.getMetaData().getColumnName(i);
					lhm.put(field, results.getString(field));
				}
				lockdetail1.add(lhm);
			}
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conn.close();
        } 
		return lockdetail1.toJSONString();
  }
  
}