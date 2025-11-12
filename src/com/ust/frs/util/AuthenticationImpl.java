package com.ust.frs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ust.frs.bean.CredentialsBean;

public class AuthenticationImpl implements Authentication {
	public static Connection con = getCon();
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","pass@word1");	
		}
		catch(ClassNotFoundException cnf) {
			System.out.println(cnf);
		}
		catch(SQLException sql) {
			System.out.println(sql);
		}
		return con;
	}

    @Override
    public boolean authenticate(CredentialsBean cb) {
    	    boolean isValid = false; // Variable to hold the validation result
    	    try {

    	        ps = con.prepareStatement("SELECT * FROM user_credentials WHERE Userid = ? AND Password = ?");
    	        
    	        ps.setString(1, cb.getUserID());   
    	        ps.setString(2, cb.getPassword()); 
    	        
    	        rs = ps.executeQuery();
    	        
    	        if (rs.next()) {
    	            isValid = true;
    	        }
    	    } catch (SQLException sql) {
    	        System.out.println(sql);
    	    }
    	    return isValid;  

    }

    @Override
    public String authorize(String userType) {
        if (userType.equalsIgnoreCase("A")) return "Administrator";
        if (userType.equalsIgnoreCase("C")) return "Customer";
        return "Invalid";
    }

    @Override
    public boolean changeLoginStatus(CredentialsBean credentials, int status) {
        boolean isUpdated = false;
        try {
            String query = "UPDATE user_credentials SET Loginstatus = ? WHERE Userid = ?";
            ps = con.prepareStatement(query);
            
            ps.setInt(1, status);
            ps.setString(2, credentials.getUserID());
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                isUpdated = true;
            }
        } catch (SQLException sql) {
            System.out.println(sql);
        }
        return isUpdated;
    }
}
