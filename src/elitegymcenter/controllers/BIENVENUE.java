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
public class BIENVENUE implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }     
   @FXML
    private void EspaceAdmin(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/ADMIN_BIENVENUE.fxml"));
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
        private void AbonnementBTN(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Abonnement/AjoutAbonnementFront.fxml"));
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
    private void Rdv(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/RdvFront/RdvFront.fxml"));
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
    private void EspaceClient(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/FrontYoussef.fxml"));
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
    private void voirfront(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/front.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
    }
    
    
               @FXML
        private void ClientBTN(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/clientfxml.fxml"));
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
        private void logout(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/loginfxml.fxml"));
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
        private void consultation_cours(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Cours/AffichageCoursFront.fxml"));
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