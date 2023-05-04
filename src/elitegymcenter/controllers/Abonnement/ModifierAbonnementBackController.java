/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Abonnement;


import elitegymcenter.entities.Abonnement;
import elitegymcenter.interfaces.AbonnementCRUD;
import elitegymcenter.services.Abonnement1CRUD;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;



/**
 * FXML Controller class
 *
 * @author shini
 */
public class ModifierAbonnementBackController implements Initializable {

    @FXML
    private TextField nomAbonnementModifierfx;
    @FXML
    private TextField prenomAbonnementModifierfx;
    @FXML
    private TextField numeroAbonnementModifierfx;
    @FXML
    private TextField emailAbonnementModifierfx;
    @FXML
    private ChoiceBox<String> typeAbonnementModifierfx;
     @FXML
    private final String[] typeAbonnementModifierfxVariable = {"Manuelle", "trimestrielle", "Anuelle"};
      @FXML
    private Label labeltelModifier;
    private boolean verificationNumeroModifier;
    static int id, numero;
    static String nom, prenom,email,type;
    
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeAbonnementModifierfx.getItems().addAll(typeAbonnementModifierfxVariable);
        nomAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getNom()));
        prenomAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getPrenom()));
        numeroAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getNumero()));
        emailAbonnementModifierfx.setText(String.valueOf(AfficherAbonnementBackController.E.getEmail()));
        
       
    }   
    
  
  

    @FXML
    private void modiferAbonnementBack(ActionEvent event) 
    {
         
        AbonnementCRUD inter = new Abonnement1CRUD();
        
        String nom = nomAbonnementModifierfx.getText();
        String prenom = prenomAbonnementModifierfx.getText();
        int numero = Integer.valueOf(numeroAbonnementModifierfx.getText());
        String email = emailAbonnementModifierfx.getText();
        String type = typeAbonnementModifierfx.getValue();
        
        if(nom.isEmpty()){
            showAlert("La case nom est vide!");
        }else if(prenom.isEmpty()){
            showAlert("La case prenom est vide!");
        }else if(email.isEmpty()){
            showAlert("La case email est vide!");
        } else if(numeroAbonnementModifierfx.getText().isEmpty()){
            showAlert("La case numero est vide!");
        }else if (verifStringModifier(nomAbonnementModifierfx))
        {
             showAlert("le nom doit contenir que des lettres!");
        }
        else if (verifStringModifier(prenomAbonnementModifierfx))
        {
             showAlert("le prenom doit contenir que des lettres!");
        }else if (verificationNumeroModifier)
        {
             showAlert("Le numero est invalide");
        }else if (verifEmailModifier(emailAbonnementModifierfx))
        {
             showAlert("l'email est invalide!");
        } else {
            
            showAlert("Etes vous sur de modifier un abonnement?");
            System.out.println(AfficherAbonnementBackController.E.getId());
            Abonnement even = new Abonnement(AfficherAbonnementBackController.E.getId(), numero, nom, prenom, email, type );
      
            inter.modifierAbonnement(even);
            Notifications notificationBuilder = Notifications.create()
                .title("Abonnement modifié avec succès")
                .text("Votre Abonnement de nom " + nom + " a été modifié avec succès")
                .hideAfter(Duration.seconds(7))
                .position(Pos.CENTER)
                .graphic(null)
                .darkStyle();
                notificationBuilder.showInformation();
        
            try {

                Parent page1= FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/Abonnement/AfficherAbonnementBack.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.out.println("Erreur\n");
                Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

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
      
      private boolean verifStringModifier(TextField chaine) {
 
        for (int i = 1; i < chaine.getText().trim().length(); i++) {
            char ch = chaine.getText().charAt(i);
            if (Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
        
    }
    
    
    
    private boolean verifEmailModifier(TextField chaine) {
        String email_pattern = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+" + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(chaine.getText().trim());

        if (((Matcher) matcher).matches()) {       //if   matcher ne contient pas la format
            return false;

           // verificationUserEmail = true;

        } else {
            return true;
            // JOptionPane.showMessageDialog(null, "Email Format invalide");
            //verificationUserEmail = false;

        }
    }
    @FXML
    private void veriftelModifier(KeyEvent event) {
    if (numeroAbonnementModifierfx.getText().trim().length() == 8) {
        int nbChar = 0;
        for (int i = 1; i < numeroAbonnementModifierfx.getText().trim().length(); i++) {
            char ch = numeroAbonnementModifierfx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labeltelModifier.setText("number valide");
            labeltelModifier.setTextFill(Color.GREEN);
             

            verificationNumeroModifier = false;
        } else {
            labeltelModifier.setText("invalide number \n"
                    + " Il exist des char");
            verificationNumeroModifier = true;

        }

    } else {
        labeltelModifier.setText("Il faut 8 chiffres");
       verificationNumeroModifier = true;
    }
}
    
    @FXML
    private void retourListeModifier(ActionEvent event) 
    {
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/AfficherAbonnementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
    
   
    
      
}
    
