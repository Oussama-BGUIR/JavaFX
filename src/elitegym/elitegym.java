/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegym;

import elitegymcenter.services.ServiceMenuCRUD;
import elitegymcenter.services.ServicePlatCRUD;
import elitegymcenter.entities.Menu;
import elitegymcenter.entities.Plat;
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
        Plat p1 = new Plat(5, 555 , 5000 , true , "testJava", "c'est un test pour voir l'enregistrement" , "image de test ecriture" );
        Menu testjava = new Menu(3, "test java" , " c'est un test " , " photo de test ");

        // Evenement1CRUD event = new Evenement1CRUD();
        ServiceMenuCRUD res = new ServiceMenuCRUD();
        
        //event.ajouterEvenement(E1);
        //res.ajouterReservation(R1);
        //per.ajouterPersonne2(p1);
        //System.out.println( event.afficherEvenement());
        //System.out.println( res.afficherReservation());
        //event.supprimerEvenement(19);
       // res.supprimerReservation(26);
        //event.modifierEvenement(new Menu(17,Date.valueOf("2023-1-1"), "conférence", "Tunis", "aaabbbbbaaaa", " ", "sportify" ));
       ///////////////// res.modifierPlat(new Plat( 10 , 5 , 555 , 5000 , true , "testJava", "c'est un test pour voir l'enregistrement" , "image de test ecriture"));
          
       
        
    }

*/
    
    public static void main(String[] args) {

    // Création d'un objet de la classe Menu
    //////Plat p1 = new Plat(4, 1000 , 5000  ,false , "oussama" , "oussamaoussamaoussamaoussama" , "oussamaoussama");
  //////////////////////////// Menu oussssssss = new Menu(10323 , false , "bguir" , "bguirbguir" , "bgggg");
   //////// Plat p2 = new Plat(4, 123 , 123  ,true , "hhhhhhh" , "hhhhhhhhhhhhhhhhhh" , "hhhhhhhhhhhhh");


    // Création d'un objet de la classe ServiceMenuCRUD
  ////////////////////  ServiceMenuCRUD res = new ServiceMenuCRUD(); 
    ServicePlatCRUD res = new ServicePlatCRUD(); 

        
    // Appel de la méthode ajouterMenu pour ajouter un menu à la base de données
   ////////////////////////// res.ajouterMenu(oussssssss);
   ////////res.ajouterPlat(p2);

    // Appel de la méthode afficherMenu pour afficher tous les menus dans la base de données
    
    //////////////////////  System.err.println(res.afficherMenu()); /////////////////
      
          //////////////////////  System.err.println(res.afficherMenu()); /////////////////
      /////////// System.err.println(res.afficherPlat()); /////////////////

   

    // Appel de la méthode supprimerMenu pour supprimer un menu de la base de données
   ////////////////// int idMenuASupprimer = 6 ; // ID du menu à supprimer
  //////////////////   res.supprimerMenu(idMenuASupprimer);
    //////////// int idPlatASupprimer = 4 ; // ID du menu à supprimer
    ///////////  res.supprimerPlat(idPlatASupprimer);
  
  
  
    // Appel de la méthode modifierMenu pour modifier un menu dans la base de données
    /////////////// Menu menuAModifier = new Menu(7, 500000 , false , "lina", "liiiiiiiinaaaaaaaaaa", "liiiiiiiiiinaaaaaaaaaaaaa");
   ///////////////  res.modifierMenu(menuAModifier);
      Plat platAModifier = new Plat (6, 12 , 4444, 4444  ,true , "test" , "tesssssssssstttt" , "teeesssssttttt");
      res.modifierPlat(platAModifier);
}
    
}
