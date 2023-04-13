/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Rdv;
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
import elitegymcenter.interfaces.RdvCRUD;
import elitegymcenter.services.ServiceRdvCRUD;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierRdvController implements Initializable {

    @FXML
    private TextField modifiernomRdvfx;
    @FXML
    private TextField modifierprenomRdvfx;
    @FXML
    private TextField modifieremailRdvfx;
    @FXML
    private DatePicker modifierdateRdvfx;
        @FXML
    private TextField modifiernumtelRdvfx;
    @FXML
    private TextField modifierdescriptionRdvfx;
   //   @FXML
  //  private TextField modifierNom_nutritioniste_idRdvfx;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifiernomRdvfx.setText(String.valueOf(RdvController.R.getNom()));
        modifierprenomRdvfx.setText(String.valueOf(RdvController.R.getPrenom()));
        modifieremailRdvfx.setText(String.valueOf(RdvController.R.getEmail()));
        modifiernumtelRdvfx.setText(String.valueOf(RdvController.R.getNumtel()));
        modifierdescriptionRdvfx.setText(String.valueOf(RdvController.R.getDescription()));
     //    modifierNom_nutritioniste_idRdvfx.setText(String.valueOf(RdvController.R.getNom_nutritioniste_id()));
       
    }    
    

    @FXML
    private void modifierRdv(ActionEvent event) 
    {
         
        RdvCRUD inter = new ServiceRdvCRUD();
        
        String nom = modifiernomRdvfx.getText();
        String prenom = modifierprenomRdvfx.getText();
         String email = modifieremailRdvfx.getText();
         Date date = java.sql.Date.valueOf(modifierdateRdvfx.getValue());
        int numtel = Integer.valueOf(modifiernumtelRdvfx.getText());
        String description = modifierdescriptionRdvfx.getText();
      //  int Nom_nutritioniste_id = Integer.valueOf( modifierNom_nutritioniste_idRdvfx.getText());
        
        
        System.out.println(RdvController.R.getId());
       // Rdv rd = new Rdv(RdvController.R.getId(), RdvController.R.getNumtel(), RdvController.R.getNom_nutritioniste_id() ,nom, prenom, email, date,description );
      
     //   inter.modifierRdv(rd);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Rdv/Rdv.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);

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
