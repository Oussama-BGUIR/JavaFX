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
public class Planning {
    private int id  ;
    private String semaine , description ;
    
    
    public Planning( String semaine, String description) {
        this.semaine = semaine;  
        this.description = description;

    
    }

    public Planning() {
       
    }

    public Planning(int id, String semaine , String description) {
        this.id = id;
        this.semaine = semaine;
        this.description = description; 

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getSemaine() {
        return semaine;
    }

    public void setSemaine(String semaine) {
        this.semaine = semaine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    

    @Override
    public String toString() {
        return "Planning{" + "id=" + id + ", semaine=" + semaine + ", description=" + description + '}';
    }
    
    /*
     @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", date=" + date + ", type=" + type + ", lieu=" + lieu + ", description=" + description + ", titre=" + titre + ", even_pic=" + even_pic + '}';
    }
    */
}


