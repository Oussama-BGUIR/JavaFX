/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a4;

import edu.workshop.services.Abonnement1CRUD;
import edu.workshop.services.Offre1CRUD;
import edu.worshop.model.Abonnement;
import java.sql.Date;
import edu.worshop.model.Offre;


/**
 *
 * @author belkn
 */
public class Workshop3A4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       /// MyConnection conn = MyConnection.getInstance();
        //Abonnement E1 = new Abonnement( 999,"lina", "fakh", "faze@efzf.com", "annuel" );
       
        //Offre R1 = new Offre(1, 45, 55, 7, Date.valueOf("2023-1-1"), Date.valueOf("2024-1-1") );
      
       // Abonnement1CRUD event = new Abonnement1CRUD();
        Offre1CRUD res = new Offre1CRUD();
        
        // event.ajouterAbonnement(E1);
        //res.ajouterOffre(R1);
        
        //System.out.println( event.afficherAbonnement());
       // System.out.println( res.afficherOffre());
        //event.supprimerAbonnement(1);
       // res.supprimerOffre(7);
       // event.modifierAbonnement(new Abonnement(1,11111,"aa", "aa", "aaabbbbbaaaa", "aaggg" ));
      res.modifierOffre(new Offre(5,2,45555, 55555, 7, Date.valueOf("2023-1-1"), Date.valueOf("2024-1-1")));
          
       
        
    }
    
}
