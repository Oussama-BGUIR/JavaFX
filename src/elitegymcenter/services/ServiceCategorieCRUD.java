/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;

import elitegymcenter.interfaces.CategorieCRUD;
import elitegymcenter.entities.Categorie;
import elitegymcenter.entities.Produit;

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
 * @author LENOVO
 */
public class ServiceCategorieCRUD implements CategorieCRUD<Categorie>{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    

    @Override
        public void ajouterCategorie(Categorie C) {
        try {
            String req = "INSERT INTO `categorie`( `nom`, `description`) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, C.getNom());
            preparedStatement.setString(2, C.getDescription());
            preparedStatement.executeUpdate();
            System.out.println("Categorie ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Categorie non ajouté  ");
            ex.printStackTrace();
        }
    }
       

          @Override
        public List<Categorie> afficherCategorie() {
        List<Categorie> list = new ArrayList<>();
        try {
            String req = "Select * from categorie";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Categorie C = new Categorie();
             C.setId(RS.getInt(1));
             C.setNom(RS.getString(2));
             C.setDescription(RS.getString(3));
             list.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
  

    @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM `categorie` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("La Categorie est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public void modifierCategorie(Categorie C) {
        try {
            String req = "UPDATE `Categorie` SET `nom` = ?, `description` = ? WHERE `categorie`.`id` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, C.getNom());
            preparedStatement.setString(2, C.getDescription());
            preparedStatement.setInt(3, C.getId());
            preparedStatement.executeUpdate();
            System.out.println("La categorie est modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
 

    @Override
public Categorie get(int id) {
Categorie categorieProduit = null;
try {
String req = "SELECT * FROM categorie WHERE id=?";
PreparedStatement ps = conn.prepareStatement(req);
ps.setInt(1, id);
ResultSet rs = ps.executeQuery();
if (rs.next()) {
categorieProduit = new Categorie(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
}
} catch (SQLException exception) {
System.out.println(exception.getMessage());
}
return categorieProduit;
}
}
    


