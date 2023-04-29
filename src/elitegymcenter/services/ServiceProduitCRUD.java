/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;
import elitegymcenter.entities.Categorie;
import elitegymcenter.interfaces.ProduitCRUD;


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
public class ServiceProduitCRUD implements ProduitCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    @Override
      public void ajouterProduit(Produit P) {
        try {
            String req = "INSERT INTO `produit`( `nom`,`prix`, `description`,`image`) VALUES (?, ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, P.getNom());
             preparedStatement.setInt(2, P.getPrix());
            preparedStatement.setString(3, P.getDescription());
           
            preparedStatement.setString(4, P.getImage());
            preparedStatement.executeUpdate();
            System.out.println("Produit ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Produit non ajouté  ");
            ex.printStackTrace();
        }
    }
    ServiceCategorieCRUD serviceCategorieCRUD= new ServiceCategorieCRUD();
      @Override
     public List<Produit> afficherProduit() {
        List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Produit P = new Produit();
             P.setId(RS.getInt(1));
           //  P.setCategorie_id(RS.getInt(2));
             // P.setCategory(RS.getString(2));
             P.setCategory(serviceCategorieCRUD.get(RS.getInt(1)));
             P.setNom(RS.getString(3));
             P.setPrix(RS.getInt(4));
             P.setDescription(RS.getString(5));
             P.setImage(RS.getString(6));
             list.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public void supprimerProduit(int id) {
        try {
            String req = "DELETE FROM `produit` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
    public void modifierProduit(Produit P) {
        try {
            String req = "UPDATE `Produit` SET `categorie_id`= ? ,`nom` = ?, `prix` = ?, `description` = ?, `image` = ? WHERE `produit`.`id` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
               preparedStatement.setString(1, P.getCategorie_id());
            preparedStatement.setString(2, P.getNom());
            preparedStatement.setInt(3, P.getPrix());
            preparedStatement.setString(4, P.getDescription());
            preparedStatement.setString(5, P.getImage());
            preparedStatement.setInt(6, P.getId());
            
            
            preparedStatement.executeUpdate();
            System.out.println("Le produit est modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
       @Override
    public void supprimerPanier(int id) {
          try {
            String req = "DELETE FROM commande WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
