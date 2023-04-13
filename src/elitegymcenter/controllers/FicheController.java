/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Fiche;
import elitegymcenter.entities.Fiche;
import elitegymcenter.interfaces.FicheCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import elitegymcenter.interfaces.FicheCRUD;
import elitegymcenter.services.ServiceFicheCRUD;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FicheController implements Initializable {

    @FXML
    private ListView<Fiche> AffichageListeFichesBackfx;
    static Fiche F = new Fiche();
    static int id,numtel;
    static String nom,prenom,email,description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Fiche> list1 = AffichageListeFichesBackfx;
        FicheCRUD inter = new ServiceFicheCRUD();
        List<Fiche> list2 = inter.afficherFiche();
        for (int i = 0; i < list2.size(); i++) 
        {
            Fiche F = list2.get(i);
            list1.getItems().add(F); // add Fiche to ListView
        } 
        
    }     
        

    @FXML
    private void modifierFicheBack(ActionEvent event) {
      
        ListView<Fiche> list = AffichageListeFichesBackfx;
        FicheCRUD inter = new ServiceFicheCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Fiche f = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = f.getNom();
        String prenom = f.getPrenom();
        String email = f.getEmail();
        String description = f.getDescription();
        int numtel = f.getNumtel();
      //  int Nom_nutritioniste_id = r.getNom_nutritioniste_id();
        
        F=f;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/ModifierFiche.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(FicheController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterFicheBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/AjouterFiche.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterFicheController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerFicheBack(ActionEvent event) {
        
    
        ListView<Fiche> list = (ListView<Fiche>) AffichageListeFichesBackfx;
        FicheCRUD inter = new ServiceFicheCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Fiche F = list.getSelectionModel().getSelectedItem();
            inter.supprimerFiche(F.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("sélectionner une fiche pour la supprimer ");
        }
         
    }

    private void showAlert(String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
    

