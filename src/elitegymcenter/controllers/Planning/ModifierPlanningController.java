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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import elitegymcenter.interfaces.PlanningCRUD;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class ModifierPlanningController implements Initializable {

    @FXML
    private TextField modifiersemainePlanning;
    @FXML
    private TextField modifierdescriptionPlanning;

    
    @FXML
    private Label labelsemaine;
    
    @FXML
    private Label labeldescription;
    
   

    
    @FXML
    private ImageView checksemaine;

    @FXML
    private ImageView checkdescription;

   

    private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < modifiersemainePlanning.getText().trim().length(); i++) {
            char ch = modifiersemainePlanning.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && modifiersemainePlanning.getText().trim().length() >=5) {
            labelsemaine.setText ("nom valide :) ");
            labelsemaine.setTextFill(Color.GREEN);
            checksemaine.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelsemaine.setText ("le nom doit etre plus que 5 lettres !");
            labelsemaine.setTextFill(Color.RED);
            checksemaine.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
        @FXML
    private void verifierDesc(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < modifierdescriptionPlanning.getText().trim().length(); i++) {
            char ch = modifierdescriptionPlanning.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && modifierdescriptionPlanning.getText().trim().length() >=20) {
            labeldescription.setText ("Description valide :) ");
            labeldescription.setTextFill(Color.GREEN);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labeldescription.setText ("écrivez plus que 20 lettres !");
            labeldescription.setTextFill(Color.RED);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifiersemainePlanning.setText(String.valueOf(PlanningController.P.getSemaine()));
        modifierdescriptionPlanning.setText(String.valueOf(PlanningController.P.getDescription()));

    }    
    

    @FXML
    private void modifierplanning(ActionEvent event) 
    {
          
        PlanningCRUD inter = new ServicePlanningCRUD();
        
        String semaine = modifiersemainePlanning.getText();
        String description = modifierdescriptionPlanning.getText();
        
        
        
        System.out.println(PlanningController.P.getId());
        Planning plan = new Planning(PlanningController.P.getId(), semaine , description );
      
        inter.modifierPlanning(plan);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Planning/Planning.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(PlanningController.class.getName()).log(Level.SEVERE, null, ex);

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
        public void switchToList(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../../gui/Planning/Planning.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListMenu");
        stage.setScene(scene);
        stage.show();
                
    }
}
