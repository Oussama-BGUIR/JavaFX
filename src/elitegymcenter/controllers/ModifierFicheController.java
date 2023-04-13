/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Fiche;
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
import elitegymcenter.interfaces.FicheCRUD;
import elitegymcenter.services.ServiceFicheCRUD;
import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierFicheController implements Initializable {

    @FXML
    private TextField modifiernomFichefx;
    @FXML
    private TextField modifierprenomFichefx;
    @FXML
    private TextField modifieremailFichefx;
        @FXML
    private TextField modifiernumtelFichefx;
    @FXML
    private TextField modifierdescriptionFichefx;
   //   @FXML
  //  private TextField modifierNom_nutritioniste_idFichefx;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifiernomFichefx.setText(String.valueOf(FicheController.F.getNom()));
        modifierprenomFichefx.setText(String.valueOf(FicheController.F.getPrenom()));
        modifieremailFichefx.setText(String.valueOf(FicheController.F.getEmail()));
        modifiernumtelFichefx.setText(String.valueOf(FicheController.F.getNumtel()));
        modifierdescriptionFichefx.setText(String.valueOf(FicheController.F.getDescription()));
     //    modifierNom_nutritioniste_idFichefx.setText(String.valueOf(FicheController.R.getNom_nutritioniste_id()));
       
    }    
    

    @FXML
    private void modifierFiche(ActionEvent event) 
    {
         
        FicheCRUD inter = new ServiceFicheCRUD();
        
        String nom = modifiernomFichefx.getText();
        String prenom = modifierprenomFichefx.getText();
        String email = modifieremailFichefx.getText();
        int numtel = Integer.valueOf(modifiernumtelFichefx.getText());
        String description = modifierdescriptionFichefx.getText();
      //  int Nom_nutritioniste_id = Integer.valueOf( modifierNom_nutritioniste_idFichefx.getText());
        
        
        System.out.println(FicheController.F.getId());
       // Fiche rd = new Fiche(FicheController.R.getId(), FicheController.R.getNumtel(), FicheController.R.getNom_nutritioniste_id() ,nom, prenom, email, date,description );
      
     //   inter.modifierFiche(rd);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Fiche.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(FicheController.class.getName()).log(Level.SEVERE, null, ex);

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
