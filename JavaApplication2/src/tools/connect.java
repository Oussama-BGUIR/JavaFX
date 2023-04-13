/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fayechi
 */
public class connect {
   public final static  String url ="jdbc:mysql://localhost:3306/pidev3a45";
   public final static String user="root";
   public final static String pwd="";
    private Connection cnx;
    public static connect ct;

    private connect() {
       try {
           cnx = DriverManager.getConnection(url, user, pwd);
           System.out.println("Connection etablie !!");
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
    }
    public static connect getInstance(){
        if(ct==null){
            ct = new connect();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
   
}
