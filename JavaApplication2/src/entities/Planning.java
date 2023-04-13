/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MSI
 */
public class Planning {
    private int id;
    private String semaine;
    private String description;
    
    public Planning(int id, String semaine, String description) {
        this.id = id;
        this.semaine = semaine;
        this.description = description;
    }
    
        public Planning() {
    }
    
    // getters and setters
    
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
}

