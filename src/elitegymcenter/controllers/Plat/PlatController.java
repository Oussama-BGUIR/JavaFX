/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Plat;


import elitegymcenter.entities.Plat;

import elitegymcenter.interfaces.PlatCRUD;
import elitegymcenter.services.ServicePlatCRUD;
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

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class PlatController implements Initializable {

    @FXML
    private ListView<Plat> AffichageListePlatBackfx;
    static Plat P = new Plat();
    static int id,menu_id , prix, calorie;
    static boolean disponibilte;
    static String nom,description ,image;

           private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Plat> list1 = AffichageListePlatBackfx;
        PlatCRUD inter = new ServicePlatCRUD();
        List<Plat> list2 = inter.afficherPlat();
        for (int i = 0; i < list2.size(); i++) 
        {
            Plat P = list2.get(i);
            list1.getItems().add(P); // add Evenement to ListView
        } 
        
    }     
        

    @FXML
    private void modifierPlatBack(ActionEvent event) {
      
        ListView<Plat> list = AffichageListePlatBackfx;
        PlatCRUD inter = new ServicePlatCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Plat p = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = p.getNom();
        int prix = p.getPrix();
        int menu_id = p.getMenu_id();
        String description = p.getDescription();
        int calorie = p.getCalorie();
        boolean disponibilte = p.getDisponibilte();
        String image = p.getImage();
        P=p;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Plat/ModifierPlat.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(PlatController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterPlatBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Plat/AjouterPlat.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerPlatBack(ActionEvent event) {
        
    
      ListView<Plat> list = AffichageListePlatBackfx;
        PlatCRUD inter = new ServicePlatCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Plat P = list.getSelectionModel().getSelectedItem();
            inter.supprimerPlat(P.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("sélectionner un plat pour le supprimer ");
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
        public void GoToMenu(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../../gui/Menu/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListMenu");
        stage.setScene(scene);
        stage.show();
                
    }
}
    

