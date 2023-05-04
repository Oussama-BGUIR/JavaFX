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
import elitegymcenter.services.ServiceRdvCRUD;
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
import javafx.stage.Stage;
import elitegymcenter.interfaces.RdvCRUD;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Date;
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
public class RdvController implements Initializable {

    @FXML
    static Rdv R = new Rdv();
    @FXML
    private TableView<Rdv> AffichageListeRdvsBackfx;

    @FXML
    private TableColumn<Rdv, Date> date;

    @FXML
    private TableColumn<Rdv, String> description;

    @FXML
    private TableColumn<Rdv, String> email;

    @FXML
    private TableColumn<Rdv, Integer> id;

    @FXML
    private TableColumn<Rdv, String> nom;

    @FXML
    private TableColumn<Rdv, Integer> numtel;

    @FXML
    private TableColumn<Rdv, String> prenom;
  
    @FXML
    private TableColumn<Rdv, Integer> nom_nutritioniste_id;
    
    @FXML 
    private TextField searchRdv;
    
     @FXML
    private Button trierid;
     
      @FXML
    private Button triernom;
       @FXML
    private Button trieremail;
           
    
    @FXML 
    private ImageView code_qr;
     
    private Stage stage; 
    private Scene scene;
    private Parent root;
    /**
     * Initializes the controller class.
     */
    
  
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        RdvCRUD inter = new ServiceRdvCRUD();
        List<Rdv> list = inter.afficherRdv();
        ObservableList<Rdv> observableList = FXCollections.observableArrayList(list);
        AffichageListeRdvsBackfx.setItems(observableList);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        numtel.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
       nom_nutritioniste_id.setCellValueFactory(new PropertyValueFactory<>("nom_nutritioniste_id"));
   
   SearchFilterRdv();
   
   trierid.setOnAction(e -> trierParId());
    triernom.setOnAction(e -> trierParNom());
    trieremail.setOnAction(e -> trierParEmail());

    
}

  
  @FXML
private void modifierRdvBack(ActionEvent event) throws IOException {
    TableView<Rdv> table = AffichageListeRdvsBackfx;
    RdvCRUD inter = new ServiceRdvCRUD();
    int selectedIndex = table.getSelectionModel().getSelectedIndex();
    
    if (selectedIndex >= 0) {
    Rdv selectedRdv = table.getSelectionModel().getSelectedItem();
    try {
        // Charger la fenêtre de modification une seule fois
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Rdv/ModifierRdv.fxml"));
        Parent root = loader.load();
        ModifierRdvController controller = loader.getController();
        controller.setRdv(selectedRdv); // initialise le contrôleur avec les valeurs du rendez-vous
        
        // Initialise la variable rdv avec la valeur sélectionnée
        controller.rdv = selectedRdv;
controller.modifiernomRdvfx.setText(selectedRdv.getNom());
controller.modifierprenomRdvfx.setText(selectedRdv.getPrenom());
controller.modifieremailRdvfx.setText(selectedRdv.getEmail());
controller.modifiernumtelRdvfx.setText(String.valueOf(selectedRdv.getNumtel()));
if (selectedRdv.getDate() != null) {
    LocalDate localDate = selectedRdv.getDate().toLocalDate();
    controller.modifierdateRdvfx.setValue(localDate);
}
controller.modifierdescriptionRdvfx.setText(selectedRdv.getDescription());

// Dans la méthode modifierRdvBack du contrôleur de la fenêtre de modification





Scene scene = new Scene(root);
Stage stage = new Stage();
stage.setScene(scene);
stage.showAndWait();


            // Rafraîchir le TableView pour afficher les modifications
            table.refresh();

           

        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        showAlert("Sélectionner un rendez-vous à modifier");
    }
}



    @FXML
    private void ajouterRdvBack(ActionEvent event) {
        
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Rdv/AjouterRdv.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AjouterRdvController.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        
    }

  @FXML
private void supprimerRdvBack(ActionEvent event) {
    TableView<Rdv> table = AffichageListeRdvsBackfx;
    RdvCRUD inter = new ServiceRdvCRUD();

    int selectedIndex = table.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Rdv rdv = table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer le rendez-vous");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce rendez-vous ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            inter.supprimerRdv(rdv.getId());
            table.getItems().remove(selectedIndex);

            Notifications notificationBuilder = Notifications.create()
                    .title("Rendez-vous supprimé")
                    .text("Le rendez-vous de l'id " + rdv.getId() + " a été supprimé avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .graphic(null)
                    .darkStyle();
            notificationBuilder.showInformation();
        }
    } else {
        showAlert("Sélectionner un rendez-vous pour le supprimer ");
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
        root = FXMLLoader.load(getClass().getResource("../gui/Rdv/Rdv.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListRdv");
        stage.setScene(scene);
        stage.show();
                
    }
        
        
        
  
        
          @FXML
        public void switchToAdmin(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../gui/AdminDashboardFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();

}
        
            @FXML
private void trierParNom() {
   
    ObservableList<Rdv> rdvs = AffichageListeRdvsBackfx.getItems();
    
    AffichageListeRdvsBackfx.getSortOrder().clear(); // Effacer tout tri précédent
    nom.setSortType(TableColumn.SortType.ASCENDING); // Spécifier le type de tri (ascendant)
    AffichageListeRdvsBackfx.getSortOrder().add(nom); // Ajouter la colonne de tri
    AffichageListeRdvsBackfx.sort(); // Appliquer le tri
}

    @FXML
private void trierParEmail() {

    ObservableList<Rdv> rdvs = AffichageListeRdvsBackfx.getItems();
    
    
    AffichageListeRdvsBackfx.getSortOrder().clear();
    email.setSortType(TableColumn.SortType.ASCENDING); 
    AffichageListeRdvsBackfx.getSortOrder().add(email); 
    AffichageListeRdvsBackfx.sort(); 
}

    @FXML
private void trierParId() {

    ObservableList<Rdv> rdvs = AffichageListeRdvsBackfx.getItems();
    
    AffichageListeRdvsBackfx.getSortOrder().clear(); 
    id.setSortType(TableColumn.SortType.DESCENDING); 
    AffichageListeRdvsBackfx.getSortOrder().add(id); 
    AffichageListeRdvsBackfx.sort(); 
    
}

   

        
        
       @FXML 
private void SearchFilterRdv() {
    ObservableList<Rdv> rdvs = AffichageListeRdvsBackfx.getItems();
    FilteredList<Rdv> filterData = new FilteredList(rdvs,e->true);
    searchRdv.setOnKeyReleased(e->{
        searchRdv.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate((Predicate<? super Rdv >) rd->{
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
                    } else  if (String.valueOf(rd.getId()).contains(newValue)) {
                        return true;
                }
                 else if (rd.getDate() != null && rd.getDate().toString().contains(newValue)) {
                 return true;
    }
                return false;
            });
        });
        final SortedList<Rdv> rdvv = new SortedList<>(filterData);
        rdvv.comparatorProperty().bind(AffichageListeRdvsBackfx.comparatorProperty());
        AffichageListeRdvsBackfx.setItems(rdvv);
    });
}

@FXML
    private void QrCodeRdv(ActionEvent event) 
    {
        Rdv r = AffichageListeRdvsBackfx.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "Nom : "+r.getNom()+"\n"+"Prenom : "+r.getPrenom()+"email : "+r.getEmail()+"numtel : "+r.getNumtel()+" date: "+r.getDate()+"description : "+r.getDescription();
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
    private void aller_fiche(ActionEvent event) 
    {
        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/Fiche.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }
}

