/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;



import elitegymcenter.entities.Offre;
import elitegymcenter.interfaces.OffreCRUD;
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
public class Offre1CRUD implements OffreCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    @Override
    public void ajouterOffre(Offre R) {
       try {
            String req = "INSERT INTO `offre`( `abonnement_nom_id`, `points`, `prix`, `pourcentage`, `date_debut`, `date_fin`) VALUES ('"+R.getAbonnement_nom_id()+"', '"+R.getPoints()+"', '"+R.getPrix()+"', '"+R.getPourcentage()+"', '"+R.getDate_debut()+"', '"+R.getDate_fin()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Offre ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Offre non ajoutée");
    
    
}
    }
     @Override
    public List<Offre> afficherOffre() {
       List<Offre> list = new ArrayList<>();
        try {
            String req = "Select * from offre";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Offre R = new Offre();
             R.setId(RS.getInt(1));
             R.setAbonnement_nom_id(RS.getInt(2));
             R.setPoints(RS.getInt(3));
             R.setPrix(RS.getDouble(4));
             R.setPourcentage(RS.getDouble(5));
             R.setDate_debut(RS.getDate(6));
             R.setDate_fin(RS.getDate(7));
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public void supprimerOffre(int id) {
        try {
            String req = "DELETE FROM `offre` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifierOffre(Offre R) {
        try {
            
            String req = "UPDATE `Offre` SET `abonnement_nom_id` = '"+R.getAbonnement_nom_id()+"', `points` = '" + R.getPoints()+ "', `prix` = '" + R.getPrix()+ "', `pourcentage` = '" + R.getPourcentage()+ "', `date_debut` = '" + R.getDate_debut()+ "', `date_fin` = '" + R.getDate_fin()+ "' WHERE `offre`.`id` = " + R.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
