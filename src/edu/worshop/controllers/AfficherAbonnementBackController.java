/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Abonnement1CRUD;
import edu.worshop.interfaces.AbonnementCRUD;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import java.util.List;
import edu.worshop.model.Abonnement;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shini
 */
public class AfficherAbonnementBackController implements Initializable {

    @FXML
    private ListView<Abonnement> affichageAbonnementFrontfx;
   
    static Abonnement E = new Abonnement();
    static int id, numero;

    static String nom,prenom,email,type;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ListView<Abonnement> list1 = affichageAbonnementFrontfx;
        AbonnementCRUD inter = new Abonnement1CRUD();
        List<Abonnement> list2 = inter.afficherAbonnement();
        for (int i = 0; i < list2.size(); i++) 
        {
            Abonnement E = list2.get(i);
            list1.getItems().add(E); // add Evenement to ListView
        }


    }

    @FXML
    private void supprimerAbonnementBack(ActionEvent event) 
    {
        ListView<Abonnement> list = (ListView<Abonnement>) affichageAbonnementFrontfx;
        AbonnementCRUD inter = new Abonnement1CRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Abonnement E = list.getSelectionModel().getSelectedItem();
            inter.supprimerAbonnement(E.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("Veuillez sélectionner un abonnement à supprimer.");
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
    private void modifierAbonnementBack(ActionEvent event) 
    {
        ListView<Abonnement> list = affichageAbonnementFrontfx;
        AbonnementCRUD inter = new Abonnement1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Abonnement e = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = e.getNom();
        String prenom = e.getPrenom();
        String email = e.getEmail();
        int numero = e.getNumero();
        String type = e.getType();
       E=e;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/modifierAbonnementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }

    @FXML
    private void ajouterAbonnementFront(ActionEvent event) {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutAbonnementFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void ajoutOffre(ActionEvent event) 
    {
        ListView<Abonnement> list = affichageAbonnementFrontfx;
        AbonnementCRUD inter = new Abonnement1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
         Abonnement e = list.getSelectionModel().getSelectedItem();
        E=e;
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherOffreController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }


}


  
    

     
   
