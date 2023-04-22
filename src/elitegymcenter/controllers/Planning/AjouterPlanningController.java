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

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class AjouterPlanningController implements Initializable {

    @FXML
    private TextField semainePlanningfx;
    @FXML
    private TextField descriptionPlanningfx;
 
    
    
    
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
        for (int i = 1; i < semainePlanningfx.getText().trim().length(); i++) {
            char ch = semainePlanningfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && semainePlanningfx.getText().trim().length() >=5) {
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
        for (int i = 1; i < descriptionPlanningfx.getText().trim().length(); i++) {
            char ch = descriptionPlanningfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && descriptionPlanningfx.getText().trim().length() >=20) {
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
        
    }    

    @FXML
    private void AjoutPlanning (ActionEvent event) {
        
        if(semainePlanningfx.getText().isEmpty()){
            showAlert("La case nom est vide!");
        }else if(descriptionPlanningfx.getText().isEmpty()){
            showAlert("La case description est vide!");
        }else{
            String semaine = semainePlanningfx.getText();
        String description = descriptionPlanningfx.getText();
        

        Planning P = new Planning(semaine, description);
        ServicePlanningCRUD ServiceMenu = new ServicePlanningCRUD();
        ServiceMenu.ajouterPlanning(P);
        
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

                   
    }
    /*
       private boolean verifString(TextField chaine) {
 
        for (int i = 1; i < chaine.getText().trim().length(); i++) {
            char ch = chaine.getText().charAt(i);
            if (Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
        
    }
*/
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
        stage.setTitle("ListPlanning");
        stage.setScene(scene);
        stage.show();
                
    }
}
    
