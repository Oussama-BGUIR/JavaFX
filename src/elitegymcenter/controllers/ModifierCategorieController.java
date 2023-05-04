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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierCategorieController implements Initializable {

    @FXML
    private TextField modifiernomCategorie;
    @FXML
    private TextField modifierdescriptionCategorie;
    @FXML
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifiernomCategorie.setText(String.valueOf(CategorieController.C.getNom()));
        modifierdescriptionCategorie.setText(String.valueOf(CategorieController.C.getDescription()));
       
    }    
    

    @FXML
    private void modifiercategorie(ActionEvent event) 
    {
         
        CategorieCRUD inter = new ServiceCategorieCRUD();
        
        String nom = modifiernomCategorie.getText();
        String description = modifierdescriptionCategorie.getText();
    
        
        
        System.out.println(CategorieController.C.getId());
        Categorie men = new Categorie(CategorieController.C.getId(), nom, description);
      
        inter.modifierCategorie(men);
        
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
    
      private void showAlert(String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
