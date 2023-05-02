/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Plat;

import elitegymcenter.controllers.Menu.*;
import elitegymcenter.entities.Menu;
import elitegymcenter.entities.Plat;
import elitegymcenter.interfaces.MenuCRUD;
import elitegymcenter.interfaces.PlatCRUD;
import elitegymcenter.services.ServiceMenuCRUD;
import elitegymcenter.services.ServicePlatCRUD;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class ModifierPlatController implements Initializable {

    @FXML
    private TextField nomPlatfx_modifier;
    @FXML
    private TextField descriptionPlatfx_modifier;
    @FXML
    private TextField caloriePlatfx_modifier;
   // @FXML
   // private TextField imagePlatfx_modifier;
    @FXML
    private TextField prixPlatfx_modifier;
    

    
    @FXML
    private CheckBox modifierdisponibilitePlat;
    
        @FXML
    private ComboBox<Menu> menuidfx_modifier;
        
    
    @FXML
    private Label labelnom;
    
    @FXML
    private Label labeldescription;
    
    @FXML
    private Label labelcalorie;
    
   // @FXML
    //private Label labelimage;
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

    //@FXML
   // private ImageView checkimage;

        @FXML
    private ImageView checkprix;
    @FXML
    private ImageView checkmenuid;

    
            private Stage stage; 
    private Scene scene;
    private Parent root;
     ServiceMenuCRUD acs=new ServiceMenuCRUD();
     
         @FXML
    private AnchorPane panePlat;

    @FXML
    private Button selectImageBtnPlat_modifier;

    @FXML
    private ImageView selectedImagePlat_modifier;
    
        private File selectedImageFile;
        public void ImportBtnPlat_modifier() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg","*jpeg"));

        selectedImageFile = openFile.showOpenDialog(panePlat.getScene().getWindow());

        if (selectedImageFile != null) {
            selectedImagePlat_modifier .setImage(new Image(selectedImageFile.toURI().toString(), 82, 84, false, true));

//            path = file.getAbsolutePath();
//            imagev = new Image(file.toURI().toString(), 134, 133, false, true);
//
//            image.setImage(imagev);
        }
    }
    
    @FXML
    private void verifiernom(KeyEvent event) {
             int nbNonChar = 0;
            String nom = nomPlatfx_modifier.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < nom.length(); i++) {
                char ch = nom.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
        if (nbNonChar == 0 && nomPlatfx_modifier.getText().trim().length() >=3) {
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
            String description = descriptionPlatfx_modifier.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < description.length(); i++) {
                char ch = description.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
        if (nbNonChar == 0 && descriptionPlatfx_modifier.getText().trim().length() >=10) {
            labeldescription.setText ("Description valide :) ");
            labeldescription.setTextFill(Color.GREEN);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labeldescription.setText ("écrivez plus que 10 lettres !");
            labeldescription.setTextFill(Color.RED);
            checkdescription.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    
        @FXML
    private void verifierCal(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < caloriePlatfx_modifier.getText().trim().length(); i++) {
            char ch = caloriePlatfx_modifier.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && caloriePlatfx_modifier.getText().trim().length() >=3) {
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
    private void verifierPrix(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < prixPlatfx_modifier.getText().trim().length(); i++) {
            char ch = prixPlatfx_modifier.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && prixPlatfx_modifier.getText().trim().length() >=4) {
            labelprix.setText ("prix valide :) ");
            labelprix.setTextFill(Color.GREEN);
            checkprix.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelprix.setText ("il faut un entier > 1000 millimes !");
            labelprix.setTextFill(Color.RED);
            checkprix.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
        
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
        menuidfx_modifier.getItems().addAll(acs.afficherMenu());
        nomPlatfx_modifier.setText(String.valueOf(PlatController.P.getNom()));
        descriptionPlatfx_modifier.setText(String.valueOf(PlatController.P.getDescription()));
        caloriePlatfx_modifier.setText(String.valueOf(PlatController.P.getCalorie()));
        modifierdisponibilitePlat.setText(String.valueOf(PlatController.P.getDisponibilte()));
       // imagePlatfx_modifier.setText(String.valueOf(PlatController.P.getImage()));
       // menuidfx_modifier.setText(String.valueOf(PlatController.P.getMenu_id()));
        prixPlatfx_modifier.setText(String.valueOf(PlatController.P.getPrix()));

}    
    

    @FXML
    private void modifierplat(ActionEvent event) 
    {
    
        PlatCRUD inter = new ServicePlatCRUD();
        
        String nom = nomPlatfx_modifier.getText();
        String description = descriptionPlatfx_modifier.getText();
        int calorie = Integer.valueOf(caloriePlatfx_modifier.getText());
        //String image = imagePlatfx_modifier.getText();
        //int menu_id = Integer.valueOf(menuidfx_modifier.getText());
        int prix = Integer.valueOf(prixPlatfx_modifier.getText());
        boolean disponibilte = modifierdisponibilitePlat.isSelected();
        //boolean disponibilte = true ;

        
        System.out.println(PlatController.P.getId());
        String imagePath = selectedImageFile.toString();

        Plat pl = new Plat(PlatController.P.getId(),menuidfx_modifier.getValue().getId(), calorie, prix, disponibilte , nom, description, imagePath );
      
        inter.modifierPlat(pl);
        
                     Notifications notificationBuilder = Notifications.create()
                 .title("succès de modification ")
                 .text("le plat " + nom + " a été modifié avec succès !!")
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.CENTER)
                 .graphic(null)
                 .darkStyle();
             notificationBuilder.showInformation();
        
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
