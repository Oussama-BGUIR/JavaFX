/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;

import java.sql.Date;



/**
 *
 * @author LENOVO
 */
public class Produit {
    private int id , prix, categorie_id ;
    private Categorie category;
    
    private String nom , description , image ;
    private int quantite_commande ;
    private Date date;

    public Produit(int id, String nom,int prix, int quantite_commande, Date date) {
        this.id = id;
        this.nom = nom;
        this.prix=prix;
        this.quantite_commande = quantite_commande;
        this.date = date;
    }
    

      public Produit(Categorie category, int prix, String nom, String description, String image) {
        this.category = category;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }

    public Categorie getCategory() {
        return category;
    }

    public void setCategory(Categorie category) {
        this.category = category;
    }
      
      
        public Produit(int categorie_id, int prix, String nom, String description, String image) {
        this.categorie_id = categorie_id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
    
    
    public Produit(int id,  int prix,Categorie category , String nom, String description, String image) {
        this.id = id;
        this.category = category;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
    
    

    
    public Produit() {
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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
        return "Produit{" + "id=" + id + ", prix=" + prix + ", category=" + category + ", nom=" + nom + ", description=" + description + ", image=" + image + '}';
    }
    

        
     
}
