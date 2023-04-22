/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;

import elitegymcenter.entities.Planning;

import elitegymcenter.utils.MyDB;
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
    Connection conn = MyDB.getInstance().getConn();
    
    
    
    
    @Override
    

    
        public void ajouterPlanning(Planning P) {
        try {
            String req = "INSERT INTO `planning`( `semaine`, `description`) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, P.getSemaine());
            preparedStatement.setString(2, P.getDescription());

            preparedStatement.executeUpdate();
            System.out.println("Planning ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Malheureusement le planning n'est pas ajouté .. ");
            ex.printStackTrace();
        }
    }
       

          @Override
        public List<Planning> afficherPlanning() {
        List<Planning> list = new ArrayList<>();
        try {
            String req = "Select * from planning";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Planning P = new Planning();
             P.setId(RS.getInt(1));
             P.setSemaine(RS.getString(2));
             P.setDescription(RS.getString(3));
             list.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
  

    @Override
    public void supprimerPlanning(int id) {
        try {
            String req = "DELETE FROM `planning` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("le planning est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
     @Override
  
    public void modifierPlanning(Planning P) {
        try {
            String req = "UPDATE `Planning` SET `semaine` = ?, `description` = ? WHERE `planning`.`id` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, P.getSemaine());
            preparedStatement.setString(2, P.getDescription());
            preparedStatement.setInt(3, P.getId());
            preparedStatement.executeUpdate();
            System.out.println("Le Planning est modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    
}



