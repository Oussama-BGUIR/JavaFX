/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Menu;

import elitegymcenter.entities.Menu;
import elitegymcenter.services.ServiceMenuCRUD;
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
public class AjouterMenuController implements Initializable {

    @FXML
    private TextField nomMenufx;
    @FXML
    private TextField descriptionMenufx;
    @FXML
    private TextField calorieMenufx;
    @FXML
    private TextField imageMenufx;
    @FXML
    private CheckBox disponibiliteMenufx;
    
    
    
    @FXML
    private Label labelnom;
    
    @FXML
    private Label labeldescription;
    
    @FXML
    private Label labelcalorie;
    
    @FXML
    private Label labelimage;

    
    @FXML
    private ImageView checknom;

    @FXML
    private ImageView checkdescription;

    @FXML
    private ImageView checkcalorie;

    @FXML
    private ImageView checkimage;

           private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < nomMenufx.getText().trim().length(); i++) {
            char ch = nomMenufx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && nomMenufx.getText().trim().length() >=5) {
            labelnom.setText ("nom valide :) ");
            labelnom.setTextFill(Color.GREEN);
            checknom.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelnom.setText ("le nom doit etre plus que 5 lettres !");
            labelnom.setTextFill(Color.RED);
            checknom.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
        @FXML
    private void verifierDesc(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < descriptionMenufx.getText().trim().length(); i++) {
            char ch = descriptionMenufx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && descriptionMenufx.getText().trim().length() >=20) {
            labeldescription.setText ("Description valide :) ");
            labeldescription.setTextFill(Color.GREEN);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labeldescription.setText ("écrivez plus que 20 lettres !");
            labeldescription.setTextFill(Color.RED);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
        @FXML
    private void verifierCal(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < calorieMenufx.getText().trim().length(); i++) {
            char ch = calorieMenufx.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && calorieMenufx.getText().trim().length() >=4) {
            labelcalorie.setText ("Calorie valide :) ");
            labelcalorie.setTextFill(Color.GREEN);
            checkcalorie.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelcalorie.setText ("il faut un entier > 1000 !");
            
            labelcalorie.setTextFill(Color.RED);
            checkcalorie.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
        @FXML
    private void verifierImg(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < imageMenufx.getText().trim().length(); i++) {
            char ch = imageMenufx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && imageMenufx.getText().trim().length() >=5) {
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void AjoutMenu(ActionEvent event) {
        
        if(nomMenufx.getText().isEmpty()){
            showAlert("La case nom est vide!");
        }else if(descriptionMenufx.getText().isEmpty()){
            showAlert("La case description est vide!");
        }else if(calorieMenufx.getText().isEmpty()){
            showAlert("La case calorie est vide!");
        } else if(imageMenufx.getText().isEmpty()){
            showAlert("La case image est vide!");
        }else{
            String nom = nomMenufx.getText();
        String description = descriptionMenufx.getText();
        int calorie = Integer.valueOf(calorieMenufx.getText());
        String image = imageMenufx.getText();
        boolean disponibilite = disponibiliteMenufx.isSelected(); // Get the boolean value from CheckBox
       // boolean disponibilite = true ;

        Menu M = new Menu(calorie, disponibilite, nom, description, image);
        ServiceMenuCRUD ServiceMenu = new ServiceMenuCRUD();
        ServiceMenu.ajouterMenu(M);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Menu/Menu.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);

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
        root = FXMLLoader.load(getClass().getResource("../../gui/Menu/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListMenu");
        stage.setScene(scene);
        stage.show();
                
    }
}
    
