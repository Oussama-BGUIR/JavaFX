/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Rdv;
import elitegymcenter.services.ServiceRdvCRUD;
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
import elitegymcenter.interfaces.RdvCRUD;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RdvController implements Initializable {

    @FXML
    private ListView<Rdv> AffichageListeRdvsBackfx;
    static Rdv R = new Rdv();
    static int id;
    static String nom,prenom,email,date,numtel,description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Rdv> list1 = AffichageListeRdvsBackfx;
        RdvCRUD inter = new ServiceRdvCRUD();
        List<Rdv> list2 = inter.afficherRdv();
        for (int i = 0; i < list2.size(); i++) 
        {
            Rdv R = list2.get(i);
            list1.getItems().add(R); // add rdv to ListView
        } 
        
    }     
        

    @FXML
    private void modifierRdvBack(ActionEvent event) {
      
        ListView<Rdv> list = AffichageListeRdvsBackfx;
        RdvCRUD inter = new ServiceRdvCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Rdv r = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = r.getNom();
        String prenom = r.getPrenom();
        String email = r.getEmail();
        Date date = r.getDate();
        String description = r.getDescription();
        int numtel = r.getNumtel();
      //  int Nom_nutritioniste_id = r.getNom_nutritioniste_id();
        
        R=r;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Rdv/ModifierRdv.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterRdvBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Menu/AjouterMenu.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterRdvController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerRdvBack(ActionEvent event) {
        
    
        ListView<Rdv> list = (ListView<Rdv>) AffichageListeRdvsBackfx;
        RdvCRUD inter = new ServiceRdvCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Rdv R = list.getSelectionModel().getSelectedItem();
            inter.supprimerRdv(R.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("sélectionner un rdv pour le supprimer ");
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
    

