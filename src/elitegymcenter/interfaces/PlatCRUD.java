/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Plat;
import java.util.List;

/**
 *
 * @author ousam
 */
public interface PlatCRUD {
    public void ajouterPlat(Plat P);
    public List<Plat> afficherPlat();
    public void supprimerPlat(int id);
    public void modifierPlat(Plat P);
    public void supprimerPanier (int id);
}
