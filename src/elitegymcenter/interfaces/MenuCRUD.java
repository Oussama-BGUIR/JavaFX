/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Menu;
import elitegymcenter.entities.Plat;
import java.util.List;

/**
 *
 * @author ousam
 */
public interface MenuCRUD {
    public void ajouterMenu(Menu M);
    public List<Menu> afficherMenu();
     public void supprimerMenu(int id);
     public void modifierMenu(Menu M);

    
}
