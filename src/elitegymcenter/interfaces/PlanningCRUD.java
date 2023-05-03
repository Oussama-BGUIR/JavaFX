/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Planning;
import javafx.collections.ObservableList;
import elitegymcenter.entities.Cours;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ousam
 */
public interface PlanningCRUD {
    public void ajouterPlanning(Planning P) throws SQLException;
    public ObservableList<Planning>  afficherPlanning();
     public void supprimerPlanning(int id);
     public void modifierPlanning(Planning P);

    
}
