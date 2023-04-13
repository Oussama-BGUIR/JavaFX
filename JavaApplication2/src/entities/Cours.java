/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Cours {
    private int id;
    private int planning_id;
    private String nom;
    private int duree;
    private String salle;
    
    public Cours(int id, int planning_id, String nom, int duree, String salle) {
        this.id = id;
        this.planning_id = planning_id;
        this.nom = nom;
        this.duree = duree;
        this.salle = salle;
    }
    
        public Cours() {
    }
    // Getters and setters for each field
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPlanning_id() {
        return planning_id;
    }
    
    public void setPlanning_id(int planning_id) {
        this.planning_id = planning_id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getDuree() {
        return duree;
    }
    
    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    public String getSalle() {
        return salle;
    }
    
    public void setSalle(String salle) {
        this.salle = salle;
    }
}

