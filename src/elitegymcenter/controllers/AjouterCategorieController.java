/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Categorie;
import elitegymcenter.services.ServiceCategorieCRUD;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private TextField nomCategoriefx;
    @FXML
    private TextField descriptionCategoriefx;
    @FXML
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjoutCategorie(ActionEvent event) {
        
        String nom = nomCategoriefx.getText();
        String description = descriptionCategoriefx.getText();
        if(nom.isEmpty()){
            showAlert("La case nom est vide!");
        }else if(description.isEmpty()){
            showAlert("La case description est vide!");
        }else
       if (verifString(nomCategoriefx))
        {
             showAlert("le nom doit contenir que des lettres!");
        }
       else{
            
            showAlert("Etes vous sur d'ajouter une categorie?");

        Categorie C = new Categorie(nom, description);
        ServiceCategorieCRUD ServiceCategorie = new ServiceCategorieCRUD();
        ServiceCategorie.ajouterCategorie(C);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Categorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
                   
    }
    }
    private boolean verifString(TextField chaine) {
 
        for (int i = 1; i < chaine.getText().trim().length(); i++) {
            char ch = chaine.getText().charAt(i);
            if (Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
        
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
    
