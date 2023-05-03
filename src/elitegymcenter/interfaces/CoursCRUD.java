/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.interfaces;

import elitegymcenter.entities.Cours;
import javafx.collections.ObservableList;

import java.util.List;

/**
 *
 * @author ousam
 */
public interface CoursCRUD {
    public void ajouterCours(Cours C);
    public ObservableList<Cours> afficherCours();
    public void supprimerCours(int id);
    public void modifierCours(Cours C);
}
