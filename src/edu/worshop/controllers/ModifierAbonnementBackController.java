/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Abonnement1CRUD;
import edu.worshop.interfaces.AbonnementCRUD;
import edu.worshop.model.Abonnement;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author shini
 */
public class ModifierAbonnementBackController implements Initializable {

    @FXML
    private TextField nomAbonnementModifierfx;
    @FXML
    private TextField prenomAbonnementModifierfx;
    @FXML
    private TextField numeroAbonnementModifierfx;
    @FXML
    private TextField emailAbonnementModifierfx;
    @FXML
    private ChoiceBox<String> typeAbonnementModifierfx;
     @FXML
    private final String[] typeAbonnementModifierfxVariable = {"Manuelle", "trimestrielle", "Anuelle"};
     
    static int id, numero;
    static String nom, prenom,email,type;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeAbonnementModifierfx.getItems().addAll(typeAbonnementModifierfxVariable);
        nomAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getNom()));
        prenomAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getPrenom()));
        numeroAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getNumero()));
        emailAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getEmail()));
        
       
    }   
    
  
  

    @FXML
    private void modiferAbonnementBack(ActionEvent event) 
    {
         
        AbonnementCRUD inter = new Abonnement1CRUD();
        
        String nom = nomAbonnementModifierfx.getText();
        String prenom = prenomAbonnementModifierfx.getText();
        int numero = Integer.valueOf(numeroAbonnementModifierfx.getText());
        String email = emailAbonnementModifierfx.getText();
        String type = typeAbonnementModifierfx.getValue();
        
        
        System.out.println(AfficherAbonnementBackController.E.getId());
        Abonnement even = new Abonnement(AfficherAbonnementBackController.E.getId(), numero, nom, prenom, email, type );
      
        inter.modifierAbonnement(even);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherAbonnementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

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
    

