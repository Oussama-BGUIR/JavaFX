/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;

/**
 *
 * @author user
 */
public class Fiche {
    private int id, numtel;
    private String nom,prenom,email,description;
    

    public Fiche(int numtel, String nom, String prenom, String email,String description) {
        this.numtel = numtel;
        this.description = description;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;        
    }

   public Fiche(int id, int numtel, String nom, String prenom, String email,String description){
       this.id= id;
        this.numtel = numtel;
        this.description = description;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;        
    }

    public Fiche() {
    }
       
    

  public int getId() {
     return id; 
}

    public void setId(int id) {
        this.id = id;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getEmail() {
        return email;
    
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
    //    return "Fiche{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone=" + numtel + ", description=" + description + '}';
   
      return " Nom :   " + nom + "   Prenom :   " + prenom ;
    }

        
     
}

