/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ousam
 */
public class MyDB {
	private static MyDB instance;

	
	 private static final String URL = "jdbc:mysql://localhost:3306/pidev3a451";
	 private static final String USER = "root";
	 private static final String PASSWORD = "";

	 private Connection cnx;

	    private MyDB() {
	        try {
	            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Connected!");
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }

	    public static MyDB getInstance() {
	        if (instance == null) {
	            instance = new MyDB();
	        }
	        return instance;
	    }

	    public Connection getConnection() {
	        return cnx;
	    }


	    public void closeConnection() {
	        try {
	            cnx.close();
	            System.out.println("Connection closed!");
	        } catch (SQLException ex) {
	            System.err.println(ex.getMessage());
	        }
	    }
    
}
