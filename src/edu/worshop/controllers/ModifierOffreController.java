/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;


import edu.workshop.services.Abonnement1CRUD;
import edu.workshop.services.Offre1CRUD;
import edu.worshop.interfaces.OffreCRUD;
import edu.worshop.model.Abonnement;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author shini
 */
public class ModifierOffreController implements Initializable {


    
   
    @FXML
    private TextField modifierPointsOffrefx;
    @FXML
    private TextField modifierPrixOffrefx;
    @FXML
    private TextField modifierPourcentageOffrefx;
    @FXML
    private DatePicker modifierDateDebutfx;
    @FXML
    private DatePicker modifierDateFinfx;
  
    static int id, points;
    static double prix, pourcentage;
    static Date date_debut,date_fin;
    
    @FXML
    private ComboBox<Abonnement> AbonnementModifierfx;
    
    private boolean verificationPointsModifier;
    private boolean verificationPrixModifier;
    private boolean verificationPourcentageModifier;
    
    @FXML
    private Label labelPrixModifier;
    @FXML
    private Label labelPourcentageModifier;
    @FXML
    private Label labelPointsModifier;
    Abonnement1CRUD acs=new Abonnement1CRUD();
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         AbonnementModifierfx.getItems().addAll(acs.afficherAbonnement());
        modifierPointsOffrefx.setText(String.valueOf(AfficherOffreController.E.getPoints()));
        modifierPrixOffrefx.setText(String.valueOf(AfficherOffreController.E.getPrix()));
        modifierPourcentageOffrefx.setText(String.valueOf(AfficherOffreController.E.getPourcentage()));
     
    }    
    @FXML
    private void ModifierOffreButton(ActionEvent event) 
    {
        OffreCRUD inter = new Offre1CRUD();
        
       
         Date date_debut = java.sql.Date.valueOf(modifierDateDebutfx.getValue());
        Date date_fin = java.sql.Date.valueOf(modifierDateFinfx.getValue());
        int points = Integer.valueOf(modifierPointsOffrefx.getText());
        double prix = Double.valueOf(modifierPrixOffrefx.getText());
        double pourcentage = Double.valueOf(modifierPourcentageOffrefx.getText());
        
    
        
 
        LocalDate currentDate = LocalDate.now(); // Gets the current date
        String dateStringlocal = currentDate.toString();
        String date_debut_string = date_debut.toString();
        String date_fin_string = date_fin.toString();
        int comparison = date_debut.compareTo(date_fin);
        int comparaison2 = date_debut_string.compareTo(dateStringlocal);
        int comparaison3 = date_fin_string.compareTo(dateStringlocal);
      
      
    if(modifierPointsOffrefx.getText().isEmpty()){
            showAlert("La case des points est vide!");
      }else if(modifierPrixOffrefx.getText().isEmpty()){
            showAlert("La case des prix est vide!");
      }else if(modifierPourcentageOffrefx.getText().isEmpty()){
            showAlert("La case de la pourcentage est vide!");
      }if(verificationPointsModifier){
            showAlert("les points sont invalide!");
      }else if(verificationPrixModifier){
            showAlert("le prix est invalide!");
      }else if(verificationPourcentageModifier){
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
            Offre even = new Offre(AfficherOffreController.E.getId(),AbonnementModifierfx.getValue().getId(),points,prix,pourcentage,date_debut,date_fin);
      
            inter.modifierOffre(even);
            Notifications notificationBuilder = Notifications.create()
                .title("Offre modifié avec succès")
                .text("Votre offre a été modifié avec succès")
                .hideAfter(Duration.seconds(7))
                .position(Pos.CENTER)
                .graphic(null)
                .darkStyle();
                notificationBuilder.showInformation();
        
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
    private void verifPointModifier(KeyEvent event) {
    
        int nbChar = 0;
        for (int i = 1; i < modifierPointsOffrefx.getText().trim().length(); i++) {
            char ch = modifierPointsOffrefx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labelPointsModifier.setText("Points valide");
            labelPointsModifier.setTextFill(Color.GREEN);

            verificationPointsModifier = false;
        } else {
            labelPointsModifier.setText("invalide points \n"
                    + " Il exist des char");
            labelPointsModifier.setTextFill(Color.RED);
            verificationPointsModifier = true;
            

        }

    
    }
    
    @FXML
    private void verifPrixModifier(KeyEvent event) 
    {
    
        int nbChar = 0;
        for (int i = 1; i < modifierPrixOffrefx.getText().trim().length(); i++) {
            char ch = modifierPrixOffrefx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labelPrixModifier.setText("Prix valide");
            labelPrixModifier.setTextFill(Color.GREEN);

            verificationPrixModifier = false;
        } else {
            labelPrixModifier.setText("invalide Prix \n"
                    + " Il exist des char");
            verificationPrixModifier = true;

        }

    
}
    @FXML
    private void verifPourcentageModifier(KeyEvent event) {
    
        int nbChar = 0;
        for (int i = 1; i < modifierPourcentageOffrefx.getText().trim().length(); i++) {
            char ch = modifierPourcentageOffrefx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labelPourcentageModifier.setText("Pourcentage valide");
            labelPourcentageModifier.setTextFill(Color.GREEN);

            verificationPointsModifier = false;
        } else {
            labelPourcentageModifier.setText("invalide Pourcentage \n"
                    + " Il exist des char");
            verificationPointsModifier = true;

        }

    
    }
    
    @FXML
    private void retourlisteOffreModifier(ActionEvent event)  
    {
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
