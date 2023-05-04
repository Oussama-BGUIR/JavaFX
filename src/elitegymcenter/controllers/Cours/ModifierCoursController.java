/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Cours;

import elitegymcenter.entities.Planning;
import elitegymcenter.entities.Cours;
import elitegymcenter.services.ServicePlanningCRUD;
import elitegymcenter.services.ServiceCoursCRUD;
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
import elitegymcenter.interfaces.PlanningCRUD;
import elitegymcenter.interfaces.CoursCRUD;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author ousam
 */
public class ModifierCoursController implements Initializable {
 @FXML
    private TextField nomCoursfx;
    @FXML
    private DatePicker dureeCoursfx;
    @FXML
    private TextField salleCoursfx;

    
    @FXML
    private TextField planningidfx;
    
    
    
    
    
    
    
    @FXML
    private Label labelnom;
    
    @FXML
    private Label labelduree;
   
    @FXML
    private Label labelsalle;



    @FXML
    private Label labelplanningid;

    
    
    @FXML
    private ImageView checknom;

    @FXML
    private ImageView checkduree;


    @FXML
    private ImageView checksalle;

    @FXML
    private ImageView checkplanningid;

    private Stage stage; 
    private Scene scene;
    private Parent root;
    
    
    @FXML
    private void verifiernom(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < nomCoursfx.getText().trim().length(); i++) {
            char ch = nomCoursfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && nomCoursfx.getText().trim().length() >=3) {
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
    /*
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
    */
    /*
        @FXML
    private void verifierDuree(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < dureeCoursfx.getText().trim().length(); i++) {
            char ch = dureeCoursfx.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && dureeCoursfx.getText().trim().length() >=3) {
            labelduree.setText ("duree valide :) ");
            labelduree.setTextFill(Color.GREEN);
            checkduree.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelduree.setText ("il faut un entier > 100 ! ");
            
            labelduree.setTextFill(Color.RED);
            checkduree.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
    */
        @FXML
    private void verifiersalle(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < salleCoursfx.getText().trim().length(); i++) {
            char ch = salleCoursfx.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && salleCoursfx.getText().trim().length() >=5) {
            labelsalle.setText ("salle valide :) ");
            labelsalle.setTextFill(Color.GREEN);
            checksalle.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelsalle.setText ("Check salle !!! ");
            labelsalle.setTextFill(Color.RED);
            checksalle.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
         @FXML
    private void verifierPlanningId(KeyEvent event) {
        int nbNonChar = 0;
        for (int i = 1; i < planningidfx.getText().trim().length(); i++) {
            char ch = planningidfx.getText().charAt(i);
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        if (nbNonChar == 0 && planningidfx.getText().trim().length() <=3) {
            labelplanningid.setText ("planning id :) ");
            labelplanningid.setTextFill(Color.GREEN);
            checkplanningid.setImage(new Image("@../../elitegymcenter/images/CheckMark.png"));


            // verificationUserNom = true;
        } else {
            labelplanningid.setText ("Check planning id !!! ");
            labelplanningid.setTextFill(Color.RED);
            checkplanningid.setImage(new Image("@../../elitegymcenter/images/erreurCheckMark.png"));


        }
    }
     
    /**
     * Initializes the controller class.
     */
    
    ServiceCoursCRUD servicePlanningCRUD = new ServiceCoursCRUD();
  //  static Cours C = new Cours();
      public Cours cr;
    
  public void setRdv(Cours cr) {
    this.cr = cr;
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
      
        if (cr.getDuree() != null) {
            LocalDate localDate = cr.getDuree().toLocalDate();
            dureeCoursfx.setValue(localDate);
        }
        nomCoursfx.setText(String.valueOf(CoursController.C.getNom()));
       // dureeCoursfx.setText(String.valueOf(CoursController.C.getDuree()));
        salleCoursfx.setText(String.valueOf(CoursController.C.getSalle()));
        planningidfx.setText(String.valueOf(CoursController.C.getPlanningId()));
    
}    
    

    @FXML
    private void modifiercours(ActionEvent event) 
    {
    
        
        
        CoursCRUD inter = new ServiceCoursCRUD();
        
         LocalDate localDate = dureeCoursfx.getValue();
    if (localDate == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs vides");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    java.sql.Date duree = java.sql.Date.valueOf(localDate);
        
        String nom = nomCoursfx.getText();
      //  String duree = dureeCoursfx.getText();
        String salle = salleCoursfx.getText();
        int PlanningId = Integer.valueOf(planningidfx.getText());

        
        System.out.println(CoursController.C.getId());
        Cours C1 = new Cours(CoursController.C.getId(),CoursController.C.getPlanningId(),nom, duree, salle );
      
        inter.modifierCours(C1);
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("../../gui/Cours/Cours.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(CoursController.class.getName()).log(Level.SEVERE, null, ex);

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
        root = FXMLLoader.load(getClass().getResource("../../gui/Cours/Cours.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListCours");
        stage.setScene(scene);
        stage.show();
                
    }
}
