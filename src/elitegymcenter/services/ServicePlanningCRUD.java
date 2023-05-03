/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;

import elitegymcenter.entities.Planning;

import elitegymcenter.utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import elitegymcenter.interfaces.PlanningCRUD;

/**
 *
 * @author ousam
 */
public class ServicePlanningCRUD implements PlanningCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConnection();
    
    
    
    
    @Override
   
        public void ajouterPlanning(Planning P) throws SQLException{
        try {
            String req = "INSERT INTO `planning`( `semaine`, `description`,`image_code_qr`) VALUES (?, ?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, P.getSemaine());
            preparedStatement.setString(2, P.getDescription());
            preparedStatement.setString(3, P.getImage_code_qr());

            preparedStatement.executeUpdate();
            System.out.println("Planning ajoute© avec succÃ¨s !!!");
        }catch (SQLException ex) {
      	   System.out.println("errr "+ex);
        	
        } 
    }
       

          @Override
          public ObservableList<Planning>  afficherPlanning() {
        	  ObservableList<Planning> plannings = FXCollections.observableArrayList();
        	    try {
        	        PreparedStatement preparedStatement =  conn.prepareStatement("SELECT * FROM `planning`");
        	        ResultSet resultSet = preparedStatement.executeQuery();
        	        while (resultSet.next()) {
        	            int id = resultSet.getInt("id");
        	            String semaine = resultSet.getString("semaine");
        	            String description = resultSet.getString("description");
        	            String image = resultSet.getString("image_code_qr");
        	            Planning planning = new Planning(id, semaine, description,image);
        	            plannings.add(planning);
        	            System.out.println(planning.toString());
        	        }
        	    } catch (SQLException e) {
        	        e.printStackTrace();
        	    }
        	    return plannings;
        	}
     
    
  

    @Override
    public void supprimerPlanning(int id) {
        try {
            String req = "DELETE FROM `planning` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("le planning est supprimÃ© !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
     @Override
  
     public void modifierPlanning(Planning planning) {
    	    try {
    	        String req = "UPDATE `planning` SET `semaine` = ?, `description` = ? WHERE `id` = ?";
    	        PreparedStatement preparedStatement = conn.prepareStatement(req);
    	        preparedStatement.setString(1, planning.getSemaine());
    	        preparedStatement.setString(2, planning.getDescription());
    	        preparedStatement.setInt(3, planning.getId());
    	        preparedStatement.executeUpdate();
    	        System.out.println("Le planning a été modifié !");
    	    } catch (SQLException ex) {
    	        System.out.println(ex.getMessage());
    	    }
    	}
    

    
}



