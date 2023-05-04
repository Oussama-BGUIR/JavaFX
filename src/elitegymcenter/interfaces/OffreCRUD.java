/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;
import elitegymcenter.entities.Offre;
import java.util.List;


/**
 *
 * @author shini
 */
public interface OffreCRUD {
    public void ajouterOffre(Offre R);
    public List<Offre> afficherOffre();
    public void supprimerOffre(int id);
    public void modifierOffre(Offre R);
    
}
