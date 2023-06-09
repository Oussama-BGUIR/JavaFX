/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Menu;

import elitegymcenter.entities.Menu;
import elitegymcenter.interfaces.MenuCRUD;
import elitegymcenter.services.ServiceMenuCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class MenuController implements Initializable {

    @FXML
    private ListView<Menu> AffichageListeMenusBackfx;
    static Menu M = new Menu();
    static int id, calorie;
    static boolean disponibilite;
    static String nom,description ,image;

           private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Menu> list1 = AffichageListeMenusBackfx;
        MenuCRUD inter = new ServiceMenuCRUD();
        List<Menu> list2 = inter.afficherMenu();
        for (int i = 0; i < list2.size(); i++) 
        {
            Menu M = list2.get(i);
            list1.getItems().add(M); // add Evenement to ListView
        } 
        
    }     
        

    @FXML
    private void modifierMenuBack(ActionEvent event) {
      
        ListView<Menu> list = AffichageListeMenusBackfx;
        MenuCRUD inter = new ServiceMenuCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Menu m = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = m.getNom();
        String description = m.getDescription();
        int calorie = m.getCalorie();
        boolean disponibilite = m.getDisponibilite();
        String image = m.getImage();
        M=m;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Menu/ModifierMenu.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterMenuBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Menu/AjouterMenu.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

    @FXML
    private void supprimerMenuBack(ActionEvent event) {
        
    
        ListView<Menu> list = (ListView<Menu>) AffichageListeMenusBackfx;
        MenuCRUD inter = new ServiceMenuCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Menu M = list.getSelectionModel().getSelectedItem();
            inter.supprimerMenu(M.getId());
            list.getItems().remove(selectedIndex);
            
                             Notifications notificationBuilder = Notifications.create()
                 .title("succès de suppression")
                 .text("le menu a été supprimé avec succès !!")
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.CENTER)
                 .graphic(null)
                 .darkStyle();
             notificationBuilder.showInformation();
            
        } else {
            showAlert("sélectionner un menu pour le supprimer ");
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
        public void GoToPlat(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../../gui/Plat/Plat.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListOPlat");
        stage.setScene(scene);
        stage.show();
                
    }
}
    

