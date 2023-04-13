/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Categorie;
import elitegymcenter.interfaces.CategorieCRUD;
import elitegymcenter.services.ServiceCategorieCRUD;
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

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CategorieController implements Initializable {

    @FXML
    private ListView<Categorie> AffichageListeCategoriesBackfx;
    static Categorie C = new Categorie();
    static int id;
    static String nom,description ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Categorie> list1 = AffichageListeCategoriesBackfx;
        CategorieCRUD inter = new ServiceCategorieCRUD();
        List<Categorie> list2 = inter.afficherCategorie();
        for (int i = 0; i < list2.size(); i++) 
        {
            Categorie C = list2.get(i);
            list1.getItems().add(C); // add Evenement to ListView
        } 
        
    }     
        

    @FXML
    private void modifierCategorieBack(ActionEvent event) {
      
        ListView<Categorie> list = AffichageListeCategoriesBackfx;
        CategorieCRUD inter = new ServiceCategorieCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Categorie c = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = c.getNom();
        String description = c.getDescription();
        
        C=c;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/ModifierCategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterCategorieBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/AjouterCategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterCategorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerCategorieBack(ActionEvent event) {
        
    
        ListView<Categorie> list = (ListView<Categorie>) AffichageListeCategoriesBackfx;
        CategorieCRUD inter = new ServiceCategorieCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Categorie C = list.getSelectionModel().getSelectedItem();
            inter.supprimerCategorie(C.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("sélectionner une categorie pour le supprimer ");
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
    
