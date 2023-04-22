/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;
import elitegymcenter.entities.Planning;


import elitegymcenter.entities.Cours;

import elitegymcenter.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import elitegymcenter.interfaces.CoursCRUD;

/**
 *
 * @author helmi
 */
public class ServiceCoursCRUD implements CoursCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    @Override

    
    public void ajouterCours(Cours C) {
        try {
            String req = "INSERT INTO `cours`( `planning_id` ,`nom`, `duree`, `salle` ) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, C.getPlanning_id());
            preparedStatement.setString(2, C.getNom());
            preparedStatement.setString(3, C.getDuree());
            preparedStatement.setString(4, C.getSalle());
            preparedStatement.executeUpdate();
            System.out.println("Cours ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Malheureusement le Cours n'est pas ajouté .. ");
            ex.printStackTrace();
        }
    }
       
    
      @Override
    public List<Cours> afficherCours() {
       List<Cours> list = new ArrayList<>();
        try {
            String req = "Select * from cours";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Cours C = new Cours();
             C.setId(RS.getInt(1));
             C.setPlanning_id(RS.getInt(2));
             C.setNom(RS.getString(3));
             C.setDuree(RS.getString(4));
             C.setSalle(RS.getString(5));
             list.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public void supprimerCours(int id) {
        try {
            String req = "DELETE FROM `cours` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("le Cours est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
  public void modifierCours(Cours C) {
        try {
            String req = "UPDATE `Cours` SET `planning_id` = ?,`nom` = ?, `duree` = ?, `salle` = ? WHERE `cours`.`id` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, C.getPlanning_id());
            preparedStatement.setString(2, C.getNom());
            preparedStatement.setString(3, C.getDuree());
            preparedStatement.setString(4, C.getSalle());
            preparedStatement.setInt(5, C.getId());

            preparedStatement.executeUpdate();
            System.out.println("Le Cours est modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
