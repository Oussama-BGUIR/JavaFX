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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
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
    private Label labeltel;
    private boolean verificationNumero;
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjoutFiche(ActionEvent event) {
        
        String nom = nomFichefx.getText();
        String prenom = prenomFichefx.getText();
         String email = emailFichefx.getText();
        int numtel = Integer.valueOf(numtelFichefx.getText());
        String description = descriptionFichefx.getText();
        
     
     if(nom.isEmpty()){
            showAlert("La case nom est vide!");
        }else if(prenom.isEmpty()){
            showAlert("La case prenom est vide!");
        }else if(email.isEmpty()){
            showAlert("La case email est vide!");
        } else if(numtelFichefx.getText().isEmpty()){
            showAlert("La case numero telephone est vide!");
        }else if (verifString(nomFichefx))
        {
             showAlert("le nom doit contenir que des lettres!");
        }
        else if (verifString(prenomFichefx))
        {
             showAlert("le prenom doit contenir que des lettres!");
        }else if (verificationNumero)
        {
             showAlert("Le numero est invalide");
        }else if (verifEmail(emailFichefx))
        {
             showAlert("l'email est invalide!");
        }
        
        
         else {
            
            showAlert("Etes vous sur d'ajouter une fiche?");
        

       Fiche F = new Fiche(numtel,nom, prenom, email,description);

        ServiceFicheCRUD ServiceFiche = new ServiceFicheCRUD();
       ServiceFiche.ajouterFiche(F);
        
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
    
  
    private boolean verifString(TextField chaine) {
 
        for (int i = 1; i < chaine.getText().trim().length(); i++) {
            char ch = chaine.getText().charAt(i);
            if (Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
        
    }
    
    
    
    private boolean verifEmail(TextField chaine) {
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
    private void veriftel(KeyEvent event) {
    if (numtelFichefx.getText().trim().length() == 8) {
        int nbChar = 0;
        for (int i = 1; i < numtelFichefx.getText().trim().length(); i++) {
            char ch = numtelFichefx.getText().charAt(i);

            if (Character.isLetter(ch)) {

                nbChar++;

            }
          
        }

        if (nbChar == 0) {
            labeltel.setText("number valide");
            //labeltel.setTextFill(Color.GREEN);

            verificationNumero = false;
        } else {
            labeltel.setText("invalide number \n"
                    + " Il exist des char");
            verificationNumero = true;

        }

    } else {
        labeltel.setText("Il faut 8 chiffres");
       verificationNumero = true;
    }

}
}
    
