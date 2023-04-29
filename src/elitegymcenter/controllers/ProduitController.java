package elitegymcenter.controllers;

import elitegymcenter.entities.Produit;
import elitegymcenter.interfaces.ProduitCRUD;
import elitegymcenter.services.ServiceProduitCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ProduitController implements Initializable {

    @FXML
    private ListView<Produit> AffichageListeProduitsBackfx;
    static Produit P = new Produit();
    static int id,prix,categorie_id;
    static String nom,description ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Produit> list1 = AffichageListeProduitsBackfx;
        ProduitCRUD inter = new ServiceProduitCRUD();
        List<Produit> list2 = inter.afficherProduit();
        for (int i = 0; i < list2.size(); i++) 
        {
            Produit P = list2.get(i);
            list1.getItems().add(P); // add Evenement to ListView
        } 
      searchFilterRdv();
        
    }     
        

    @FXML
    private void modifierProduitBack(ActionEvent event) {
      
        ListView<Produit> list = AffichageListeProduitsBackfx;
        ProduitCRUD inter = new ServiceProduitCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Produit p = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = p.getNom();
        String description = p.getDescription();
        int prix = p.getPrix();
        String categorie_id = p.getCategorie_id();
        String image = p.getImage();
        
        P=p;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/ModifierProduit.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }
@FXML
    private void accueil(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Front.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void ajouterProduitBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/AjouterProduit.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerProduitBack(ActionEvent event) {
        
    
        ListView<Produit> list = (ListView<Produit>) AffichageListeProduitsBackfx;
        ProduitCRUD inter = new ServiceProduitCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Produit P = list.getSelectionModel().getSelectedItem();
            inter.supprimerProduit(P.getId());
            list.getItems().remove(selectedIndex);
        } else {
            showAlert("Sélectionner un produit pour le supprimer ");
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
    private TextField searchTextField;
    
    
    @FXML
    public FilteredList<Produit> recherche(ObservableList matchList) {
        FilteredList<Produit> filterData = new FilteredList<Produit>(matchList, b -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData.setPredicate(SearchModel -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String serachKeeyword = newValue.toLowerCase();
                if (((Produit) SearchModel).getCategory().getNom().toLowerCase().contains(serachKeeyword)) {
                    return true;
                } else if ((((Produit) SearchModel).getNom() + "").toLowerCase().contains(serachKeeyword)) {
                    return true;
                }

                return false;
            });

        });

        return filterData;
    }
    
        @FXML 
private void searchFilterRdv() {
    ObservableList<Produit> prods = AffichageListeProduitsBackfx.getItems();
    FilteredList<Produit> filterData = new FilteredList<>(prods, e -> true);
    searchTextField.setOnKeyReleased(e -> {
        String newValue = searchTextField.getText().toLowerCase();
        filterData.setPredicate((Predicate<? super Produit>) prd -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String toLowerCaseFilter = newValue.toLowerCase();
            return prd.getNom().toLowerCase().contains(toLowerCaseFilter) ||
                   Integer.toString(prd.getId()).contains(newValue) ||
                   Integer.toString(prd.getPrix()).contains(newValue);
        });
    });
    SortedList<Produit> prd = new SortedList<>(filterData);
    AffichageListeProduitsBackfx.setItems(prd);
}



}