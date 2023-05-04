package elitegymcenter.controllers;

import static elitegymcenter.controllers.RdvController.R;
import elitegymcenter.entities.Fiche;
import elitegymcenter.entities.Rdv;
import elitegymcenter.interfaces.RdvCRUD;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ModifierRdvController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField modifiernomRdvfx;

    @FXML
    public TextField modifierprenomRdvfx;

    @FXML
    public TextField modifieremailRdvfx;

    @FXML
    public DatePicker modifierdateRdvfx;

    @FXML
    public TextField modifiernumtelRdvfx;

    @FXML
    public TextField modifierdescriptionRdvfx;

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
    private ImageView checknumtel;

    @FXML
    private ImageView checkdate;

    @FXML
    private ImageView checkdescription;
    
     @FXML
    private ComboBox<Fiche> modifiernutritRdvfx;

  

    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 0; i < modifiernomRdvfx.getText().length(); i++) {
            char ch = modifiernomRdvfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
    if (nbNonChar == 0 && modifiernomRdvfx.getText().trim().length() >= 5) {
        labelnom.setText("Nom valide");
        labelnom.setTextFill(Color.GREEN);
        checknom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));
    } else {
        labelnom.setText("Vérifiez le prénom !");
        labelnom.setTextFill(Color.RED);
        checknom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));
    }
}

    
     @FXML
private void verifierprenom(KeyEvent event) {
    int nbNonChar = 0;
    for (int i = 0; i < modifierprenomRdvfx.getText().trim().length(); i++) {
        char ch = modifierprenomRdvfx.getText().charAt(i);
        if (!Character.isLetter(ch)) {
            nbNonChar++;
        }
    }
    if (nbNonChar == 0 && modifierprenomRdvfx.getText().trim().length() >= 5) {
        labelprenom.setText("Prenom valide");
        labelprenom.setTextFill(Color.GREEN);
        checkprenom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));
    } else {
        labelprenom.setText("Vérifiez le prénom !");
        labelprenom.setTextFill(Color.RED);
        checkprenom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));
    }
}

   @FXML
        private void verifierDesc(KeyEvent event) {
            int nbNonChar = 0;
            String description = modifierdescriptionRdvfx.getText().trim(); // Enlever les espaces en début et en fin
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
    String email = modifieremailRdvfx.getText().trim();
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
    String tel = modifiernumtelRdvfx.getText().trim();
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

//@FXML
//private void verifierDate(ActionEvent event) {
  //  LocalDate dateSaisie = modifierdateRdvfx.getValue();
   // LocalDate dateSysteme = LocalDate.now();
    
   // if (dateSaisie != null && !dateSaisie.isBefore(dateSysteme)) {
    //    labeldate.setText("Date valide :)");
      //  labeldate.setTextFill(Color.GREEN);
      //  checkdate.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));
    //} else {
     //   labeldate.setText("La date doit être supérieure ou égale à la date système.");
     //   labeldate.setTextFill(Color.RED);
      //  checkdate.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));
   // }
//} 
ServiceFicheCRUD fi =new ServiceFicheCRUD();

       public Rdv rdv;
    
  public void setRdv(Rdv rdv) {
    this.rdv = rdv;
}

    
  @Override
public void initialize(URL url, ResourceBundle rb) {
        
    if (rdv != null) {
      modifiernomRdvfx.setText(rdv.getNom());

        modifierprenomRdvfx.setText(rdv.getPrenom());
        modifieremailRdvfx.setText(rdv.getEmail());
        modifiernumtelRdvfx.setText(String.valueOf(rdv.getNumtel()));

        if (rdv.getDate() != null) {
            LocalDate localDate = rdv.getDate().toLocalDate();
            modifierdateRdvfx.setValue(localDate);
        }

        modifierdescriptionRdvfx.setText(rdv.getDescription());
       
     
    }
     modifiernutritRdvfx.getItems().addAll(fi.afficherFiche());
}


   @FXML
private void modifierRdv(ActionEvent event) {
    RdvCRUD inter = new ServiceRdvCRUD();

    LocalDate localDate = modifierdateRdvfx.getValue();
    if (localDate == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    java.sql.Date date = java.sql.Date.valueOf(localDate);

    
    String nom = modifiernomRdvfx.getText();
    String prenom = modifierprenomRdvfx.getText();
    String email = modifieremailRdvfx.getText();
    int numtel = Integer.parseInt(modifiernumtelRdvfx.getText());
    String description = modifierdescriptionRdvfx.getText();
     
    Rdv rd = new Rdv(this.rdv.getId(), numtel, date, nom, prenom, email, description,modifiernutritRdvfx.getValue().getId());
    inter.modifierRdv(rd);

    stage = (Stage) modifiernomRdvfx.getScene().getWindow();
    stage.close();




           Notifications notificationBuilder = Notifications.create()
    .title("Rendez-vous modifié avec succès")
    .text("Le rendez-vous de l'id " +rdv.getId()+ " a été modifié et ça sera " + date  )
    .hideAfter(Duration.seconds(5))
    .position(Pos.CENTER)
    .graphic(null)
    .darkStyle();
notificationBuilder.showInformation();

    
    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("../gui/Rdv/Rdv.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
    } catch (IOException ex) {
        System.out.println("Erreur");
        Logger.getLogger(RdvController.class.getName()).log(Level.SEVERE, null, ex);
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
