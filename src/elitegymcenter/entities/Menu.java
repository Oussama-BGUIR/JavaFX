/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;

import java.sql.Date;

/**
 *
 * @author ousam
 */
public class Menu {
    private int id , calorie ;
    private boolean disponibilite ;
    private String nom , description , image ;
    
    
    public Menu(int calorie, boolean disponibilite, String nom, String description, String image) {
        this.nom = nom;  
        this.description = description;
        this.calorie = calorie;
        this.disponibilite = disponibilite;
        this.image = image;
    
    }

    public Menu() {
       
    }

    public Menu(int id,int calorie, boolean disponibilite, String nom , String description, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description; 
        this.calorie = calorie;
        this.disponibilite = disponibilite;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    

    @Override
    public String toString() {
        //return "Menu{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", calorie=" + calorie + ", desponibilite=" + disponibilite + ", image=" + image + '}';
    return " Nom : " + nom ;
    }
    

}


