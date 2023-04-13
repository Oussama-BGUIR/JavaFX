/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Rdv {
     private int id, numtel;
     private Date date;
    private String nom,prenom,email,description;

    public Rdv( int numtel,Date date,String nom, String prenom, String email,String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.email = email;
        this.description = description;
     //   this.Nom_nutritioniste_id = Nom_nutritioniste_id;
        this.numtel = numtel;
    }

    public Rdv() {
       
    }

      public Rdv(int id,  int numtel, Date date,String nom, String prenom, String email, String description) {
        this.id = id;
   //     this.Nom_nutritioniste_id = Nom_nutritioniste_id;
         this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.email = email;
        this.numtel = numtel;
        this.description = description;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
 //      public int getNom_nutritioniste_id() {
   //     return Nom_nutritioniste_id;
    //}

    //public void setNom_nutritioniste_id(int Nom_nutritioniste_id) {
      //  this.Nom_nutritioniste_id = Nom_nutritioniste_id;
    //}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom= nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom= prenom;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Rendez-vous{" + "id=" + id + ",nom=" + nom + ",prenom=" + prenom + ", date=" + date + ", email=" + email + ",numtel=" + numtel + ", description=" + description + '}';
    }
    
    
    
    
}
