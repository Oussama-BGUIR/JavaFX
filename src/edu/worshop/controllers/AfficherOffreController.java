/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import edu.workshop.services.Offre1CRUD;
import edu.worshop.interfaces.OffreCRUD;
import edu.worshop.model.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shini
 */
public class AfficherOffreController implements Initializable {

    @FXML
    private ListView<Offre> affichageOffrefx;
    
     static Offre E = new Offre();
    static int id, points;

    static double prix,pourcentage;
    static Date date_debut,date_fin;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListView<Offre> list1 = affichageOffrefx;
        OffreCRUD inter = new Offre1CRUD();
        List<Offre> list2 = inter.afficherOffre();
        for (int i = 0; i < list2.size(); i++) 
        {
            Offre E = list2.get(i);
            list1.getItems().add(E); // add Offre to ListView
        }

    }    

    @FXML
    private void supprimerOffre(ActionEvent event) 
    {
        ListView<Offre> list = (ListView<Offre>) affichageOffrefx;
        OffreCRUD inter = new Offre1CRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Offre E = list.getSelectionModel().getSelectedItem();
            inter.supprimerOffre(E.getId());
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
    private void modifierOffre(ActionEvent event) 
    {
        ListView<Offre> list = affichageOffrefx;
        OffreCRUD inter = new Offre1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Offre e = list.getSelectionModel().getSelectedItem();
 
        int points = e.getPoints();
        double pourcentage = e.getPourcentage();
        double prix = e.getPrix();
        Date date_debut = e.getDate_debut();
        Date date_fin = e.getDate_fin();
        
       E=e;
       System.out.println(E.getId());
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/modifierOffre.fxml"));
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
