/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import elitegymcenter.entities.Fiche;
import elitegymcenter.entities.Rdv;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import elitegymcenter.interfaces.FicheCRUD;
import elitegymcenter.services.ServiceFicheCRUD;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FicheController implements Initializable {

    @FXML
    static Fiche F = new Fiche();
      @FXML
    private TableView<Fiche> AffichageListeFichesBackfx;

    @FXML
    private TableColumn<Fiche, String> description;

    @FXML
    private TableColumn<Fiche, String> email;

    @FXML
    private TableColumn<Fiche, Integer> id;

    @FXML
    private TableColumn<Fiche, String> nom;

    @FXML
    private TableColumn<Fiche, Integer> numtel;

    @FXML
    private TableColumn<Fiche, String> prenom;
    
    @FXML 
    private ImageView code_qr;
    
     @FXML
    private Button trierid;
     
      @FXML
    private Button triernom;
       @FXML
    private Button trieremail;
       
        @FXML
    private TextField searchFiche;
    
           private Stage stage; 
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
   @Override
public void initialize(URL url, ResourceBundle rb) {
    // Initialisation des colonnes du TableView
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));

    // Récupération des données depuis le service
    FicheCRUD inter = new ServiceFicheCRUD();
    List<Fiche> list2 = inter.afficherFiche();

    // Ajout des fiches à la table view
    AffichageListeFichesBackfx.setItems(FXCollections.observableArrayList(list2));
    
    trierid.setOnAction(e -> trierParId());
    triernom.setOnAction(e -> trierParNom());
    trieremail.setOnAction(e -> trierParEmail());
    
    SearchFilterFiche();
}

        

    @FXML
    private void modifierFicheBack(ActionEvent event) {
      
        TableView<Fiche> list = AffichageListeFichesBackfx;
        FicheCRUD inter = new ServiceFicheCRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Fiche f = list.getSelectionModel().getSelectedItem();
 
       
        String nom = f.getNom();
        String prenom = f.getPrenom();
        String email = f.getEmail();
       
        int numtel = f.getNumtel();
         String description = f.getDescription();
        
        F=f;
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/ModifierFiche.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(FicheController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    
    }


    @FXML
    private void ajouterFicheBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/AjouterFiche.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterFicheController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

   
       @FXML
private void supprimerFicheBack(ActionEvent event) {
    TableView<Fiche> table = AffichageListeFichesBackfx;
    FicheCRUD inter = new ServiceFicheCRUD();

    int selectedIndex = table.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Fiche F = table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer la fiche");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette fiche ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // supprimer la fiche
            inter.supprimerFiche(F.getId());
            table.getItems().remove(selectedIndex);

            Notifications notificationBuilder = Notifications.create()
                    .title("Fiche à supprimer ")
                    .text("La fiche de l'identifiant " + F.getId() + " a été supprimée avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .graphic(null)
                    .darkStyle();
            notificationBuilder.showInformation();
        }

    } else {
        showAlert("sélectionner une fiche pour la supprimer ");
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
        public void switchToList(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../gui/Fiche.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListFiche");
        stage.setScene(scene);
        stage.show();
                
    }

        
        @FXML
        public void switchToAdmin(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../gui/AdminDashboardFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListFiche");
        stage.setScene(scene);
        stage.show();
        
        
        
}
        
        
        
        
        
        @FXML
private void trierParNom() {
    // Récupérer la liste des académies depuis le TableView
    ObservableList<Fiche> fiches = AffichageListeFichesBackfx.getItems();
    
    // Trier la liste par nom
    AffichageListeFichesBackfx.getSortOrder().clear(); // Effacer tout tri précédent
    nom.setSortType(TableColumn.SortType.ASCENDING); // Spécifier le type de tri (ascendant)
    AffichageListeFichesBackfx.getSortOrder().add(nom); // Ajouter la colonne de tri
    AffichageListeFichesBackfx.sort(); // Appliquer le tri
}

    @FXML
private void trierParEmail() {
    // Récupérer la liste des académies depuis le TableView
    ObservableList<Fiche> fiches = AffichageListeFichesBackfx.getItems();
    
    // Trier la liste par nom
    AffichageListeFichesBackfx.getSortOrder().clear(); // Effacer tout tri précédent
    email.setSortType(TableColumn.SortType.ASCENDING); // Spécifier le type de tri (ascendant)
    AffichageListeFichesBackfx.getSortOrder().add(email); // Ajouter la colonne de tri
    AffichageListeFichesBackfx.sort(); // Appliquer le tri
}

    @FXML
private void trierParId() {
    // Récupérer la liste des académies depuis le TableView
    ObservableList<Fiche> fiches = AffichageListeFichesBackfx.getItems();
    
    // Trier la liste par nom
    AffichageListeFichesBackfx.getSortOrder().clear(); // Effacer tout tri précédent
    id.setSortType(TableColumn.SortType.DESCENDING); // Spécifier le type de tri (ascendant)
    AffichageListeFichesBackfx.getSortOrder().add(id); // Ajouter la colonne de tri
    AffichageListeFichesBackfx.sort(); // Appliquer le tri
}

        @FXML
    private void QrCodeRdv(ActionEvent event) 
    {
        Fiche f = AffichageListeFichesBackfx.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "Nom : "+f.getNom()+"\n"+"Prenom : "+f.getPrenom()+"email : "+f.getEmail()+"numtel : "+f.getNumtel()+"description : "+f.getDescription();
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
            
            System.out.println("YEEES");
            
            code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
}
        
      @FXML 
private void SearchFilterFiche() {
    ObservableList<Fiche> fiches = AffichageListeFichesBackfx.getItems();
    FilteredList<Fiche> filterData = new FilteredList(fiches,e->true);
    searchFiche.setOnKeyReleased(e->{
        searchFiche.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate((Predicate<? super Fiche >) rd->{
                if(newValue==null){
                    return true;
                }
                String toLowerCaseFilter = newValue.toLowerCase();
                if(rd.getNom().contains(newValue)){
                    return true;
                } else if(rd.getPrenom().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if (rd.getEmail().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if (Integer.toString(rd.getNumtel()).contains(newValue)) {
                    return true;
                    } else if (Integer.toString(rd.getId()).contains(newValue)) {
                    return true;
                }
                 
                return false;
            });
        });
        final SortedList<Fiche> fich = new SortedList<>(filterData);
        fich.comparatorProperty().bind(AffichageListeFichesBackfx.comparatorProperty());
        AffichageListeFichesBackfx.setItems(fich);
    });
}

@FXML
    private void aller_rdv(ActionEvent event) 
    {
        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/Rdv/Rdv.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }
}
    

