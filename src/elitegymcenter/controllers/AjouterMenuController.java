/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Menu;
import elitegymcenter.services.ServiceMenuCRUD;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class AjouterMenuController implements Initializable {

    @FXML
    private TextField nomMenufx;
    @FXML
    private TextField descriptionMenufx;
    @FXML
    private TextField calorieMenufx;
    @FXML
    private TextField imageMenufx;
    @FXML
    private CheckBox disponibiliteMenufx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjoutMenu(ActionEvent event) {
        
        String nom = nomMenufx.getText();
        String description = descriptionMenufx.getText();
        int calorie = Integer.valueOf(calorieMenufx.getText());
        String image = imageMenufx.getText();
        boolean disponibilite = disponibiliteMenufx.isSelected(); // Get the boolean value from CheckBox
       // boolean disponibilite = true ;

        Menu M = new Menu(calorie, disponibilite, nom, description, image);
        ServiceMenuCRUD ServiceMenu = new ServiceMenuCRUD();
        ServiceMenu.ajouterMenu(M);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Menu/Menu.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);

        }
                   
    }
}
    
