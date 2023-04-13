/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;
import elitegymcenter.entities.Menu;
import elitegymcenter.interfaces.PlatCRUD;


import elitegymcenter.entities.Plat;

import elitegymcenter.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ousam
 */
public class ServicePlatCRUD implements PlatCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    @Override

    
    public void ajouterPlat(Plat P) {
        try {
            String req = "INSERT INTO `plat`( `menu_id` ,`nom`, `description`, `disponibilte`, `calorie`, `image`, `prix` ) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, P.getMenu_id());
            preparedStatement.setString(2, P.getNom());
            preparedStatement.setString(3, P.getDescription());
            preparedStatement.setBoolean(4, P.getDisponibilte());
            preparedStatement.setInt(5, P.getCalorie());
            preparedStatement.setString(6, P.getImage());
            preparedStatement.setInt(7, P.getPrix());
            preparedStatement.executeUpdate();
            System.out.println("Plat ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Malheureusement le plat n'est pas ajouté .. ");
            ex.printStackTrace();
        }
    }
       
    
      @Override
    public List<Plat> afficherPlat() {
       List<Plat> list = new ArrayList<>();
        try {
            String req = "Select * from plat";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Plat P = new Plat();
             P.setId(RS.getInt(1));
             P.setMenu_id(RS.getInt(2));
             P.setNom(RS.getString(3));
             P.setDescription(RS.getString(4));
             P.setDisponibilte(RS.getBoolean(5));
             P.setCalorie(RS.getInt(6));
             P.setImage(RS.getString(7));
             P.setPrix(RS.getInt(8));
             list.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public void supprimerPlat(int id) {
        try {
            String req = "DELETE FROM `plat` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("le plat est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
  public void modifierPlat(Plat P) {
        try {
            String req = "UPDATE `Plat` SET `menu_id` = ?,`nom` = ?, `description` = ?, `disponibilte` = ?, `calorie` = ?, `image` = ? , `prix` = ? WHERE `plat`.`id` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, P.getMenu_id());
            preparedStatement.setString(2, P.getNom());
            preparedStatement.setString(3, P.getDescription());
            preparedStatement.setBoolean(4, P.getDisponibilte());
            preparedStatement.setInt(5, P.getCalorie());
            preparedStatement.setString(6, P.getImage());
            preparedStatement.setInt(7, P.getPrix());
            preparedStatement.setInt(8, P.getId());

            preparedStatement.executeUpdate();
            System.out.println("Le plat est modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
