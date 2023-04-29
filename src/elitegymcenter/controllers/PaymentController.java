/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import com.stripe.exception.StripeException;
import elitegymcenter.services.Emailsender;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import elitegymcenter.services.ServicePaymentStripe;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ServicePaymentStripe servicePayment;
    int prix;
    @FXML
    private TextField numCardField;

    private boolean payed = false;
    @FXML
    private TextField expMoisField;
    @FXML
    private TextField expanneeField;
    @FXML
    private TextField cvvField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTextLimiter(expMoisField, 2);
        addTextLimiter(expanneeField, 4);
        addTextLimiter(cvvField, 3);

    }

    @FXML
    private void Payer(ActionEvent event) {
        String nom = "YOUSSEF";
        String email = "youssef.hamrouni@esprit.com";
        String numCard = numCardField.getText();
        String expMois = expMoisField.getText();
        String exAnnee = expanneeField.getText();
        String cvv = cvvField.getText();

        servicePayment = new ServicePaymentStripe(email, nom,67500, numCard, expMois, exAnnee, cvv);              
            String message = "Bienvenue à Elite Gym\n"
                        + "\n"
                        + "Votre paiement a été effectué  \n"
                        + "\n"
                        +  "Nom : " + nom  + "\n"
                        + "Prix  : " + "67500$" + "\n"
                       
                        + "\n";

                Emailsender.sendEmail_add("youssef.hamrouni@esprit.tn",message);
        try {
            servicePayment.payer();

            FXMLLoader fxmlLoader = new FXMLLoader();
            payed = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Paiement effectué avec succes");
            alert.show();

        } catch (StripeException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Erreur de paiement");
            alert.show();

        }

    }

    public boolean getReturn() {
        return payed; //return what you want controlled by your controller class
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

}
