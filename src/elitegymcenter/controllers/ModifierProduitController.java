/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Produit;
import elitegymcenter.interfaces.ProduitCRUD;
import elitegymcenter.services.ServiceProduitCRUD;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierProduitController implements Initializable {

    @FXML
    private TextField modifiernomProduit;
    @FXML
    private TextField modifierdescriptionProduit;
    @FXML
    private TextField modifierprixProduit;
    @FXML
    private TextField modifiercategorie_idProduit;
    @FXML
    private TextField modifierimageProduit;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifiernomProduit.setText(String.valueOf(ProduitController.P.getNom()));
        modifierdescriptionProduit.setText(String.valueOf(ProduitController.P.getDescription()));
        modifierprixProduit.setText(String.valueOf(ProduitController.P.getPrix()));
        modifiercategorie_idProduit.setText(String.valueOf(ProduitController.P.getCategorie_id()));
        modifierimageProduit.setText(String.valueOf(ProduitController.P.getImage()));


       
    }    
    

    @FXML
    private void modifierproduit(ActionEvent event) 
    {
         
        ProduitCRUD inter = new ServiceProduitCRUD();
        
        String nom = modifiernomProduit.getText();
        String description = modifierdescriptionProduit.getText();
        int prix = Integer.valueOf(modifierprixProduit.getText());
        String image = modifierimageProduit.getText();
        int categorie_id = Integer.valueOf(modifiercategorie_idProduit.getText());
    
        
        
        System.out.println(ProduitController.P.getId());
        Produit P = new Produit(ProduitController.P.getId(),categorie_id, prix, nom, description,image);
      
        inter.modifierProduit(P);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Produit.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);

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
