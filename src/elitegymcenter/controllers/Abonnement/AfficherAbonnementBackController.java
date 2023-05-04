/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Abonnement;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import elitegymcenter.controllers.Offre.AfficherOffreController;
import elitegymcenter.entities.Abonnement;
import elitegymcenter.interfaces.AbonnementCRUD;
import elitegymcenter.services.Abonnement1CRUD;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author shini
 */
public class AfficherAbonnementBackController implements Initializable {

   /* @FXML
    private ListView<Abonnement> affichageAbonnementFrontfx;*/
     @FXML
    private TableView<Abonnement> affichageAbonnementFrontfx;

    @FXML
    private TableColumn<Abonnement, Integer> idfx;

    @FXML
    private TableColumn<Abonnement, String> nomfx;

    @FXML
    private TableColumn<Abonnement, String> prenomfx;

    @FXML
    private TableColumn<Abonnement, Integer> numerofx;

    @FXML
    private TableColumn<Abonnement, String> emailfx;

    @FXML
    private TableColumn<Abonnement, String> typefx;
    
    @FXML
    private TextField txtfieldrecherche;

    //@FXML
    //private Pagination pagination ;
   // private final static int rowsPerPage = 10;
   // private final static int dataSize = 100;
    
    @FXML
    private ImageView code_qr;
   
    static Abonnement E = new Abonnement();
    static int id, numero;

    static String nom,prenom,email,type;
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
            AbonnementCRUD inter = new Abonnement1CRUD();
            List<Abonnement> list = inter.afficherAbonnement();
            ObservableList<Abonnement> observableList = FXCollections.observableArrayList(list);
            affichageAbonnementFrontfx.setItems(observableList);
            idfx.setCellValueFactory(new PropertyValueFactory<>("Id"));
            nomfx.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            prenomfx.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
            numerofx.setCellValueFactory(new PropertyValueFactory<>("Numero"));
            emailfx.setCellValueFactory(new PropertyValueFactory<>("Email"));
            typefx.setCellValueFactory(new PropertyValueFactory<>("Type"));
            
            SearchFilter();
           
        


    }

    @FXML
    private void supprimerAbonnementBack(ActionEvent event) 
    {
        TableView<Abonnement> list = (TableView<Abonnement>) affichageAbonnementFrontfx;
        AbonnementCRUD inter = new Abonnement1CRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer l'abonnement");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet abonnement ?");

        Optional<ButtonType> result = alert.showAndWait();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Abonnement E = list.getSelectionModel().getSelectedItem();
            inter.supprimerAbonnement(E.getId());
            list.getItems().remove(selectedIndex);
       

            Notifications notificationBuilder = Notifications.create()
                    .title("Abonnement supprimé")
                    .text("L'abonnement avec le nom " + E.getNom() + " a été supprimé avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .graphic(null)
                    .darkStyle();
            notificationBuilder.showInformation();
            
        
        } else {
            showAlert("Veuillez sélectionner un abonnement à supprimer.");
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
    private void modifierAbonnementBack(ActionEvent event) 
    {
        TableView<Abonnement> list = affichageAbonnementFrontfx;
        AbonnementCRUD inter = new Abonnement1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Abonnement e = list.getSelectionModel().getSelectedItem();
 
       
       
        String nom = e.getNom();
        String prenom = e.getPrenom();
        String email = e.getEmail();
        int numero = e.getNumero();
        String type = e.getType();
       E=e;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/Abonnement/modifierAbonnementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
        
    

    @FXML
    private void ajouterAbonnementFront(ActionEvent event) {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/AjoutAbonnementFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

  
    
     @FXML
    private void Offreliste(ActionEvent event) 
    {
        TableView<Abonnement> list = affichageAbonnementFrontfx;
        AbonnementCRUD inter = new Abonnement1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
         Abonnement e = list.getSelectionModel().getSelectedItem();
        E=e;
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/Offre/AfficherOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherOffreController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }

    @FXML
    private void QrCodeAbonnement(ActionEvent event) 
    {
        Abonnement p = affichageAbonnementFrontfx.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "Nom : "+p.getNom()+"\n"+"Prenom : "+p.getPrenom()+"email : "+p.getEmail()+"numero : "+p.getNumero()+"type : "+p.getType();
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
            
            System.out.println("Success...");
            
            code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
        
    }
    

    
     @FXML
    private void triAbonnementBack(ActionEvent event)
    {
        ObservableList<Abonnement> listeAbonnement = affichageAbonnementFrontfx.getItems();
        Collections.sort(listeAbonnement, new Comparator<Abonnement>() {
            @Override
            public int compare(Abonnement e1, Abonnement e2) {
                return e1.getNom().compareToIgnoreCase(e2.getNom());
            }
        });
        affichageAbonnementFrontfx.setItems(listeAbonnement);
    }
    
     @FXML
    private void triAbonnementBackPrenom(ActionEvent event)
    {
        ObservableList<Abonnement> listeAbonnement = affichageAbonnementFrontfx.getItems();
        Collections.sort(listeAbonnement, new Comparator<Abonnement>() {
            @Override
            public int compare(Abonnement e1, Abonnement e2) {
                return e1.getPrenom().compareToIgnoreCase(e2.getPrenom());
            }
        });
        affichageAbonnementFrontfx.setItems(listeAbonnement);
    }
    
    @FXML 
    private void SearchFilter() {
        ObservableList<Abonnement> rdvs = affichageAbonnementFrontfx.getItems();
        FilteredList<Abonnement> filterData = new FilteredList(rdvs,e->true);
        txtfieldrecherche.setOnKeyReleased(e->{
        txtfieldrecherche.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate((Predicate<? super Abonnement >) rd->{
                if(newValue==null){
                    return true;
                }
                String toLowerCaseFilter = newValue.toLowerCase();
                if(rd.getNom().contains(newValue)){
                    return true;
                } /*else if(rd.getPrenom().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if (rd.getEmail().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                }*/
                return false;
            });
        });
        final SortedList<Abonnement> rdvv = new SortedList<>(filterData);
        rdvv.comparatorProperty().bind(affichageAbonnementFrontfx.comparatorProperty());
        affichageAbonnementFrontfx.setItems(rdvv);
        });
    }
    
    @FXML
    private void retourAdminAbonnement(ActionEvent event) 
    {
        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/AdminDashboardFxml.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }
    
    
        
}
    




  
    

     
   
