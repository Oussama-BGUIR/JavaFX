/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Fiche;
import java.util.List;

/**
 *
 * @author user
 */
public interface FicheCRUD {
    public void ajouterFiche(Fiche F);
    public List<Fiche> afficherFiche();
     public void supprimerFiche(int id);
     public void modifierFiche(Fiche F);
    
}
