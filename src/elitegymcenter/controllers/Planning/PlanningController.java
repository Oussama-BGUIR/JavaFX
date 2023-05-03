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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import elitegymcenter.interfaces.PlanningCRUD;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class PlanningController implements Initializable {

    @FXML
    private TableView<Planning> tableView;
 

    private Stage stage; 
    private Scene scene;
    private Parent root;
    @FXML
    private TextField filtre ;
 
    @FXML
    private TableColumn<Planning, String> ID;
    @FXML
    private TableColumn<Planning, String> semaines;
    @FXML
    private TableColumn<Planning, String> description;
    ObservableList<Planning> obList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
         PlanningCRUD inter = new ServicePlanningCRUD();
         obList = inter.afficherPlanning();
 
        
         ID.setCellValueFactory(new PropertyValueFactory<>("id"));
          semaines.setCellValueFactory(new PropertyValueFactory<>("semaine"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
 
// Sort the list by price ascending
     // Load the data from the CompetitionService into the TableView
   
     tableView.setItems(obList);
    }     
        

    @FXML
    private void modifierPlanningBack(ActionEvent event) {
    	
    	   
        Planning selectedUser = tableView.getSelectionModel().getSelectedItem();
   if (selectedUser == null) {
       // Afficher un message d'erreur
       Alert alert = new Alert(AlertType.ERROR);
       alert.setTitle("Erreur");
       alert.setHeaderText("Impossible de modifier");
       alert.setContentText("Veuillez sélectionner un  Planning pour modifier !");
       alert.showAndWait();
   } else {
       // Show an input dialog to get the new Planning details.
       Dialog<Planning> dialog = new Dialog<>();
       dialog.setTitle("Modifier un  Planning");
       dialog.setHeaderText("Modifier les champs");

       // Set the default value of the input fields to the current Planning details.
       TextField firstNameField = new TextField(selectedUser.getSemaine());
       TextField lastNameField = new TextField(selectedUser.getDescription());
        

       // Add the input fields to the dialog pane.
       GridPane grid = new GridPane();
       grid.add(new Label("Semaine:"), 1, 1);
       grid.add(firstNameField, 2, 1);
       grid.add(new Label("Description:"), 1, 2);
       grid.add(lastNameField, 2, 2);
       
       dialog.getDialogPane().setContent(grid);

       // Add buttons to the dialog pane.
       ButtonType modifierButtonType = new ButtonType("Modifier", ButtonData.OK_DONE);
       dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

       // Convert the result to a Planning object when the modify button is clicked.
       dialog.setResultConverter(dialogButton -> {
           if (dialogButton == modifierButtonType) {
               return new Planning(
                       firstNameField.getText(),
                       lastNameField.getText()
                    
               );
           }
           return null;
       });

       Optional<Planning> result = dialog.showAndWait();
       if (result.isPresent()) {
           // Update the selected Planning with the new values.
           selectedUser.setSemaine(result.get().getSemaine());
           selectedUser.setDescription(result.get().getDescription());
          

           // Call the service method to update the Planning in the database.
           PlanningCRUD inter = new ServicePlanningCRUD();
           inter.modifierPlanning(selectedUser);

           // Show a confirmation alert.
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Succès");
           alert.setHeaderText(" Planning a été modifié avec succès");
           alert.setContentText("Les modifications ont été enregistrées.");
           alert.showAndWait();
       }

   }
   tableView.refresh();
     
    
    }


    @FXML
    private void ajouterPlanningBack(ActionEvent event) {
        
        
        try {	  
        	root = FXMLLoader.load(getClass().getResource("../../gui/Planning/AjouterPlanning.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("AjoutPlanning");
        stage.setScene(scene);
        stage.show();

           
          
        } catch (IOException ex) {
            System.out.println("Erreur\n"+ex.toString());
 
        }
    
        
    }

    @FXML
    private void supprimerPlanningBack(ActionEvent event) {
        
    
        // obtenir l'entrée sélectionnée dans la tableView
        Planning selectedLN = tableView.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur si aucune entrée n'est sélectionnée
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer la Planning ");
            alert.setContentText("Veuillez sÃ©lectionner une Planning Ã  supprimer !");
            alert.showAndWait();
        } else {
            // demander une confirmation avant de supprimer l'entrée
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer cet Planning ?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText(null);
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    // supprimer l'entrée de la base de données
                    PlanningCRUD inter = new ServicePlanningCRUD();
                    inter.supprimerPlanning(selectedLN.getId());

                    // rafraîchir la tableView
                    tableView.getItems().remove(selectedLN);
                    tableView.refresh();

                    // afficher une alerte de réussite après la suppression
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Planning a été supprimé avec succès !");
                    success.setHeaderText(null);
                    success.showAndWait();
                }
            });
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
                @FXML
                public void handleSearch(KeyEvent event) {
                    if (event.getCode() == KeyCode.ENTER) {
                        
						String searchText = filtre.getText().trim();
                        if (searchText.isEmpty()) {
                            tableView.setItems(obList);
                        } else {
                            ObservableList<Planning> filteredList = FXCollections.observableArrayList();
                            boolean productFound = false;
                            for (Planning b : obList) {
                                // search for name or description
                                if ((b.getDescription().toLowerCase().contains(searchText.toLowerCase())) 
                                        || (b.getSemaine().toLowerCase().contains(searchText.toLowerCase()))) {
                                    filteredList.add(b);
                                    productFound = true;
                                }
                            }
                            if (!productFound) {
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Planning non trouv�");
                                alert.setHeaderText("Aucun planning ne correspond � votre recherche");
                                alert.setContentText("Veuillez essayer une autre recherche.");
                                alert.showAndWait();
                            }
                            tableView.setItems(filteredList);
                        }
                    }
                }
                @FXML
                public void Tri() {
                    ObservableList<Planning> planningList = tableView.getItems();

                    Comparator<Planning> semaineComparator = new Comparator<Planning>() {
                        @Override
                        public int compare(Planning p1, Planning p2) {
                        	int semaine1 = Integer.parseInt(p1.getSemaine().replaceAll("\\D+", ""));
                        	int semaine2 = Integer.parseInt(p2.getSemaine().replaceAll("\\D+", ""));

                            return Integer.compare(semaine1, semaine2);
                        }
                    };

                    planningList.sort(semaineComparator);
                }

}
    

