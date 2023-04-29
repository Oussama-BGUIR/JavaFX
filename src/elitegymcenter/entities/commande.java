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
public class commande {
    private int id , prix ;
    
    private String nom ;
    private int quantite_commande ;
    private Date date;

    public commande(int id, int prix, String nom, int quantite_commande, Date date) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.quantite_commande = quantite_commande;
        this.date = date;
    }

    public commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getQuantite_commande() {
        return quantite_commande;
    }

    public void setQuantite_commande(int quantite_commande) {
        this.quantite_commande = quantite_commande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
