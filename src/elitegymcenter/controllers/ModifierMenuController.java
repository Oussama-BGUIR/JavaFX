/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Menu;
import elitegymcenter.interfaces.MenuCRUD;
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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class ModifierMenuController implements Initializable {

    @FXML
    private TextField modifiernomMenu;
    @FXML
    private TextField modifierdescriptionMenu;
    @FXML
    private TextField modifiercalorieMenu;
    @FXML
    private TextField modifierimageMenu;
    @FXML
    private CheckBox modifierdisponibiliteMenu;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifiernomMenu.setText(String.valueOf(MenuController.M.getNom()));
        modifierdescriptionMenu.setText(String.valueOf(MenuController.M.getDescription()));
        modifiercalorieMenu.setText(String.valueOf(MenuController.M.getCalorie()));
        modifierdisponibiliteMenu.setText(String.valueOf(MenuController.M.getDisponibilite()));
        modifierimageMenu.setText(String.valueOf(MenuController.M.getImage()));
    }    
    

    @FXML
    private void modifiermenu(ActionEvent event) 
    {
         
        MenuCRUD inter = new ServiceMenuCRUD();
        
        String nom = modifiernomMenu.getText();
        String description = modifierdescriptionMenu.getText();
        int calorie = Integer.valueOf(modifiercalorieMenu.getText());
        boolean disponibilite = modifierdisponibiliteMenu.isSelected();
        String image = modifierimageMenu.getText();
        
        
        System.out.println(MenuController.M.getId());
        Menu men = new Menu(MenuController.M.getId(), calorie, disponibilite, nom, description, image );
      
        inter.modifierMenu(men);
        
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
    
      private void showAlert(String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
