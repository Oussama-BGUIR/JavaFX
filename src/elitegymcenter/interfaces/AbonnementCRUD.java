/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Abonnement;
import java.util.List;
/**
 *
 * @author shini
 */
public interface AbonnementCRUD {
    public void ajouterAbonnement(Abonnement E);
    public List<Abonnement> afficherAbonnement();
    public void supprimerAbonnement(int id);
    public void modifierAbonnement(Abonnement E);
    
}
