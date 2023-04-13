/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;
import java.sql.Date;


/**
 *
 * @author shini
 */
public class Offre {
    private int id, abonnement_nom_id, points;
    private double prix, pourcentage;
    private Date date_debut, date_fin;
    
    public Offre(int abonnement_nom_id, int points, double prix, double pourcentage, Date date_debut, Date date_fin) {
        this.abonnement_nom_id = abonnement_nom_id;
        this.points = points;
        this.prix = prix;
        this.pourcentage = pourcentage;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Offre(int id, int abonnement_nom_id, int points, double prix, double pourcentage, Date date_debut, Date date_fin) {
        this.id = id;
        this.abonnement_nom_id = abonnement_nom_id;
        this.points = points;
        this.prix = prix;
        this.pourcentage = pourcentage;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Offre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbonnement_nom_id() {
        return abonnement_nom_id;
    }

    public void setAbonnement_nom_id(int abonnement_nom_id) {
        this.abonnement_nom_id = abonnement_nom_id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", abonnement_nom_id=" + abonnement_nom_id + ", points=" + points + ", prix=" + prix + ", pourcentage=" + pourcentage + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
    
}
