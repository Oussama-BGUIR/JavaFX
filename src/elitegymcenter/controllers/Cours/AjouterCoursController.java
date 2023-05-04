/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Cours;

import elitegymcenter.entities.Planning;
import elitegymcenter.entities.Cours;
import elitegymcenter.services.ServicePlanningCRUD;
import elitegymcenter.services.ServiceCoursCRUD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class AjouterCoursController implements Initializable {

    @FXML
    private TextField nomCoursfx;
    @FXML
    private TextField dureeCoursfx;
    @FXML
    private TextField salleCoursfx;

    @FXML
	private TextField urltf;
 
	 
	
	private File file;
    @FXML
    private TextField planningidfx;
 
    @FXML
    private Label labelnom;
    
    @FXML
    private Label labelduree;
   
    @FXML
    private Label labelsalle;

    @FXML
    private Label labelplanningid;

    
    
    @FXML
    private ImageView checknom;

    @FXML
    private ImageView checkduree;


    @FXML
    private ImageView checksalle;

    @FXML
    private ImageView checkplanningid;

    private Stage stage; 
    private Scene scene;
    private Parent root;
	private ObservableList<String>UserList;

	@FXML
    private DatePicker date;
	@FXML
	private ComboBox<Planning> comboBoxUser;
	@FXML
	private void importer(ActionEvent event) {
		
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("S�lectionnez un fichier PNG");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg"));
        File fichierSelectionne = fileChooser.showOpenDialog(stage);

        if (fichierSelectionne != null) {
        	urltf.setText(fichierSelectionne.getName());
            file = fichierSelectionne;
        }
		
	}
    /**
     * Initializes the controller class.
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO
		
		ServicePlanningCRUD sc = new ServicePlanningCRUD();
		UserList = FXCollections.observableArrayList();
		setComboBoxItems();
 
		};
		private void setComboBoxItems() {
			ServicePlanningCRUD sc = new ServicePlanningCRUD();

			// Retrieve all reclamations and their corresponding users
			ObservableList<Planning> users = sc.afficherPlanning();

			// Add the user names to the list

			// Set the items of the combo box
			comboBoxUser.setItems(users);
			
			comboBoxUser.setConverter(new StringConverter<Planning>() {
	            @Override
	            public String toString(Planning object) {
	                if (object != null) {
	                    return object.getDescription();
	                } else return "";
	            }

	            @Override
	            public Planning fromString(String string) {
	                return comboBoxUser.getSelectionModel().getSelectedItem();
	            }
	        });
		}

    @FXML
    private void AjoutCours(ActionEvent event) {
        
    	String salle;
		String nom ;
		salle = salleCoursfx.getText();
 
		nom = nomCoursfx.getText();
	     if (date.getValue() == null || salleCoursfx.getText().isEmpty()||nomCoursfx.getText().isEmpty()) {
	            // Affichage d'une alerte en cas de champ vide
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.setTitle("Erreur de saisie");
	            alert.setHeaderText(null);
	            alert.setContentText("Veuillez remplir tous les champs!");
	            alert.showAndWait();
	        }
	     else {

	    	 LocalDate localDate = date.getValue();
	    	 LocalDateTime localDateTime = localDate.atStartOfDay();
        Cours C = new Cours (comboBoxUser.getSelectionModel().getSelectedItem().getId(), nom, localDateTime, salle,file.getAbsolutePath());
 
        ServiceCoursCRUD ServiceCours = new ServiceCoursCRUD();
        ServiceCours.ajouterCours(C);
        Alert alert1 = new Alert(AlertType.INFORMATION);
        alert1.setTitle("Confirmation");
        alert1.setHeaderText(null);
        alert1.setContentText("Ajouter Cours est avec succers!!!  " );
        alert1.showAndWait();
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Cours/Cours.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);

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
        root = FXMLLoader.load(getClass().getResource("../../gui/Cours/Cours.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListCours");
        stage.setScene(scene);
        stage.show();
                
    }
}
    
