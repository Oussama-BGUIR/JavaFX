/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;


import elitegymcenter.entities.Rdv;

import elitegymcenter.utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import elitegymcenter.interfaces.RdvCRUD;
import elitegymcenter.interfaces.RdvFrontCRUD;
import java.sql.PreparedStatement;

/**
 *
 * @author user
 */
public class ServiceRdvFrontCRUD implements RdvFrontCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    @Override
        public void ajouterRdv(Rdv R) {
        try {
            String req = "INSERT INTO `rdv`( `nom`,`prenom`, `email`,`date`,`description`, `numtel`) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, R.getNom());
            preparedStatement.setString(2, R.getPrenom());
           preparedStatement.setString(3, R.getEmail());
           preparedStatement.setDate(4, R.getDate());
           preparedStatement.setString(5, R.getDescription());
             preparedStatement.setInt(6, R.getNumtel());
           //   preparedStatement.setInt(7, R.getNom_nutritioniste_id());
            
            preparedStatement.executeUpdate();
            System.out.println("Rdv ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Malheureusement le rdv n'est pas ajouté .. ");
            ex.printStackTrace();
        }
    }
    
    
      @Override
    public List<Rdv> afficherRdv() {
       List<Rdv> list = new ArrayList<>();
        try {
            String req = "Select * from rdv";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Rdv R = new Rdv();
             R.setId(RS.getInt(1));
        //     R.setNom_nutritioniste_id(RS.getInt(2));
             R.setNom(RS.getString(2));
             R.setPrenom(RS.getString(3));
             R.setEmail(RS.getString(4));
             R.setDate(RS.getDate(5));
             R.setDescription(RS.getString(6));
             R.setNumtel(RS.getInt(7));
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
    
}

