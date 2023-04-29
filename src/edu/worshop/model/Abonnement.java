/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;

/**
 *
 * @author shini
 */
public class Abonnement {
    private int id, numero;
    private String nom,prenom,email,type;

    public Abonnement(int numero, String nom, String prenom, String email, String type) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.type = type;
    }

    public Abonnement() {
    }

    public Abonnement(int id, int numero, String nom, String prenom, String email, String type) {
        this.id = id;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        //return "Abonnement{" + "id=" + id + ", numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", type=" + type + '}';
            return  " Nom : " + nom ;

    }
    
    
}
