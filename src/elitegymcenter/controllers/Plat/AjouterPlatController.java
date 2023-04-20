/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Plat;

import elitegymcenter.controllers.Menu.*;
import elitegymcenter.entities.Menu;
import elitegymcenter.entities.Plat;
import elitegymcenter.services.ServiceMenuCRUD;
import elitegymcenter.services.ServicePlatCRUD;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class AjouterPlatController implements Initializable {

    @FXML
    private TextField nomPlatfx;
    @FXML
    private TextField descriptionPlatfx;
    @FXML
    private TextField caloriePlatfx;
    @FXML
    private TextField imagePlatfx;
    @FXML
    private TextField prixPlatfx;
    
    @FXML
    private TextField menuidfx;
    
    
    
    @FXML
    private CheckBox disponibilitePlatfx;
    
    
    
    @FXML
    private Label labelnom;
    
    @FXML
    private Label labeldescription;
    
    @FXML
    private Label labelcalorie;
    
    @FXML
    private Label labelimage;

    @FXML
    private Label labelprix;

    @FXML
    private Label labelmenuid;

    
    
    @FXML
    private ImageView checknom;

    @FXML
    private ImageView checkdescription;

    @FXML
    private ImageView checkcalorie;

    @FXML
    private ImageView checkimage;
    @FXML
    private ImageView checkprix;
    @FXML
    private ImageView checkmenuid;

           private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < nomPlatfx.getText().trim().length(); i++) {
            char ch = nomPlatfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && nomPlatfx.getText().trim().length() >=3) {
            labelnom.setText ("nom valide :) ");
            labelnom.setTextFill(Color.GREEN);
            checknom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelnom.setText ("le nom doit etre plus que 3 lettres !");
            labelnom.setTextFill(Color.RED);
            checknom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
        @FXML
    private void verifierDesc(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < descriptionPlatfx.getText().trim().length(); i++) {
            char ch = descriptionPlatfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && descriptionPlatfx.getText().trim().length() >=10) {
            labeldescription.setText ("Description valide :) ");
            labeldescription.setTextFill(Color.GREEN);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labeldescription.setText ("écrivez plus que 10 lettres ! ");
            labeldescription.setTextFill(Color.RED);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
        @FXML
    private void verifierCal(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < caloriePlatfx.getText().trim().length(); i++) {
            char ch = caloriePlatfx.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && caloriePlatfx.getText().trim().length() >=3) {
            labelcalorie.setText ("Calorie valide :) ");
            labelcalorie.setTextFill(Color.GREEN);
            checkcalorie.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelcalorie.setText ("il faut un entier > 100 ! ");
            
            labelcalorie.setTextFill(Color.RED);
            checkcalorie.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
        @FXML
    private void verifierImg(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < imagePlatfx.getText().trim().length(); i++) {
            char ch = imagePlatfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && imagePlatfx.getText().trim().length() >=5) {
            labelimage.setText ("image valide :) ");
            labelimage.setTextFill(Color.GREEN);
            checkimage.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelimage.setText ("Check image !!! ");
            labelimage.setTextFill(Color.RED);
            checkimage.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    @FXML
    private void verifierPrix(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < prixPlatfx.getText().trim().length(); i++) {
            char ch = prixPlatfx.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && prixPlatfx.getText().trim().length() >=4) {
            labelprix.setText (" prix valide :) ");
            labelprix.setTextFill(Color.GREEN);
            checkprix.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelprix.setText ("il faut un entier > 1000 millimes !");
            labelprix.setTextFill(Color.RED);
            checkprix.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
         @FXML
    private void verifierMenuId(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < menuidfx.getText().trim().length(); i++) {
            char ch = menuidfx.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && menuidfx.getText().trim().length() <=3) {
            labelmenuid.setText ("Menu id :) ");
            labelmenuid.setTextFill(Color.GREEN);
            checkmenuid.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelmenuid.setText ("Check menu id !!! ");
            labelmenuid.setTextFill(Color.RED);
            checkmenuid.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


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
    private void AjoutPlat(ActionEvent event) {
        
        if(nomPlatfx.getText().isEmpty()){
            showAlert("La case nom est vide!");
        }else if(descriptionPlatfx.getText().isEmpty()){
            showAlert("La case description est vide!");
        }else if(caloriePlatfx.getText().isEmpty()){
            showAlert("La case calorie est vide!");
        } else if(imagePlatfx.getText().isEmpty()){
            showAlert("La case image est vide!");
        }else{
            String nom = nomPlatfx.getText();
        String description = descriptionPlatfx.getText();
        int calorie = Integer.valueOf(caloriePlatfx.getText());
        String image = imagePlatfx.getText();
        int menu_id = Integer.valueOf(menuidfx.getText());
        int prix = Integer.valueOf(prixPlatfx.getText());

        boolean disponibilte = disponibilitePlatfx.isSelected(); // Get the boolean value from CheckBox
       // boolean disponibilite = true ;

        Plat P = new Plat(menu_id,calorie,prix, disponibilte, nom, description, image);
        ServicePlatCRUD ServicePlat = new ServicePlatCRUD();
        ServicePlat.ajouterPlat(P);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Plat/Plat.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(PlatController.class.getName()).log(Level.SEVERE, null, ex);

        }
            
        }

                   
    }
    /*
       private boolean verifString(TextField chaine) {
 
        for (int i = 1; i < chaine.getText().trim().length(); i++) {
            char ch = chaine.getText().charAt(i);
            if (Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
        
    }
*/
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
        root = FXMLLoader.load(getClass().getResource("../../gui/Plat/Plat.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListPlat");
        stage.setScene(scene);
        stage.show();
                
    }
}
    
