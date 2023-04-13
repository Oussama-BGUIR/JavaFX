/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Abonnement1CRUD;
import static edu.worshop.controllers.AfficherAbonnementBackController.email;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import edu.worshop.model.Abonnement;

import java.io.IOException;
import static java.lang.String.valueOf;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shini
 */
public class AjoutAbonnementFrontController implements Initializable {

    @FXML
    private TextField nomAbonnementfx;
    @FXML
    private TextField prenomAbonnementfx;
    @FXML
    private TextField numeroAbonnementfx;
    @FXML
    private TextField emailAbonnementfx;
    @FXML
    private ChoiceBox<String> typeAbonnementfx;
    
    private boolean verificationNumero;
  
    
    private final String[] typeAbonnementfxVariable = {"Manuelle", "trimestrielle", "Anuelle"};
    @FXML
    private Label labeltel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeAbonnementfx.getItems().addAll(typeAbonnementfxVariable);
       
    }    

    @FXML
    private void AjoutAbonnementFront(ActionEvent event) 
    {
        
        String nom = nomAbonnementfx.getText();
        String prenom = prenomAbonnementfx.getText();
        int numero = Integer.valueOf(numeroAbonnementfx.getText());
        String email = emailAbonnementfx.getText();
        String type = typeAbonnementfx.getValue();
        
        
        
        if(nom.isEmpty()){
            showAlert("La case nom est vide!");
        }else if(prenom.isEmpty()){
            showAlert("La case prenom est vide!");
        }else if(email.isEmpty()){
            showAlert("La case email est vide!");
        } else if(numeroAbonnementfx.getText().isEmpty()){
            showAlert("La case numero est vide!");
        }else if (verifString(nomAbonnementfx))
        {
             showAlert("le nom doit contenir que des lettres!");
        }
        else if (verifString(prenomAbonnementfx))
        {
             showAlert("le prenom doit contenir que des lettres!");
        }else if (verificationNumero)
        {
             showAlert("Le numero est invalide");
        }else if (verifEmail(emailAbonnementfx))
        {
             showAlert("l'email est invalide!");
        }
        
        
         else {
            
            showAlert("Etes vous sur d'ajouter un abonnement?");
            Abonnement E = new Abonnement(numero,nom,prenom,email,type);
            Abonnement1CRUD abonnement1 = new Abonnement1CRUD();
            abonnement1.ajouterAbonnement(E);
        
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
    }
    private void showAlert(String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    
    
    
    private boolean verifEmail(TextField chaine) {
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(chaine.getText().trim());

        if (((Matcher) matcher).matches()) {       //if   matcher ne contient pas la format
            return false;

           // verificationUserEmail = true;

        } else {
            return true;
            // JOptionPane.showMessageDialog(null, "Email Format invalide");
            //verificationUserEmail = false;

        }
       
        
    }
    
     @FXML
    private void veriftel(KeyEvent event) {
    if (numeroAbonnementfx.getText().trim().length() == 8) {
        int nbChar = 0;
        for (int i = 1; i < numeroAbonnementfx.getText().trim().length(); i++) {
            char ch = numeroAbonnementfx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labeltel.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationNumero = false;
        } else {
            labeltel.setText("invalide number \n"
                    + " Il exist des char");
            verificationNumero = true;

        }

    } else {
        labeltel.setText("Il faut 8 chiffres");
       verificationNumero = true;
    }
}
    
    
}
    
    
    
    
    


