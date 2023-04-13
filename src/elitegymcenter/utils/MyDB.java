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
 * @author user
 */
public class MyDB {
    
   private static Connection conn; //DB Credations
    
String url = "jdbc:mysql://localhost:3306/pidev3a45";
String user = "root";
String pwd = "";
private static MyDB instance;
    private MyDB() {
        try {
            conn=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!!!");
        } catch (SQLException ex) {
            System.out.println("Prebleme de connexion");        }
    }

    public static MyDB getInstance() {
        if(instance==null){
            instance= new MyDB();
        }
        return instance;
    }
 
    
    public Connection getConn(){
        return MyDB.getInstance().conn;
    }
    
}
