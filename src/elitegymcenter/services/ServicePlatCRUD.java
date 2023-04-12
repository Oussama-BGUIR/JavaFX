/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;
import elitegymcenter.interfaces.PlatCRUD;


import elitegymcenter.entities.Plat;

import elitegymcenter.utils.MyDB;
import java.sql.Connection;
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
            String req = "INSERT INTO `plat`( `menu_id` , `nom`, `description`, `disponibilite`, `calorie`, `image` , `prix` ) VALUES ('"+P.getMenu_id()+"','"+P.getNom()+"','"+P.getDescription()+"','"+P.getDisponibilite()+"','"+P.getCalorie()+"','"+P.getImage()+"','"+P.getPrix()+"')";

            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Plat est ajouté avec success !!!");
        } catch (SQLException ex) {
            System.out.println("malheureusement le plat n'est pas ajouté .. ");
    
    
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
             P.setDisponibilite(RS.getBoolean(5));
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
            
            String req = "UPDATE `Plat` SET `menu_id` = '" + P.getMenu_id()+ "', `nom` = '" + P.getNom()+ "', `description` = '" + P.getDescription()+ "', `disponibilite` = '" + P.getDisponibilite()+ "', `calorie` = '" + P.getCalorie()+ "', `image` = '" + P.getImage()+ "', `prix` = '" + P.getPrix()+ "' WHERE `plat`.`id` = " + P.getId();

            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("le plat à été modifié avec success !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
