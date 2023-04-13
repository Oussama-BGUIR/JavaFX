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
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterRdvController implements Initializable {

    @FXML
    private TextField nomRdvfx;
    @FXML
    private TextField prenomRdvfx;
    @FXML
    private TextField emailRdvfx;
    @FXML
    private DatePicker dateRdvfx;
        @FXML
    private TextField numtelRdvfx;
    @FXML
    private TextField descriptionRdvfx;
      @FXML
    private TextField Nom_nutritioniste_idRdvfx;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjoutRdv(ActionEvent event) {
        
        String nom = nomRdvfx.getText();
        String prenom = prenomRdvfx.getText();
         String email = emailRdvfx.getText();
        Date date = java.sql.Date.valueOf(dateRdvfx.getValue());
        int numtel = Integer.valueOf(numtelRdvfx.getText());
        String description = descriptionRdvfx.getText();
        int Nom_nutritioniste_id = Integer.valueOf(Nom_nutritioniste_idRdvfx.getText());
        
     
     
        

     //   Rdv R = new Rdv(nom, prenom, email, date, numtel,description,Nom_nutritioniste_id);
        ServiceRdvCRUD ServiceRdv = new ServiceRdvCRUD();
      //  ServiceRdv.ajouterRdv(R);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Menu/Menu.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);

        }
                   
    }
}
    
