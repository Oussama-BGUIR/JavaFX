/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Rdv;
import java.util.List;

/**
 *
 * @author user
 */
public interface RdvCRUD {
    public void ajouterRdv(Rdv R);
    public List<Rdv> afficherRdv();
    public void supprimerRdv(int id);
    public void modifierRdv(Rdv R);
}