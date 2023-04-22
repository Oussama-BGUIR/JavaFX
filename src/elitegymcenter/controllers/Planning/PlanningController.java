/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Planning;

import elitegymcenter.entities.Planning;
import elitegymcenter.services.ServicePlanningCRUD;
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
import elitegymcenter.interfaces.PlanningCRUD;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class PlanningController implements Initializable {

    @FXML
    private ListView<Planning> AffichageListePlanningsBackfx;
    static Planning P = new Planning();
    static int id;
    static String semaine,description ;

    private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Planning> list1 = AffichageListePlanningsBackfx;
        PlanningCRUD inter = new ServicePlanningCRUD();
        List<Planning> list2 = inter.afficherPlanning();
        for (int i = 0; i < list2.size(); i++) 
        {
            Planning P = list2.get(i);
            list1.getItems().add(P); // add Evenement to ListView
        } 
        
    }     
        

    @FXML
    private void modifierPlanningBack(ActionEvent event) {
      
        ListView<Planning> list = AffichageListePlanningsBackfx;
        PlanningCRUD inter = new ServicePlanningCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Planning p = list.getSelectionModel().getSelectedItem();
 
       
       
        String semaine = p.getSemaine();
        String description = p.getDescription();
        
        P=p;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Planning/ModifierPlanning.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(PlanningController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterPlanningBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Planning/AjouterPlanning.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterPlanningController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerPlanningBack(ActionEvent event) {
        
    
        ListView<Planning> list = (ListView<Planning>) AffichageListePlanningsBackfx;
        PlanningCRUD inter = new ServicePlanningCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Planning P = list.getSelectionModel().getSelectedItem();
            inter.supprimerPlanning(P.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("sélectionner un Planning pour le supprimer ");
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
        public void GoToCours(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../../gui/Cours/Cours.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListOCours");
        stage.setScene(scene);
        stage.show();
                
    }
}
    

