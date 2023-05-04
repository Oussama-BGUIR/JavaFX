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
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author user
 */
public class ServiceRdvCRUD implements RdvCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    @Override
        public void ajouterRdv(Rdv R) {
     
      try {
            String req = "INSERT INTO `rdv`(`nom_nutritioniste_id`,`nom`,`prenom`, `email`,`date`,`numtel`,`description`) VALUES ( '"+R.getNom_nutritioniste_id()+"','"+R.getNom()+"', '"+R.getPrenom()+"', '"+R.getEmail()+"', '"+R.getDate()+"','"+R.getNumtel()+"' , '"+R.getDescription()+"' )";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Rdv ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Rdv non ajoutée");
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
             R.setNom_nutritioniste_id(RS.getInt(2));
             R.setNom(RS.getString(3));
             R.setPrenom(RS.getString(4));
             R.setEmail(RS.getString(5));
             R.setDate(RS.getDate(6));
             R.setNumtel(RS.getInt(7));
             R.setDescription(RS.getString(8));
         
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public void supprimerRdv(int id) {
        try {
            String req = "DELETE FROM `rdv` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("le rendez-vous est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
     public void modifierRdv(Rdv R) {
       try {
            String req = "UPDATE rdv SET nom = nom_nutritioniste_id = '" + R.getNom_nutritioniste_id()+"','" + R.getNom()+ "', prenom = '" + R.getPrenom()+ "', email = '" + R.getEmail()+ "', date = '" + R.getDate()+ "', numtel = '" + R.getNumtel()+"', Description = '" + R.getDescription()+ "' WHERE rdv.`id` = " + R.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Rdv modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   /*  public List<Rdv> findByDateAndNutritionnisteId(Date date, int nom_nutritionniste_id) {
    List<Rdv> rdvs = new ArrayList<>();
    try {
        String req = "SELECT * FROM rdv WHERE date=? AND nom_nutritioniste_id=?";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setDate(1, date);
        ps.setInt(2, nom_nutritionniste_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Rdv r = new Rdv();
            r.setId(rs.getInt("id"));
            r.setNom(rs.getString("nom"));
            r.setPrenom(rs.getString("prenom"));
            r.setEmail(rs.getString("email"));
            r.setDate(rs.getDate("date"));
            r.setDescription(rs.getString("description"));
            r.setNumtel(rs.getInt("numtel"));
            r.setNom_nutritioniste_id(rs.getInt("nom_nutritioniste_id"));
            rdvs.add(r);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return rdvs;
}*/

    
}

     
    
   