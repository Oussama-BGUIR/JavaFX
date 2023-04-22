/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.entities;



/**
 *
 * @author ousam
 */
public class Cours {
    private int id , planning_id ;
    private String nom , duree , salle ;
    

        public Cours(int planning_id, String nom, String duree , String salle) {
        this.planning_id = planning_id;
        this.nom = nom;
        this.duree = duree;
        this.salle = salle;
    }
    
    public Cours(int id, int planning_id, String nom, String duree , String salle) {
        this.id = id;
        this.planning_id = planning_id;
        this.nom = nom;
        this.duree = duree;
        this.salle = salle;
    }
    
    

    
    public Cours() {
       
    }
    
    

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

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
    
  


    @Override
    public String toString() {
        return "Cours{" + "id=" + id +  ", planning_id=" + planning_id +  ", nom=" + nom + ", duree=" + duree + ", salle=" + salle + '}';
    }
   
       
     
}
