/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/*
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;*/

 
import elitegymcenter.entities.Cours;
import elitegymcenter.services.ServiceCoursCRUD;
 import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
 import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.Account;
 import com.twilio.rest.api.v2010.account.Message;
 import com.twilio.type.PhoneNumber;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
/**
 * FXML Controller class
 *
  */
public class AffichageCoursFrontController implements Initializable {

    @FXML
    private TilePane produitsTilePane;
    ServiceCoursCRUD a = new ServiceCoursCRUD();
    public static Cours pr ; 
    @FXML
    private Button panierButton;
    static Cours C = new Cours();
  
 ObservableList<Cours> obList;

    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
        ServiceCoursCRUD a = new ServiceCoursCRUD();

        obList = a.afficherCours();
        //Panier panier = new Panier();
      
        for (Cours crs : obList) {
       	 System.out.println("cours: est" +crs.toString());

       	Card card;
		try {
			card = new Card(crs);
	        produitsTilePane.getChildren().add(card);

		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         }
        
 
    }

    private class Card extends VBox {
    	private Cours cours;
        //private Panier panier;

        public Card(Cours cours) throws WriterException {
            if (cours == null) {
                throw new IllegalArgumentException("cours cannot be null");
            }
            this.cours = cours;
             
            //this.panier = panier;
        	
     	    File file = new File(cours.getImage());
    	    System.out.println("image:"+cours.getImage());
    	    Image image = new Image(file.toURI().toString());

    	    // Cr�er une vue d'image et l'ajouter � un conteneur ou � la sc�ne principale
    	    ImageView imageView = new ImageView(image);
    	    imageView.setFitWidth(150);
    	    imageView.setFitHeight(150);
    	    Label nomLabel = new Label(cours.getNom());
    	    Label descriptionLabel = new Label(cours.getSalle());
    	    Label prixLabel = new Label("Date: " + cours.getDuree());
    	    prixLabel.setTextFill(Color.RED);
    	    VBox.setMargin(imageView, new Insets(10, 0, 0, 0));
    	    VBox.setMargin(nomLabel, new Insets(10, 0, 0, 0));
    	    VBox.setMargin(descriptionLabel, new Insets(10, 0, 0, 0));
    	    VBox.setMargin(prixLabel, new Insets(10, 0, 0, 0));

    	    // Create heart icons
    	    FontAwesomeIconView panierIcon = new FontAwesomeIconView(FontAwesomeIcon.PLAY);
    	 // D�finir la taille de l'ic�ne
    	    panierIcon.setSize("2em");

    	    // D�finir la couleur de l'ic�ne
    	    panierIcon.setFill(Color.GREEN);
    	    
    	    panierIcon.setOnMouseClicked(arg0 -> {      
    	    	  // Cr�er la popup
    	        Popup popup = new Popup();
    	        popup.setAutoHide(true);

    	        // Cr�er le texte et les boutons
    	        Label label = new Label("Cours selectionnes");
    	        Button closeButton = new Button("Fermer");
    	        Button participateButton = new Button("Je participe a ce cours");

    	        // Ajouter le texte et les boutons � la popup
    	        VBox box = new VBox(label, participateButton, closeButton);
    	        box.setStyle("-fx-background-color: white; -fx-padding: 10px;");
    	        popup.getContent().add(box);

    	        // D�finir l'action du bouton "Fermer"
    	        closeButton.setOnAction(closeEvent -> {
    	            popup.hide();
    	        });


    	       participateButton.setOnAction(participateEvent -> {
    System.out.println("Je participe à ce cours");

    String qrCodeString = cours.getPlanningQR();
    int size = 500;
    try {
        if (qrCodeString != null) {
            byte[] qrCodeBytes = Base64.getDecoder().decode(qrCodeString);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(qrCodeBytes);
            BufferedImage qrCodeBufferedImage = ImageIO.read(inputStream);
            Image qrCodeImage = SwingFXUtils.toFXImage(qrCodeBufferedImage, null);
            ImageView qrCodeView = new ImageView(qrCodeImage);
            qrCodeView.setFitWidth(size);
            qrCodeView.setFitHeight(size);
            box.getChildren().add(qrCodeView);
        } else {
            System.out.println("QR Code string is null.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }



    	            // Cr�er une vue d'image et l'ajouter � un conteneur ou � la sc�ne principale
    	   
    	           
    	           String accountSid = "ACb921518a539268564e5b2714c21a3c9a";
        String authToken = "1e92c19d77091d0b68ffb4975619fbfb";
        Twilio.init(accountSid, authToken);
        String fromPhoneNumber = "+16205249569"; // Change this to your Twilio phone number
        String toPhoneNumber = "+21620052781"; // Change this to the user's phone number
        String smsBody = "Bonjour, Vous avez participer Un cours :"+cours.getNom()+" a date :"+cours.getDuree();
     try {
    Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), smsBody).create();
    System.out.println("SMS envoyé avec succès à " + toPhoneNumber);
} catch (ApiException e) {
    System.err.println("Erreur lors de l'envoi du SMS : " + e.getMessage());
}
     //  Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), smsBody).create();
       // System.out.println("SMS sent successfully to " + toPhoneNumber);
    	             
    	        });
    	        box.setPrefSize(200, 100);
    	     
    	        // Centrer la popup
    	        double centerX = (panierIcon.getScene().getWindow().getWidth() - box.getPrefWidth()) / 2.0;
    	        double centerY = (panierIcon.getScene().getWindow().getHeight() - box.getPrefHeight()) / 2.0;
    	        popup.show(panierIcon.getScene().getWindow(), centerX, centerY);
    	        // Afficher la popup pr�s de l'ic�ne du panier
    	        Bounds bounds = panierIcon.localToScreen(panierIcon.getBoundsInLocal());
    	        popup.show(panierIcon.getScene().getWindow(), bounds.getMinX(), bounds.getMaxY());
    	    });
 
 
     
    	    // Add heart button to card
    	    HBox heartBox = new HBox();
    	    heartBox.setAlignment(Pos.CENTER_RIGHT);
    	    heartBox.getChildren().addAll(panierIcon);
    	    VBox.setMargin(heartBox, new Insets(0, 10, 0, 0));

    	    // Add all nodes to the card
    	    getChildren().addAll(imageView, nomLabel, descriptionLabel, prixLabel, heartBox);
    	    setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10;");
    	    setPrefWidth(200);
    	    setPrefHeight(250);
    	    getStyleClass().add("product-card");

        }

		
    }
      public class PanierPopup extends Popup {
     	/*    private VBox vbox;
    	    private Panier panier;
    	    private GridPane gridPane;

    	    public PanierPopup(Panier panier) {
    	        this.panier = panier;
    	        gridPane = new GridPane();
    	        gridPane.setHgap(10);
    	        gridPane.setVgap(5);
    	        gridPane.setPadding(new Insets(10));
    	        //Create close button
    	        Button closeButton = new Button("Fermer");
    	        closeButton.setOnAction(event -> {
     
    	        	   Window window = closeButton.getScene().getWindow();
    	        	    if (window instanceof Stage) {
    	        	        Stage stage = (Stage) window;
    	        	        stage.hide();
    	        	    } else {
    	        	        window.hide();
    	        	    }   	        });
    	        GridPane.setConstraints(closeButton, 1, 2); // Place le bouton en dessous des autres boutons
    	        gridPane.getChildren().add(closeButton);
    	        // Create list view
    	        ListView<Produit> listView = new ListView<>();
    	        listView.setCellFactory(param -> new ListCell<Produit>() {
    	            @Override
    	            protected void updateItem(Produit produit, boolean empty) {
    	                super.updateItem(produit, empty);
    	                if (empty || produit == null) {
    	                    setText(null);
    	                } else {
    	                    HBox hbox = new HBox();
    	                    hbox.setAlignment(Pos.CENTER_LEFT);
    	                    Label label = new Label(produit.getNom() + " - Quantit�: " + produit.getQuantite() + " - Prix: " + produit.getPrix());
    	  
    	                    hbox.getChildren().addAll(label );
    	                    setText(null);
    	                    setGraphic(hbox);
    	                }
    	            }
    	        });
    	         
    	         
    	        listView.setItems(FXCollections.observableArrayList(panier.getProduits()));
     	        GridPane.setConstraints(listView, 0, 0, 2, 1);
    	     // Create close button
    	     
    	        double prixTotal = calculerPrixTotal();
    	        Label labelPrixTotal = new Label();
    	        labelPrixTotal.setText("Total:" + prixTotal);
 
    	        labelPrixTotal.setTextFill(Color.GREEN);
    	        GridPane.setConstraints(labelPrixTotal, 0, 2);

    	        // Calculate initial total price
    	     // Calculate initial total price
    	        for (Produit produit : panier.getProduits()) {
    	            prixTotal += produit.getPrix() * produit.getQuantite();
    	        }
    	        labelPrixTotal.setText("Total:" + prixTotal);
    	        System.out.println(prixTotal);
    	       System.out.println( prixTotal);
    	         // Create button to remove product from cart
    	        Button supprimerButton = new Button("Supprimer");
    	        supprimerButton.setOnAction(event -> {
    	            Produit produit = listView.getSelectionModel().getSelectedItem();

    	            if (produit != null) {
    	            	System.out.println("supp");
    	            	ObservableList<Produit> produits = listView.getItems();
    	            	produits.remove(produit);
        	            listView.refresh();

    	            	listView.setItems(produits);
    	                
    	                updatePrixTotalLabel(labelPrixTotal);
     	                
    	            }
    	            listView.refresh();
    	        });
    	        GridPane.setConstraints(supprimerButton, 0, 1);
    	      

    	        // Create passer commande button
    	        Button passerCommandeButton = new Button("Passer Commande");
    	        passerCommandeButton.setOnAction(event -> {
    	        	CommandeService sc=new CommandeService();
    	        	Commande c =new Commande();
  
    	        	c.setPanierId(4);
     	        	c.setAdresseLivraison("Tunis");
    	        	c.setPrixTotal(calculerPrixTotal());
    	        	c.setStatus("en cours");
    	        	try {
						sc.createCommande(c);
						 Window window = passerCommandeButton.getScene().getWindow();
	    	        	    if (window instanceof Stage) {
	    	        	        Stage stage = (Stage) window;
	    	        	        stage.hide();
	    	        	    } else {
	    	        	        window.hide();
	    	        	    } 
						Alert alert = new Alert(AlertType.INFORMATION);
					    alert.setHeaderText(null);
					    alert.setContentText("Commande ajout�e avec succ�s et mail envoyee avec succes");
					    alert.showAndWait();
					   

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	        });
    	        GridPane.setConstraints(passerCommandeButton, 1, 1);
    	     
    	        // Add all nodes to the grid pane
    	     // Create label for total price
    	     

    	        // Add label to grid pane
    	        gridPane.getChildren().addAll(listView, supprimerButton,passerCommandeButton, labelPrixTotal);

    	        // Set grid pane as the content of the popup
    	        this.getContent().add(new BorderPane(gridPane));
    	    }
    	    private void updatePrixTotalLabel(Label labelPrixTotal) {
    	        double prixTotal = calculerPrixTotal();
    	        labelPrixTotal.setText("Total:" + prixTotal);
    	    }

    	    public void addPanierButton(Button button) {
    	        if (gridPane == null) {
    	            gridPane = new GridPane();
    	            gridPane.setHgap(10);
    	            gridPane.setVgap(5);
    	            gridPane.setPadding(new Insets(10));
    	            this.getContent().add(new BorderPane(gridPane));
    	        }
    	        GridPane.setConstraints(button, 0, gridPane.getRowCount());
    	        gridPane.getChildren().add(button);
    	    }
    	    private double calculerPrixTotal() {
    	        double prixTotal = 0.0;
    	        for (Produit produit : panier.getProduits()) {
    	            prixTotal += produit.getPrix() * produit.getQuantite();
    	        }
    	        return prixTotal;
    	    }
    	}*/
    	        
    	  
} }
     

 