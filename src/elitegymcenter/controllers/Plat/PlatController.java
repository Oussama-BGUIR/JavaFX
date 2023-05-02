/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Plat;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import elitegymcenter.entities.Plat;

import elitegymcenter.interfaces.PlatCRUD;
import elitegymcenter.services.ServicePlatCRUD;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class PlatController implements Initializable {

    @FXML
    private ListView<Plat> AffichageListePlatBackfx;
    static Plat P = new Plat();
    static int id,menu_id , prix, calorie;
    static boolean disponibilte;
    static String nom,description ,image;
        
    @FXML 
    private ImageView code_qr;
      @FXML 
    private TextField rechercheplats;
      
      
           private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        ListView<Plat> list1 = AffichageListePlatBackfx;
        PlatCRUD inter = new ServicePlatCRUD();
        List<Plat> list2 = inter.afficherPlat();
        for (int i = 0; i < list2.size(); i++) 
        {
            Plat P = list2.get(i);
            list1.getItems().add(P); // add Evenement to ListView
        } 
        
        recherchefiltrePlat();
        
   // trnom.setOnAction(e -> trierParNom());
        
    }     
        


            @FXML
      private void trierParNom() {
          ObservableList<Plat> plats = AffichageListePlatBackfx.getItems();

          // Sort the items in the ListView by the "nom" field in ascending order
          plats.sort(Comparator.comparing(Plat::getNom));

          // Refresh the ListView to reflect the sorted order
          AffichageListePlatBackfx.refresh();
      }


    @FXML
    private void modifierPlatBack(ActionEvent event) {
      
        ListView<Plat> list = AffichageListePlatBackfx;
        PlatCRUD inter = new ServicePlatCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Plat p = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = p.getNom();
        int prix = p.getPrix();
        int menu_id = p.getMenu_id();
        String description = p.getDescription();
        int calorie = p.getCalorie();
        boolean disponibilte = p.getDisponibilte();
        String image = p.getImage();
        P=p;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Plat/ModifierPlat.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(PlatController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterPlatBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Plat/AjouterPlat.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }
    
    /* 
    @FXML
    private void supprimerPlatBack(ActionEvent event) {
        
    
        ListView<Plat> list = (ListView<Plat>) AffichageListePlatBackfx;
        PlatCRUD inter = new ServicePlatCRUD();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Plat P = list.getSelectionModel().getSelectedItem();
            inter.supprimerPlat(P.getId());
            list.getItems().remove(selectedIndex);
       
        } else {
            showAlert("sélectionner un Plat pour le supprimer ");
        }
         
    }
    */

                @FXML
       private void supprimerPlatBack(ActionEvent event) {

           ListView<Plat> list = (ListView<Plat>) AffichageListePlatBackfx;
           PlatCRUD inter = new ServicePlatCRUD();

           int selectedIndex = list.getSelectionModel().getSelectedIndex();
           if (selectedIndex >= 0) {
               Plat P = list.getSelectionModel().getSelectedItem();
               inter.supprimerPlat(P.getId());
               ObservableList<Plat> newList = FXCollections.observableArrayList(list.getItems());
               newList.remove(selectedIndex);
               list.setItems(newList);
               
               // notification
                            Notifications notificationBuilder = Notifications.create()
                 .title("succès de suppression ")
                 .text("le plat a été supprimé avec succès !!")
                 .hideAfter(Duration.seconds(7))
                 .position(Pos.CENTER)
                 .graphic(null)
                 .darkStyle();
             notificationBuilder.showInformation();

           } else {
               showAlert("sélectionner un Plat pour le supprimer ");
           }
       }
    
       
       
       
    @FXML
    private void QrCodePlat(ActionEvent event) 
    {
        Plat p = AffichageListePlatBackfx.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "Nom : "+p.getNom()+"\n"+"description : "+p.getDescription()+"\n"+"calorie : "+p.getCalorie()+"\n"+"prix : "+p.getPrix();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Yeeesss");
            
            code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
}

        @FXML 
        private void recherchefiltrePlat() {
            ObservableList<Plat> plats = AffichageListePlatBackfx.getItems();
            FilteredList<Plat> filterData = new FilteredList<>(plats, e -> true);

            rechercheplats.setOnKeyReleased(e -> {
                String newValue = rechercheplats.getText().toLowerCase();
                filterData.setPredicate((Predicate<? super Plat>) pl -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String toLowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(pl.getId()).contains(newValue)) {
                        return true; // rechercher par l'id
                    } else if (String.valueOf(pl.getCalorie()).contains(newValue)) {
                        return true; // rechercher par le prix
                    }else if (String.valueOf(pl.getPrix()).contains(newValue)) {
                        return true; // rechercher par le prix
                    } else if (pl.getNom().toLowerCase().contains(toLowerCaseFilter)) {
                        return true; // rechercher par le nom
                    }

                    return false;
                });
            });

            SortedList<Plat> platss = new SortedList<>(filterData);
            AffichageListePlatBackfx.setItems(platss);
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
        public void GoToMenu(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../../gui/Menu/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListMenu");
        stage.setScene(scene);
        stage.show();
                
    }
}
    

