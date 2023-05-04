/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Categorie;
import elitegymcenter.interfaces.CategorieCRUD;
import elitegymcenter.services.ServiceCategorieCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CategorieController implements Initializable {

    @FXML
    private TableView<Categorie> AffichageListeCategoriesBackfx;
    static Categorie C = new Categorie();
    static int id;
    static String nom,description ;
    
       @FXML
    private TableColumn<Categorie, String> descriptionfx;

    @FXML
    private TableColumn<Categorie, Integer> idfx;

    @FXML
    private TableColumn<Categorie, String> nomfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     CategorieCRUD inter = new ServiceCategorieCRUD();
    List<Categorie> list = inter.afficherCategorie();
    ObservableList<Categorie> observableList = FXCollections.observableArrayList(list);
    AffichageListeCategoriesBackfx.setItems(observableList);
    idfx.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
    descriptionfx.setCellValueFactory(new PropertyValueFactory<>("description"));
  
        
    }     
        

    @FXML
    private void modifierCategorieBack(ActionEvent event) {
      
        TableView<Categorie> list = AffichageListeCategoriesBackfx;
        CategorieCRUD inter = new ServiceCategorieCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Categorie c = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = c.getNom();
        String description = c.getDescription();
        
        C=c;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/ModifierCategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterCategorieBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/AjouterCategorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterCategorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerCategorieBack(ActionEvent event) {
        
    
        TableView<Categorie> list = (TableView<Categorie>) AffichageListeCategoriesBackfx;
        CategorieCRUD inter = new ServiceCategorieCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Categorie C = list.getSelectionModel().getSelectedItem();
            inter.supprimerCategorie(C.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("sélectionner une categorie pour le supprimer ");
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
    private void switchProduit(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Produit.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
    }     
    
    @FXML
    private void retour_admin(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/AdminDashboardFXML.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
    }     
    
}
    

