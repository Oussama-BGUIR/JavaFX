/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Offre1CRUD;
import edu.worshop.model.Offre;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shini
 */
public class AjoutOffreController implements Initializable {

    @FXML
    private TextField pointsOffrefx;
    @FXML
    private TextField prixOffrefx;
    @FXML
    private TextField pourcentageOffrefx;
    @FXML
    private DatePicker dateDebutfx;
    @FXML
    private DatePicker dateFinfx;
    
    private boolean verificationPoints;
    private boolean verificationPrix;
    private boolean verificationPourcentage;
    
    @FXML
    private Label labelPrix;
    @FXML
    private Label labelPourcentage;
    @FXML
    private Label labelPoints;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutOffre(ActionEvent event) 
    {
        int points = Integer.valueOf(pointsOffrefx.getText());
        double prix = Double.valueOf(prixOffrefx.getText());
        double pourcentage = Double.valueOf(pourcentageOffrefx.getText());
        Date date_debut = java.sql.Date.valueOf(dateDebutfx.getValue());
        Date date_fin = java.sql.Date.valueOf(dateFinfx.getValue());
        
         LocalDate currentDate = LocalDate.now(); // Gets the current date
        String dateStringlocal = currentDate.toString();
        String date_debut_string = date_debut.toString();
        String date_fin_string = date_fin.toString();
        int comparison = date_debut.compareTo(date_fin);
        int comparaison2 = date_debut_string.compareTo(dateStringlocal);
        int comparaison3 = date_fin_string.compareTo(dateStringlocal);
      
      if(pointsOffrefx.getText().isEmpty()){
            showAlert("La case des points est vide!");
      }else if(prixOffrefx.getText().isEmpty()){
            showAlert("La case des prix est vide!");
      }else if(pourcentageOffrefx.getText().isEmpty()){
            showAlert("La case de la pourcentage est vide!");
      }if(verificationPoints){
            showAlert("les points sont invalide!");
      }else if(verificationPrix){
            showAlert("le prix est invalide!");
      }else if(verificationPourcentage){
            showAlert("le pourncentage est invalide!");
      }else if (points < 0) 
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            showAlert("erreur les points sont negatifs");
           
        } else if (prix < 0) { 
            showAlert("erreur le prix est negatif");
 
        } else if (pourcentage < 0) {
             showAlert("erreur le pourcentage est negatif");
  
        }else if ((comparaison2 < 0) || (comparaison3 < 0))
        {
            showAlert("La date est inférieur à la date d'aujourd'hui");
        } else if  (comparison > 0) {
            showAlert("la date debut est supérieur à la date finale");
        } else{
        
           
            showAlert("Etes vous sur d'ajouter un offre?");
            Offre E = new Offre(AfficherAbonnementBackController.E.getId(),points,prix,pourcentage,date_debut,date_fin);
            Offre1CRUD offre1 = new Offre1CRUD();
            offre1.ajouterOffre(E);

            try {

                Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherOffre.fxml"));
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
        
    @FXML
    private void verifPoint(KeyEvent event) {
    
        int nbChar = 0;
        for (int i = 1; i < pointsOffrefx.getText().trim().length(); i++) {
            char ch = pointsOffrefx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labelPoints.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationPoints = false;
        } else {
            labelPoints.setText("invalide number \n"
                    + " Il exist des char");
            verificationPoints = true;

        }

    
    }
    
    @FXML
    private void verifPrix(KeyEvent event) 
    {
    
        int nbChar = 0;
        for (int i = 1; i < prixOffrefx.getText().trim().length(); i++) {
            char ch = prixOffrefx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labelPrix.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationPrix = false;
        } else {
            labelPrix.setText("invalide number \n"
                    + " Il exist des char");
            verificationPrix = true;

        }

    
}
    @FXML
    private void verifPourcentage(KeyEvent event) {
    
        int nbChar = 0;
        for (int i = 1; i < pourcentageOffrefx.getText().trim().length(); i++) {
            char ch = pourcentageOffrefx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labelPourcentage.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationPoints = false;
        } else {
            labelPourcentage.setText("invalide number \n"
                    + " Il exist des char");
            verificationPoints = true;

        }

    
}
}

 
