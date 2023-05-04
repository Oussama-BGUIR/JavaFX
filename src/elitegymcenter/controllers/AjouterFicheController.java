/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;


import elitegymcenter.entities.Fiche;
import elitegymcenter.services.ServiceFicheCRUD;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
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
public class AjouterFicheController implements Initializable {

     @FXML
    private TextField nomFichefx;
    @FXML
    private TextField prenomFichefx;
    @FXML
    private TextField emailFichefx;
        @FXML
    private TextField numtelFichefx;
    @FXML
    private TextField descriptionFichefx;
    
    
    
    
    @FXML
    private Label labelnom;
    
       @FXML
    private Label labelprenom;
       
         @FXML
    private Label labelemail;
         
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
    private ImageView checknumtel;
    
    @FXML
    private ImageView checkdescription;



           private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < nomFichefx.getText().trim().length(); i++) {
            char ch = nomFichefx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && nomFichefx.getText().trim().length() >=3) {
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
        for (int i = 1; i < prenomFichefx.getText().trim().length(); i++) {
            char ch = prenomFichefx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && prenomFichefx.getText().trim().length() >=3) {
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
            String description = descriptionFichefx.getText().trim(); // Enlever les espaces en début et en fin
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
    String email = emailFichefx.getText().trim();
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
    String tel = numtelFichefx.getText().trim();
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
        
    }    

    @FXML
    private void AjoutFiche(ActionEvent event) {
        
        if(nomFichefx.getText().isEmpty()){
            showAlert("La case nom est vide!");
        }else if(prenomFichefx.getText().isEmpty()){
            showAlert("La case description est vide!");
        }else if(emailFichefx.getText().isEmpty()){
            showAlert("La case email est vide!");
        } else if(numtelFichefx.getText().isEmpty()){
            showAlert("La case numero telephone est vide!");
             } else if(descriptionFichefx.getText().isEmpty()){
            showAlert("La case description est vide!");
        }else{
            String nom = nomFichefx.getText();
            String prenom = prenomFichefx.getText();
             String email = emailFichefx.getText();
              int numtel = Integer.valueOf(numtelFichefx.getText());
        String description = descriptionFichefx.getText();
       
      

        Fiche F = new Fiche(numtel, prenom, nom,email, description);
        ServiceFicheCRUD ServiceFiche = new ServiceFicheCRUD();
        ServiceFiche.ajouterFiche(F);
        
        
Notifications notificationBuilder = Notifications.create()
    .title("Fiche ajoutées avec succées")
    .text("La fiche du "  +nom+  " du  a été sauvegardé avec succèes")
    .hideAfter(Duration.seconds(5))
    .position(Pos.CENTER)
    .graphic(null)
    .darkStyle();
notificationBuilder.showInformation();
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Fiche.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(FicheController.class.getName()).log(Level.SEVERE, null, ex);

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
        root = FXMLLoader.load(getClass().getResource("../gui/Fiche.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListFiche");
        stage.setScene(scene);
        stage.show();
                
    }
}
    
