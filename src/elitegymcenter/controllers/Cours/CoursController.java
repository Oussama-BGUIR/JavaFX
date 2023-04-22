/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Cours;


import elitegymcenter.entities.Cours;

import elitegymcenter.services.ServiceCoursCRUD;
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
import elitegymcenter.interfaces.CoursCRUD;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class CoursController implements Initializable {

    @FXML
    private ListView<Cours> AffichageListeCoursBackfx;
    static Cours C = new Cours();
    static int id, planning_id ;
   
    static String nom,duree ,salle;

    private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Cours> list1 = AffichageListeCoursBackfx;
        CoursCRUD inter = new ServiceCoursCRUD();
        List<Cours> list2 = inter.afficherCours();
        for (int i = 0; i < list2.size(); i++) 
        {
            Cours C = list2.get(i);
            list1.getItems().add(C); // add Evenement to ListView
        } 
        
    }     
        

    @FXML
    private void modifierCoursBack(ActionEvent event) {
      
        ListView<Cours> list = AffichageListeCoursBackfx;
        CoursCRUD inter = new ServiceCoursCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Cours c = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = c.getNom();
        int planning_id = c.getPlanning_id();
        String duree = c.getDuree();
        String salle = c.getSalle();
        C=c;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Cours/ModifierCours.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterCoursBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Cours/AjouterCours.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterCoursController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerCoursBack(ActionEvent event) {
        
    
      ListView<Cours> list = AffichageListeCoursBackfx;
        CoursCRUD inter = new ServiceCoursCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cours C = list.getSelectionModel().getSelectedItem();
            inter.supprimerCours(C.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("sélectionner un Cours pour le supprimer ");
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
        public void GoToPlanning(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../../gui/Planning/Planning.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListPlanning");
        stage.setScene(scene);
        stage.show();
                
    }
}
    

