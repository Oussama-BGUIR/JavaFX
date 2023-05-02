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
public class Plat {
    private Menu menu;
    private int id , menu_id , calorie , prix ;
    private boolean disponibilte ;
    private String nom , description , image ;
    
    private int quantite_commande ;
    private Date date;

    public Plat(Menu menu, int id, int calorie, int prix, boolean disponibilte, String nom, String description, String image) {
        this.menu = menu;
        this.id = id;
        this.calorie = calorie;
        this.prix = prix;
        this.disponibilte = disponibilte;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
    
    public Plat(int id, String nom,int prix, int quantite_commande, Date date) {
        this.id = id;
        this.nom = nom;
        this.prix=prix;
        this.quantite_commande = quantite_commande;
        this.date = date;
    }

    public Plat(Menu menu, int calorie, int prix, boolean disponibilte, String nom, String description, String image) {
        this.menu = menu;
        this.calorie = calorie;
        this.prix = prix;
        this.disponibilte = disponibilte;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
    
 
    
        public Plat(int menu_id, int calorie, int prix, boolean disponibilte, String nom, String description, String image) {
        this.menu_id = menu_id;
        this.calorie = calorie;
        this.prix = prix;
        this.disponibilte = disponibilte;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
    
    public Plat(int id, int menu_id, int calorie, int prix, boolean disponibilite, String nom, String description, String image) {
        this.id = id;
        this.menu_id = menu_id;
        this.calorie = calorie;
        this.prix = prix;
        this.disponibilte = disponibilte;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    

    
    public Plat() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean getDisponibilte() {
        return disponibilte;
    }

    public void setDisponibilte(boolean disponibilte) {
        this.disponibilte = disponibilte;
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
        return "Plat{" + "id=" + id +  ", menu_id=" + menu_id +  ", nom=" + nom + ", description=" + description + ", calorie=" + calorie + ", prix=" + prix + ", desponibilite=" + disponibilte + ", image=" + image + '}';
    }
   
       
     
}
