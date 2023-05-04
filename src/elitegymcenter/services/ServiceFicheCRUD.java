/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;

import elitegymcenter.entities.Fiche;

import elitegymcenter.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import elitegymcenter.interfaces.FicheCRUD;

/**
 *
 * @author user
 */
public class ServiceFicheCRUD implements FicheCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();

    
    
    @Override
        public void ajouterFiche(Fiche F) {
        try {
            String req = "INSERT INTO `fiche`( `nom`,`prenom`, `email`, `numtel`,`description`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, F.getNom());
            preparedStatement.setString(2, F.getPrenom());
           preparedStatement.setString(3, F.getEmail());
            preparedStatement.setInt(4, F.getNumtel());
           preparedStatement.setString(5, F.getDescription());
            
            preparedStatement.executeUpdate();
            System.out.println("Fiche ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Malheureusement la fiche n'est pas ajouté .. ");
            ex.printStackTrace();
        }
    }
       

          @Override
        public List<Fiche> afficherFiche() {
        List<Fiche> list = new ArrayList<>();
        try {
            String req = "Select * from fiche";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Fiche F = new Fiche();
             F.setId(RS.getInt(1));
             F.setNom(RS.getString(2));
             F.setPrenom(RS.getString(3));
             F.setEmail(RS.getString(4));
             F.setNumtel(RS.getInt(5));
             F.setDescription(RS.getString(6));
             
             list.add(F);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
  

    @Override
    public void supprimerFiche(int id) {
        try {
            String req = "DELETE FROM `fiche` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("la fiche est supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
     @Override
     public void modifierFiche(Fiche F) {
        try {
            
            String req = "UPDATE `Fiche` SET `nom` = '" + F.getNom()+ "', `prenom` = '" + F.getPrenom()+ "', `email` = '" + F.getEmail()+ "', `numtel`= '" +F.getNumtel()+"', `description` = '" + F.getDescription()+ "' WHERE `fiche`.`id` = " + F.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Fiche modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//public void modifierFiche(Fiche F) {
  //      try {
    //        String req = "UPDATE Fiche SET nom` = ?, prenom = ?, email = ?, description = ?, numtel = ? WHERE fiche.`id` = ?";
      //      PreparedStatement preparedStatement = conn.prepareStatement(req);
        //    preparedStatement.setString(1, F.getNom());
          //  preparedStatement.setString(2, F.getPrenom());
           // preparedStatement.setString(3, F.getEmail());
    //        preparedStatement.setString(4, F.getDescription());
      //       preparedStatement.setInt(5, F.getNumtel());
          //  preparedStatement.setInt(6, F.getId());
            
            
          //  preparedStatement.executeUpdate();
        //    System.out.println("La fiche est modifiée !");
      //  } catch (SQLException ex) {
        //    System.out.println(ex.getMessage());
       // }
    }
    
    
    

  



