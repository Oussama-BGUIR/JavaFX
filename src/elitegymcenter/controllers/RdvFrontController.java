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
public class RdvFrontController implements Initializable {

     @FXML
    private TextField nomRdvFrontfx;
    @FXML
    private TextField prenomRdvFrontfx;
    @FXML
    private TextField emailRdvFrontfx;
    @FXML
    private DatePicker dateRdvFrontfx;
        @FXML
    private TextField numtelRdvFrontfx;
    @FXML
    private TextField descriptionRdvFrontfx;
    
     @FXML
    private ComboBox<Fiche> nutrtitRdvfx;
    
    
    
    
    
    
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
        for (int i = 1; i < nomRdvFrontfx.getText().trim().length(); i++) {
            char ch = nomRdvFrontfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && nomRdvFrontfx.getText().trim().length() >=3) {
            labelnom.setText ("Nom valide ");
            labelnom.setTextFill(Color.GREEN);
            checknom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelnom.setText ("Verifier le nom ");
            labelnom.setTextFill(Color.RED);
            checknom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
       @FXML
    private void verifierprenom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < prenomRdvFrontfx.getText().trim().length(); i++) {
            char ch = prenomRdvFrontfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && prenomRdvFrontfx.getText().trim().length() >=3) {
            labelprenom.setText ("Prenom valide ");
            labelprenom.setTextFill(Color.GREEN);
            checkprenom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelprenom.setText ("Verifier le prenom ");
            labelprenom.setTextFill(Color.RED);
            checkprenom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
      @FXML
        private void verifierDesc(KeyEvent event) {
            int nbNonChar = 0;
            String description = descriptionRdvFrontfx.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < description.length(); i++) {
                char ch = description.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
            if (nbNonChar == 0 && description.length() >= 5) {
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
    String email = emailRdvFrontfx.getText().trim();
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
    String tel = numtelRdvFrontfx.getText().trim();
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

ServiceFicheCRUD fi =new ServiceFicheCRUD();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      nutrtitRdvfx.getItems().addAll(fi.afficherFiche());
        
    }    

  @FXML
private void AjoutRdv(ActionEvent event) {
     
 
   LocalDate localDate = dateRdvFrontfx.getValue();
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
    if (nomRdvFrontfx.getText().isEmpty()) {
        showAlert("La case nom est vide!");
    } else if (prenomRdvFrontfx.getText().isEmpty()) {
        showAlert("La case description est vide!");
    } else if (emailRdvFrontfx.getText().isEmpty()) {
        showAlert("La case email est vide!");
    } else if (numtelRdvFrontfx.getText().isEmpty()) {
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
     else if (descriptionRdvFrontfx.getText().isEmpty()) {
        showAlert("La case description est vide!");
    } else {
        String nom = nomRdvFrontfx.getText();
        String prenom = prenomRdvFrontfx.getText();
        String email = emailRdvFrontfx.getText();
       LocalDate dateSaisie = dateRdvFrontfx.getValue();
           date = java.sql.Date.valueOf(dateRdvFrontfx.getValue());
       
        int numtel = Integer.valueOf(numtelRdvFrontfx.getText());
        String description = descriptionRdvFrontfx.getText();

        Rdv R = new Rdv(numtel, (java.sql.Date) date, prenom, nom, email, description,nutrtitRdvfx.getValue().getId());
        ServiceRdvCRUD ServiceRdv = new ServiceRdvCRUD();
        ServiceRdv.ajouterRdv(R);

        
          // Récupérer la valeur de l'e-mail du champ de saisie
              String emailDestinataire = emailRdvFrontfx.getText();

              // Créer le message à envoyer
             String message = "Bienvenue " + prenom + " " +nom+  " à Elite Gym " 
                           
                        + "Votre rendez-vous avec le nutritionniste "
                       
                        + "ça sera le " + date  ;
                    //    + "Si vous voulez le contacter voici le numero  : " + numtel  + "\n"
                    //    + "l'email  : " + email + "\n"
                      

// Envoyer l'e-mail au destinataire
Emailsender.sendEmail_add(emailDestinataire, message);

        
         

Notifications notificationBuilder = Notifications.create()
    .title("Rendez-vous ajouté avec succès")
    .text("Votre rendez-vous du " + date + " a été sauvegardé avec succès")
    .hideAfter(Duration.seconds(7))
    .position(Pos.CENTER)
    .graphic(null)
    .darkStyle();
notificationBuilder.showInformation();

                
           try {
               
            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Home/Home.fxml"));
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
        public void switchToHome(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../gui/Home/Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListFiche");
        stage.setScene(scene);
        stage.show();

}
}
    
