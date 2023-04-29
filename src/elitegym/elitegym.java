/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temRdve file, choose Tools | TemRdves
 * and open the temRdve in the editor.
 */
package elitegym;

import elitegymcenter.services.ServiceFicheCRUD;
import elitegymcenter.services.ServiceRdvCRUD;
import elitegymcenter.entities.Fiche;
import elitegymcenter.entities.Rdv;
import elitegymcenter.utils.MyDB;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class elitegym {

    /**
     * @param args the command line arguments
     */
    
    /*
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //// MyDB conn = MyDB.getInstance();
        //Rdv p1 = new Rdv(5, 555 , 5000 , true , "testJava", "c'est un test pour voir l'enregistrement" , "image de test ecriture" );
        Fiche testjava = new Fiche(3, "test java" , " c'est un test " , " photo de test ");

        // Evenement1CRUD event = new Evenement1CRUD();
        ServiceFicheCRUD res = new ServiceFicheCRUD();
        
        //event.ajouterEvenement(E1);
        //res.ajouterReservation(R1);
        //per.ajouterPersonne2(p1);
        //System.out.println( event.afficherEvenement());
        //System.out.println( res.afficherReservation());
        //event.supprimerEvenement(19);
       // res.supprimerReservation(26);
        //event.modifierEvenement(new Fiche(17,Date.valueOf("2023-1-1"), "conférence", "Tunis", "aaabbbbbaaaa", " ", "sportify" ));
       ///////////////// res.modifierRdv(new Rdv( 10 , 5 , 555 , 5000 , true , "testJava", "c'est un test pour voir l'enregistrement" , "image de test ecriture"));
          
       
        
    }

*/
    
    public static void main(String[] args) {

    // Création d'un objet de la classe Fiche
   // Rdv rdve = new Rdv(123456789,Date.valueOf("2023-1-1"), "ghassen" , "rejeb" , "rejeb@geg.com","hhfhserh");
    
 ///// Fiche oussssssss = new Fiche( 4444 , "hhhhh" , "bguir" , "bguirbguir" , "bgggg");


    // Création d'un objet de la classe ServiceFicheCRUD
 //   ServiceFicheCRUD res = new ServiceFicheCRUD(); 
    ServiceRdvCRUD rdv = new ServiceRdvCRUD(); 
      
        
    // Appel de la méthode ajouterFiche pour ajouter une fiche à la base de données
///   res.ajouterFiche(oussssssss);
//   rdv.ajouterRdv(rdve);

    // Appel de la méthode afficherFiche pour afficher tous les Fiches dans la base de données
    
  /// System.err.println(res.afficherFiche()); /////////////////
  //System.err.println(rdv.afficherRdv());
      
      
    /*List<Fiche> listeFiches = res.afficherFiche();
    for (Fiche Fiche : listeFiches) {
        System.out.println(Fiche);
    }
*/

    // Appel de la méthode supprimerFiche pour supprimer un Fiche de la base de données
  ///int idFicheASupprimer = 33 ; // ID du Fiche à supprimer
 //  res.supprimerFiche(idFicheASupprimer);
 //    int idRdvASupprimer = 52 ;
   //    rdv.supprimerRdv(idRdvASupprimer);

    // Appel de la méthode modifierFiche pour modifier un Fiche dans la base de données
 //Fiche FicheAModifier = new Fiche(34,1234, "oussema" , "bguir" , "rhs", "shshdhds");
  //  res.modifierFiche(FicheAModifier);
  
  
  //Rdv RdvAModifier = new Rdv(51, 45612, Date.valueOf("2023-1-1") , "samar" , "jalleli" , "samaarr@fe", "uugihlb");
   //rdv.modifierRdv(RdvAModifier);
}
    
}
