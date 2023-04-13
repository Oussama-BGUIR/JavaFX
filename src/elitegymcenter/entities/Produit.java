/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;



/**
 *
 * @author LENOVO
 */
public class Produit {
    private int id , categorie_id , prix ;
  
    private String nom , description , image ;
    

        public Produit(int categorie_id, int prix, String nom, String description, String image) {
        this.categorie_id = categorie_id;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
    
    public Produit(int id, int categorie_id, int prix, String nom, String description, String image) {
        this.id = id;
        this.categorie_id = categorie_id;
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

    public void setCategorie_id(int menu_id) {
        this.categorie_id = menu_id;
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
        return "Produit{" + "id=" + id +  ", categorie_id=" + categorie_id +  ", nom=" + nom + ", description=" + description + ", prix=" + prix + ",  image=" + image + '}';
    }
    /*
   @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", evenement_id=" + evenement_id + ", paiement=" + paiement + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone +  '}';
    }
    */
    

        
     
}
