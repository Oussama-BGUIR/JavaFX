/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Categorie;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface CategorieCRUD {
    public void ajouterCategorie(Categorie C);
    public List<Categorie> afficherCategorie();
     public void supprimerCategorie(int id);
     public void modifierCategorie(Categorie C);
    
}
