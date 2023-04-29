/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminController implements Initializable {

   private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       @FXML
        public void switchToListRdv(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../gui/Rdv/Rdv.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListRdv");
        stage.setScene(scene);
        stage.show();
            
} 
        
         @FXML
        public void switchToListFiche(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../gui/Fiche.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListFiche");
        stage.setScene(scene);
        stage.show();
        
        
        
}
        
    
}
