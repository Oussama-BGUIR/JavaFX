/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Planning;
import elitegymcenter.entities.Cours;
import java.util.List;

/**
 *
 * @author ousam
 */
public interface PlanningCRUD {
    public void ajouterPlanning(Planning P);
    public List<Planning> afficherPlanning();
     public void supprimerPlanning(int id);
     public void modifierPlanning(Planning P);

    
}
