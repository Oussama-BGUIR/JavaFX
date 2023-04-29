package elitegymcenter.controllers;

import elitegymcenter.entities.Categorie;
import elitegymcenter.entities.Produit;
import elitegymcenter.services.ServiceCategorieCRUD;
import elitegymcenter.services.ServiceProduitCRUD;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField nomProduitfx;
    @FXML
    private TextField descriptionProduitfx;
    @FXML
    private TextField prixProduitfx;
    @FXML
    private TextField categorie_idProduitfx;
    @FXML
    
    private TextField imageProduitfx;
        @FXML
    private ComboBox<Categorie> category;
        
        @FXML
    private ImageView image;

    @FXML
    private Button importimage;
    @FXML
    private AnchorPane pane;
    

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            category.getItems().addAll(serviceCategorie.afficherCategorie());
        } catch (Exception e) {
        }
        // TODO
        
    }    

    
     ServiceCategorieCRUD serviceCategorie = new ServiceCategorieCRUD();

    
    @FXML
    private void AjoutProduit(ActionEvent event) {
        
        
        
      

        String nom = nomProduitfx.getText();
        int prix = Integer.valueOf(prixProduitfx.getText());
       
         //String categorie_id = categorie_idProduitfx.getText();
        String description = descriptionProduitfx.getText();
        //String image = imageProduitfx.getText();
        Categorie C1 = category.getValue();
        if(nom.isEmpty()){
            showAlert("La case nom est vide!");
        }else if(description.isEmpty()){
            showAlert("La case description est vide!");
            }else
       if (verifString(nomProduitfx))
        {
             showAlert("le nom doit contenir que des lettres!");
        }else{
            
            showAlert("Etes vous sur d'ajouter un produit ?");
        

       String imagePath = selectedImageFile.toString();
            Produit P = new Produit (C1, prix, nom, description,imagePath);
        ServiceProduitCRUD ServiceProduit = new ServiceProduitCRUD();
        ServiceProduit.ajouterProduit(P);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Produit.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }
                   
    }
    }
    
    @FXML
    private void retourListe(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Produit.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
    }
        private boolean verifString(TextField chaine) {
 
        for (int i = 1; i < chaine.getText().trim().length(); i++) {
            char ch = chaine.getText().charAt(i);
            if (Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
        
    }
    private void showAlert(String message) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        private File selectedImageFile;

    public void inventoryImportBtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        selectedImageFile = openFile.showOpenDialog(pane.getScene().getWindow());

        if (selectedImageFile != null) {
            image .setImage(new Image(selectedImageFile.toURI().toString(), 142, 115, false, true));

//            path = file.getAbsolutePath();
//            imagev = new Image(file.toURI().toString(), 134, 133, false, true);
//
//            image.setImage(imagev);
        }
    }
    }

    