/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegym;

import elitegymcenter.services.ServicePlanningCRUD;
import elitegymcenter.services.ServiceCoursCRUD;
import elitegymcenter.entities.Planning;
import elitegymcenter.entities.Cours;
import elitegymcenter.utils.MyDB;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ousam
 */
public class elitegym {

    /**
     * @param args the command line arguments
     */
    
    /*
    
    public static void main(String[] args) {
        // TODO code application logic here
        
         MyDB conn = MyDB.getInstance();
        Cours p1 = new Cours(5, 555 , 5000 , true , "testJava", "c'est un test pour voir l'enregistrement" , "image de test ecriture" );
        Menu testjava = new Menu(3, "test java" , " c'est un test " , " photo de test ");

        // Evenement1CRUD event = new Evenement1CRUD();
        ServicePlanningCRUD res = new ServicePlanningCRUD();
        
        //event.ajouterEvenement(E1);
        //res.ajouterReservation(R1);
        //per.ajouterPersonne2(p1);
        //System.out.println( event.afficherEvenement());
        //System.out.println( res.afficherReservation());
        //event.supprimerEvenement(19);
       // res.supprimerReservation(26);
        //event.modifierEvenement(new Menu(17,Date.valueOf("2023-1-1"), "conférence", "Tunis", "aaabbbbbaaaa", " ", "sportify" ));
       ///////////////// res.modifierPlat(new Cours( 10 , 5 , 555 , 5000 , true , "testJava", "c'est un test pour voir l'enregistrement" , "image de test ecriture"));
          
       
        
    }

*/
    
    public static void main(String[] args) {

    // Création d'un objet de la classe Menu
   // Planning P1 = new Planning( "oussamaoussamaoussamaoussama" , "oussamaoussama");
   // Cours C1 = new Cours(10, "oussamaoussamaoussamaoussama" , "2023-02-11 21:02:00", "CoursCoursCours");
 

    // Création d'un objet de la classe ServicePlanningCRUD
   // ServicePlanningCRUD res = new ServicePlanningCRUD(); 
   ServiceCoursCRUD res = new ServiceCoursCRUD(); 

        
    // Appel de la méthode ajouterMenu pour ajouter un menu à la base de données
    // res.ajouterPlanning(P1);
//    res.ajouterCours(C1);
    // Appel de la méthode afficherMenu pour afficher tous les menus dans la base de données
    
    //////////////////////  System.err.println(res.afficherMenu()); /////////////////
      
        System.err.println(res.afficherCours()); /////////////////
    //  System.err.println(res.afficherPlanning()); /////////////////

   

    // Appel de la méthode supprimerMenu pour supprimer un menu de la base de données
  // int idCoursASupprimer = 21 ; // ID du menu à supprimer
  // res.supprimerCours(idCoursASupprimer);
    //  int idPlanningASupprimer = 11 ; // ID du menu à supprimer
     // res.supprimerPlanning(idPlanningASupprimer);
  
  
  
    // Appel de la méthode modifierMenu pour modifier un menu dans la base de données

  // Cours C1 = new Cours(22,5, "tessstt" , "2050-02-11 21:02:00", "tesstt");
  // res.modifierCours(C1);

   // Cours C1 = new Cours (10 , 10, "test" , "tesssssssssstttt" , "teeesssssttttt");
     //res.modifierCours(C1);
      
     // Planning P1 = new Planning (11, "tesssssssssstttt" , "teeesssssttttt");
     // res.modifierPlanning(P1);
}
    
}
