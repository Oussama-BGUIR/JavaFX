/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;


import elitegymcenter.entities.Fiche;
import elitegymcenter.entities.Rdv;
import elitegymcenter.services.Emailsender;
import elitegymcenter.services.ServiceFicheCRUD;
import elitegymcenter.services.ServiceRdvCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class AjouterRdvController implements Initializable {

     @FXML
    private TextField nomRdvfx;
    @FXML
    private TextField prenomRdvfx;
    @FXML
    private TextField emailRdvfx;
    @FXML
    private DatePicker dateRdvfx;
        @FXML
    private TextField numtelRdvfx;
    @FXML
    private TextField descriptionRdvfx;
    
    @FXML
    private ComboBox<Fiche> nutrtitRdvfx;;
    
    
    
    
    @FXML
    private Label labelnom;
    
       @FXML
    private Label labelprenom;
       
         @FXML
    private Label labelemail;
          @FXML
    private Label labeldate;
         
             @FXML
    private Label labelnumtel;
    
    @FXML
    private Label labeldescription;
    
    ServiceFicheCRUD fi =new ServiceFicheCRUD();


    
    @FXML
    private ImageView checknom;
        @FXML
    private ImageView checkprenom;

        @FXML
    private ImageView checkemail;
        
         @FXML
    private ImageView checkdate;

    @FXML
    private ImageView checknumtel;
    
    @FXML
    private ImageView checkdescription;



           private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < nomRdvfx.getText().trim().length(); i++) {
            char ch = nomRdvfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && nomRdvfx.getText().trim().length() >=5) {
            labelnom.setText ("nom valide ");
            labelnom.setTextFill(Color.GREEN);
            checknom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelnom.setText ("Check nom !!! ");
            labelnom.setTextFill(Color.RED);
            checknom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
       @FXML
    private void verifierprenom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < prenomRdvfx.getText().trim().length(); i++) {
            char ch = prenomRdvfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && prenomRdvfx.getText().trim().length() >=5) {
            labelprenom.setText ("prenom valide ");
            labelprenom.setTextFill(Color.GREEN);
            checkprenom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelprenom.setText ("Check nom !!! ");
            labelprenom.setTextFill(Color.RED);
            checkprenom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
      @FXML
        private void verifierDesc(KeyEvent event) {
            int nbNonChar = 0;
            String description = descriptionRdvfx.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < description.length(); i++) {
                char ch = description.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
            if (nbNonChar == 0 && description.length() >= 7) {
                labeldescription.setText("Description valide ");
                labeldescription.setTextFill(Color.GREEN);
                checkdescription.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));

            } else {
                labeldescription.setText("Ecrivez plus que 7 lettres !");
                labeldescription.setTextFill(Color.RED);
                checkdescription.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));
            }
        }
    
      @FXML
private void verifierEmail(KeyEvent event) {
    String email = emailRdvfx.getText().trim();
    if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && email.length() <= 50) {
        labelemail.setText("Adresse e-mail valide :)");
        labelemail.setTextFill(Color.GREEN);
        checkemail.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));
    } else {
        labelemail.setText("Adresse e-mail invalide !");
        labelemail.setTextFill(Color.RED);
        checkemail.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));
    }
}

    
    @FXML
private void verifiernumtel(KeyEvent event) {
    String tel = numtelRdvfx.getText().trim();
    if (tel.matches("^[0-9]{8}$") && Integer.parseInt(tel) > 0) {
        labelnumtel.setText("Numéro de téléphone valide :)");
        labelnumtel.setTextFill(Color.GREEN);
        checknumtel.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));
    } else {
        labelnumtel.setText("Numéro de téléphone invalide !");
        labelnumtel.setTextFill(Color.RED);
        checknumtel.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));
    }
}


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nutrtitRdvfx.getItems().addAll(fi.afficherFiche());
        
    }    

  @FXML
private void AjoutRdv(ActionEvent event) {
     
 
   LocalDate localDate = dateRdvfx.getValue();
    if (localDate == null) {
       Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
       alert.setContentText("Veuillez remplir tous les champs.");
       alert.showAndWait();
       return;
    }
    Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
   Date date = Date.from(instant);
    if (nomRdvfx.getText().isEmpty()) {
        showAlert("La case nom est vide!");
    } else if (prenomRdvfx.getText().isEmpty()) {
        showAlert("La case description est vide!");
    } else if (emailRdvfx.getText().isEmpty()) {
        showAlert("La case email est vide!");
    } else if (numtelRdvfx.getText().isEmpty()) {
        showAlert("La case numero telephone est vide!");
    }
          else if (localDate.isBefore(LocalDate.now())) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Date incorrecte");
        alert.setHeaderText(null);
        alert.setContentText("La date doit être supérieure ou égale à la date actuelle.");
        alert.showAndWait();
       return;
          }
     else if (descriptionRdvfx.getText().isEmpty()) {
        showAlert("La case description est vide!");
    } else {
        String nom = nomRdvfx.getText();
        String prenom = prenomRdvfx.getText();
        String email = emailRdvfx.getText();
       LocalDate dateSaisie = dateRdvfx.getValue();
           date = java.sql.Date.valueOf(dateRdvfx.getValue());
       
        int numtel = Integer.valueOf(numtelRdvfx.getText());
        String description = descriptionRdvfx.getText();
     //   nutrtitRdvfx.getValue();

        Rdv R = new Rdv(numtel, (java.sql.Date) date, prenom, nom, email, description,nutrtitRdvfx.getValue().getId());
        ServiceRdvCRUD ServiceRdv = new ServiceRdvCRUD();
        ServiceRdv.ajouterRdv(R);

        
        
Notifications notificationBuilder = Notifications.create()
    .title("Rendez-vous ajouté avec succès")
    .text("Votre rendez-vous du " + date + " a été sauvegardé avec succès")
    .hideAfter(Duration.seconds(5))
    .position(Pos.CENTER)
    .graphic(null)
    .darkStyle();
notificationBuilder.showInformation();

                
           try {
               
            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Rdv/Rdv.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);

        }
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
}
    
