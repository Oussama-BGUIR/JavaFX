/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;

import elitegymcenter.entities.Abonnement;
import elitegymcenter.interfaces.AbonnementCRUD;
import elitegymcenter.utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author shini
 */
public class Abonnement1CRUD implements AbonnementCRUD
{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    @Override
    public void ajouterAbonnement(Abonnement E) {
       try {
            String req = "INSERT INTO `abonnement`( `nom`, `prenom`, `numero`, `email`, `type`) VALUES ('"+E.getNom()+"','"+E.getPrenom()+"','"+E.getNumero()+"','"+E.getEmail()+"','"+E.getType()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Abonnement ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Abonnement non ajouté");
    
    
}
    }
    @Override
    public List<Abonnement> afficherAbonnement() {
       List<Abonnement> list = new ArrayList<>();
        try {
            String req = "Select * from abonnement";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Abonnement E = new Abonnement();
             
             E.setId(RS.getInt(1));
             E.setNom(RS.getString(2));
              E.setType(RS.getString(3));
             E.setPrenom(RS.getString(4));
             E.setNumero(RS.getInt(5));
             E.setEmail(RS.getString(6));
            
         
             list.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public void supprimerAbonnement(int id) {
        try {
            String req = "DELETE FROM `abonnement` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Abonnement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifierAbonnement(Abonnement E) {
        try {
            String req = "UPDATE `Abonnement` SET `nom` = '" + E.getNom()+ "', `type` = '" + E.getType()+"',  `prenom` = '" + E.getPrenom()+ "', `numero` = '" + E.getNumero()+ "', `email` = '" + E.getEmail()+ "' WHERE `abonnement`.`id` = " + E.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Abonnement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
