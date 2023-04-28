/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Menu;

import elitegymcenter.entities.Menu;
import elitegymcenter.interfaces.MenuCRUD;
import elitegymcenter.services.ServiceMenuCRUD;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class ModifierMenuController implements Initializable {

    @FXML
    private TextField modifiernomMenu;
    @FXML
    private TextField modifierdescriptionMenu;
    @FXML
    private TextField modifiercalorieMenu;
    //@FXML
   // private TextField modifierimageMenu;
    @FXML
    private CheckBox modifierdisponibiliteMenu;

    
    @FXML
    private Label labelnom;
    
    @FXML
    private Label labeldescription;
    
    @FXML
    private Label labelcalorie;
    
   // @FXML
   // private Label labelimage;

    
    @FXML
    private ImageView checknom;

    @FXML
    private ImageView checkdescription;

    @FXML
    private ImageView checkcalorie;

   // @FXML
   // private ImageView checkimage;

           private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private AnchorPane pane;

    @FXML
    private Button selectImageBtn_modifier;

    @FXML
    private ImageView selectedImage_modifier;
    
        private File selectedImageFile;
            
        public void ImportBtn_modifier() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg","*jpeg"));

        selectedImageFile = openFile.showOpenDialog(pane.getScene().getWindow());

        if (selectedImageFile != null) {
            selectedImage_modifier .setImage(new Image(selectedImageFile.toURI().toString(), 82, 84, false, true));

//            path = file.getAbsolutePath();
//            imagev = new Image(file.toURI().toString(), 134, 133, false, true);
//
//            image.setImage(imagev);
        }
    }
    
    @FXML
    private void verifiernom(KeyEvent event) {
            int nbNonChar = 0;
            String nom = modifiernomMenu.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < nom.length(); i++) {
                char ch = nom.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
        if (nbNonChar == 0 && modifiernomMenu.getText().trim().length() >=5) {
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
            String description = modifierdescriptionMenu.getText().trim(); // Enlever les espaces en début et en fin
            for (int i = 0; i < description.length(); i++) {
                char ch = description.charAt(i);
                if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) { // Vérifier les espaces aussi
                    nbNonChar++;
                }
            }
        if (nbNonChar == 0 && modifierdescriptionMenu.getText().trim().length() >=20) {
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
        for (int i = 1; i < modifiercalorieMenu.getText().trim().length(); i++) {
            char ch = modifiercalorieMenu.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && modifiercalorieMenu.getText().trim().length() >=4) {
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
    
      
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        modifiernomMenu.setText(String.valueOf(MenuController.M.getNom()));
        modifierdescriptionMenu.setText(String.valueOf(MenuController.M.getDescription()));
        modifiercalorieMenu.setText(String.valueOf(MenuController.M.getCalorie()));
        modifierdisponibiliteMenu.setText(String.valueOf(MenuController.M.getDisponibilite()));
       // modifierimageMenu.setText(String.valueOf(MenuController.M.getImage()));
    }    
    

    @FXML
    private void modifiermenu(ActionEvent event) 
    {
          
        MenuCRUD inter = new ServiceMenuCRUD();
        
        String nom = modifiernomMenu.getText();
        String description = modifierdescriptionMenu.getText();
        int calorie = Integer.valueOf(modifiercalorieMenu.getText());
        boolean disponibilite = modifierdisponibiliteMenu.isSelected();
       // String image = modifierimageMenu.getText();
        
        
        System.out.println(MenuController.M.getId());
        
        String imagePath = selectedImageFile.toString();
        Menu men = new Menu(MenuController.M.getId(), calorie, disponibilite, nom, description, imagePath );
      
        inter.modifierMenu(men);
        
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
