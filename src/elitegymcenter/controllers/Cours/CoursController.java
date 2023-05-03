/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Cours;


import elitegymcenter.entities.Cours;
import elitegymcenter.entities.Planning;
import elitegymcenter.services.ServiceCoursCRUD;
import elitegymcenter.services.ServicePlanningCRUD;
 
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
 

import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
 

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class CoursController implements Initializable {

	 @FXML
	    private TableView<Cours> tableView;
	 

	    private Stage stage; 
	    private Scene scene;
	    private Parent root;
	    @FXML
	    private TextField filtre ;
	 
	    @FXML
	    private TableColumn<Cours, String> ID;
	    @FXML
	    private TableColumn<Cours, String> plan;
	    @FXML
	    private TableColumn<Cours, String> date;
	    @FXML
	    private TableColumn<Cours, String> salle;
	    @FXML
	    private TableColumn<Cours, String>  image;
	    ObservableList<Cours> obList;
	     
     
	  ServiceCoursCRUD a =new ServiceCoursCRUD();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obList = a.afficherCours();
        
        
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
         salle.setCellValueFactory(new PropertyValueFactory<>("salle"));
       date.setCellValueFactory(new PropertyValueFactory<>("duree"));
       plan.setCellValueFactory(new PropertyValueFactory<>("planningDescription"));
       image.setCellValueFactory(new PropertyValueFactory<>("image"));
       Callback<TableColumn<Cours, String>, TableCell<Cours, String>> cellFactoryImage
       = //
       new Callback<TableColumn<Cours, String>, TableCell<Cours, String>>() {
   @Override
   public TableCell<Cours, String> call(final TableColumn<Cours, String> param) {
       final TableCell<Cours, String> cell = new TableCell<Cours, String>() {

           @Override
           public void updateItem(String item, boolean empty) {
               super.updateItem(item, empty);
               if (empty) {
                   setGraphic(null);
                   setText("Aucune image n'existe dans cette liste");
               } else {
                   ImageView imagev = new ImageView(new Image("file:/"+item));
                   imagev.setFitHeight(120);
                   imagev.setFitWidth(200);
                   setGraphic(imagev);
                   setText(null);
                   //System.out.println(item);
               }
           }
       };
       return cell;
   }
};

image.setCellFactory(cellFactoryImage);
  
    tableView.setItems(obList);

     
    }
    @FXML
    private void modifierCoursBack(ActionEvent event) {
      

        Cours selectedUser = tableView.getSelectionModel().getSelectedItem();
   if (selectedUser == null) {
       // Afficher un message d'erreur
       Alert alert = new Alert(AlertType.ERROR);
       alert.setTitle("Erreur");
       alert.setHeaderText("Impossible de modifier");
       alert.setContentText("Veuillez sélectionner un  Cours pour modifier !");
       alert.showAndWait();
   } else {
       // Show an input dialog to get the new Planning details.
       Dialog<Cours> dialog = new Dialog<>();
       dialog.setTitle("Modifier un  Cours");
       dialog.setHeaderText("Modifier les champs");

       // Set the default value of the input fields to the current Planning details.
       TextField nom = new TextField(selectedUser.getNom());
  
       TextField salle = new TextField(selectedUser.getSalle());

       // Add the input fields to the dialog pane.
       GridPane grid = new GridPane();
       grid.add(new Label("Nom:"), 1, 1);
       grid.add(nom, 2, 1);
       grid.add(new Label("Salle:"), 1, 2);
       grid.add(salle, 2, 2);
       
       dialog.getDialogPane().setContent(grid);

       // Add buttons to the dialog pane.
       ButtonType modifierButtonType = new ButtonType("Modifier", ButtonData.OK_DONE);
       dialog.getDialogPane().getButtonTypes().addAll(modifierButtonType, ButtonType.CANCEL);

       // Convert the result to a Planning object when the modify button is clicked.
       dialog.setResultConverter(dialogButton -> {
           if (dialogButton == modifierButtonType) {
               return new Cours(
                       nom.getText(),
                       salle.getText()
                    
               );
           }
           return null;
       });

       Optional<Cours> result = dialog.showAndWait();
       if (result.isPresent()) {
           // Update the selected Planning with the new values.
           selectedUser.setSalle(result.get().getSalle());
           selectedUser.setNom(result.get().getNom());
          

           // Call the service method to update the Planning in the database.
           ServiceCoursCRUD inter = new ServiceCoursCRUD();
           inter.modifierCours(selectedUser);

           // Show a confirmation alert.
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Succès");
           alert.setHeaderText(" Cours a été modifié avec succès");
           alert.setContentText("Les modifications ont été enregistrées.");
           alert.showAndWait();
       }

   }
   tableView.refresh();
     
        
    
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
    	// obtenir l'entrée sélectionnée dans la tableView
        Cours selectedLN = tableView.getSelectionModel().getSelectedItem();
        if (selectedLN == null) {
            // Afficher un message d'erreur si aucune entrée n'est sélectionnée
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de supprimer la Cours ");
            alert.setContentText("Veuillez sÃ©lectionner une Cours Ã  supprimer !");
            alert.showAndWait();
        } else {
            // demander une confirmation avant de supprimer l'entrée
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer cet Planning ?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText(null);
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    // supprimer l'entrée de la base de données
                    ServiceCoursCRUD inter = new ServiceCoursCRUD();
                    inter.supprimerCours(selectedLN.getId());

                    // rafraîchir la tableView
                    tableView.getItems().remove(selectedLN);
                    tableView.refresh();

                    // afficher une alerte de réussite après la suppression
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Cours a été supprimé avec succès !");
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
        public void GoToPlanning(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../../gui/Planning/Planning.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListPlanning");
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
                        ObservableList<Cours> filteredList = FXCollections.observableArrayList();
                        boolean productFound = false;
                        for (Cours b : obList) {
                            // search for name or description
                        	  LocalDateTime dureeObjet = b.getDuree();
                      	    if ((b.getSalle().toLowerCase().contains(searchText.toLowerCase())) 
                      	            || (dureeObjet.toString().toLowerCase().contains(searchText.toLowerCase()))) {
                      	        filteredList.add(b);
                                productFound = true;
                            }
                        }
                        if (!productFound) {
                            Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Cours non trouv�");
                            alert.setHeaderText("Aucun cours ne correspond � votre recherche");
                            alert.setContentText("Veuillez essayer une autre recherche.");
                            alert.showAndWait();
                        }
                        tableView.setItems(filteredList);
                    }
                }
            }
            private boolean isAscending = true;
            @FXML
            public void Tri(){
            	  Comparator<Cours> dureeComparator = new Comparator<Cours>() {
            	        @Override
            	        public int compare(Cours c1, Cours c2) {
            	            if (isAscending) {
            	                return c1.getDuree().compareTo(c2.getDuree());
            	            } else {
            	                return c2.getDuree().compareTo(c1.getDuree());
            	            }
            	        }
            	    };

            	    ObservableList<Cours> coursList = tableView.getItems();
            	    coursList.sort(dureeComparator);

            	    // Reverse the sorting order for the next call to Tri
            	    isAscending = !isAscending;
                }

}
    

