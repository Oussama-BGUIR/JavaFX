/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Planning;

import elitegymcenter.entities.Planning;
import elitegymcenter.services.ServicePlanningCRUD;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

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
    
     
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjoutPlanning (ActionEvent event) throws SQLException {
        
    	String semaine;
		String description ;
		semaine = semainePlanningfx.getText();
 
		description = descriptionPlanningfx.getText();
	     if ( descriptionPlanningfx.getText().isEmpty()||semainePlanningfx.getText().isEmpty()) {
	            // Affichage d'une alerte en cas de champ vide
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur de saisie");
	            alert.setHeaderText(null);
	            alert.setContentText("Veuillez remplir tous les champs!");
	            alert.showAndWait();
	        }
	     else {
 
        
	    	  String text = "Code QR pour le planning " + semaine +"  " +description ;
	            ByteArrayOutputStream out = QRCode.from(text).to(ImageType.PNG).stream();

	            // Convertir l'image en une chaîne de caractères encodée en base64
	            String base64Image = Base64.getEncoder().encodeToString(out.toByteArray());
	            
	            System.out.println("Base64 avant : " + base64Image);
	            
 	            // Afficher l'image dans une alerte
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("Code QR pour  article " + semaine +description);
	            alert.setHeaderText(null);

	            // Créer un ImageView avec l'image du code QR encodée en base64
	            ImageView imageView = new ImageView();
	            imageView.setImage(new Image(new ByteArrayInputStream(Base64.getDecoder().decode( base64Image))));

	            // Ajouter l'ImageView à la boîte de dialogue de l'alerte
	            alert.getDialogPane().setContent(imageView);

	            // Afficher l'alerte
	            alert.showAndWait();
        Planning P = new Planning(semaine, description,base64Image);
        ServicePlanningCRUD ServiceMenu = new ServicePlanningCRUD();
        ServiceMenu.ajouterPlanning(P);
        Alert alert1 = new Alert(AlertType.INFORMATION);
        alert1.setTitle("Confirmation");
        alert1.setHeaderText(null);
        alert1.setContentText("Ajouter Planning est avec succers!!!  " );
        alert1.showAndWait();
        
        try {

                    
                root = FXMLLoader.load(getClass().getResource("../../gui/Planning/Planning.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("ListCours");
                stage.setScene(scene);
                stage.show();
          
        } catch (IOException ex) {
          
 
        }
            
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
        stage.setTitle("ListPlanning");
        stage.setScene(scene);
        stage.show();
                
    }
        @FXML
        public void ajouterPlanningBack(ActionEvent event) throws IOException  {
        
      
        }
}
    
