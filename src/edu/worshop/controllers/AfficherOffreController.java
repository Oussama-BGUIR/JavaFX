/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import java.util.Collections;
import java.util.Comparator;
import edu.workshop.services.Offre1CRUD;
import edu.worshop.interfaces.OffreCRUD;
import edu.worshop.model.Abonnement;
import edu.worshop.model.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import java.sql.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author shini
 */
public class AfficherOffreController implements Initializable {

   
    
    
    
    @FXML
    private TableView<Offre> affichageOffrefx;

    @FXML
    private TableColumn<Offre, Integer> idfx;
    @FXML
    private TableColumn<Offre, Integer> pointsfx;

    @FXML
    private TableColumn<Offre, Double> prixfx;

    @FXML
    private TableColumn<Offre, Double> pourcentagefx;

    @FXML
    private TableColumn<Offre, Integer> abonnement_idfx;

    @FXML
    private TableColumn<Offre, Date> datedebutfx;

    @FXML
    private TableColumn<Offre, Date> datefinfx;
    
    @FXML
    private TextField recherchePoints;
    
   
    
    
     static Offre E = new Offre();
    static int id, points;

    static double prix,pourcentage;
    static Date date_debut,date_fin;
     
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        
        
         OffreCRUD inter = new Offre1CRUD();
            List<Offre> list = inter.afficherOffre();
            ObservableList<Offre> observableList = FXCollections.observableArrayList(list);
            affichageOffrefx.setItems(observableList);
            idfx.setCellValueFactory(new PropertyValueFactory<>("Id"));
            abonnement_idfx.setCellValueFactory(new PropertyValueFactory<>("abonnement_nom_id"));
            pointsfx.setCellValueFactory(new PropertyValueFactory<>("Points"));
            prixfx.setCellValueFactory(new PropertyValueFactory<>("Prix"));
            pourcentagefx.setCellValueFactory(new PropertyValueFactory<>("Pourcentage"));
            datedebutfx.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            datefinfx.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
            
            SearchFilter();

    }    

    @FXML
    private void supprimerOffre(ActionEvent event) 
    {
        TableView<Offre> list = (TableView<Offre>) affichageOffrefx;
        OffreCRUD inter = new Offre1CRUD();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer l'offre");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet offre ?");

        Optional<ButtonType> result = alert.showAndWait();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Offre E = list.getSelectionModel().getSelectedItem();
            inter.supprimerOffre(E.getId());
            list.getItems().remove(selectedIndex);
               Notifications notificationBuilder = Notifications.create()
                    .title("Offre supprimé")
                    .text("L'offre a été supprimé avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .graphic(null)
                    .darkStyle();
            notificationBuilder.showInformation();
        } else {
            showAlert("Veuillez sélectionner un abonnement à supprimer.");
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
    private void modifierOffre(ActionEvent event) 
    {
        TableView<Offre> list = affichageOffrefx;
        OffreCRUD inter = new Offre1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Offre e = list.getSelectionModel().getSelectedItem();
 
        int points = e.getPoints();
        double pourcentage = e.getPourcentage();
        double prix = e.getPrix();
        Date date_debut = e.getDate_debut();
        Date date_fin = e.getDate_fin();
        
       E=e;
       System.out.println(E.getId());
        
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/modifierOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void StatistiqueOffre(ActionEvent event) 
    {
        // Create a map to store the frequency of each type
        Map<Integer, Integer> typeFrequency = new HashMap<>();

        // Loop through the items in the TableView
        for (Offre o : affichageOffrefx.getItems()) {
            double prix = o.getPrix();
            double pourcentage = o.getPourcentage();

            if (typeFrequency.containsKey((int)prix)) {
                typeFrequency.put((int)prix, typeFrequency.get((int)prix) + 1);
            } else {
                typeFrequency.put((int)prix, 1);
            }
            
        }
    
        // Create a PieChart data set
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (double prix: typeFrequency.keySet()) {
         
            int frequency = typeFrequency.get((int)prix);
            double percentage = (double) frequency / affichageOffrefx.getItems().size() * 100;
            
            String percentageText = String.format("%.2f%%", percentage);
            String prixText = String.format("%.2f%%", prix);


            PieChart.Data slice = new PieChart.Data("le prix " + prixText + ":" + percentageText, frequency);
            pieChartData.add(slice);
        
        }


    
         // Create a PieChart with the data set
        PieChart chart = new PieChart(pieChartData);
     
        // Show percentage values in the chart's tooltip
        for (final PieChart.Data data : chart.getData()) {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(String.format("%.2f%%", (data.getPieValue() / affichageOffrefx.getItems().size() * 200)));
            Tooltip.install(data.getNode(), tooltip);
        }

        // Show the chart in a new window
        Scene scene = new Scene(chart);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       }
    
     @FXML
    private void AjouterOffre(ActionEvent event) 
    {
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
    
      @FXML
    private void listeAbonnements(ActionEvent event) 
    {
        try {

            Parent page1= FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AfficherAbonnementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(AfficherAbonnementBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
    
    
    
    @FXML 
    private void SearchFilter() {
        ObservableList<Offre> rdvs = affichageOffrefx.getItems();
        FilteredList<Offre> filterData = new FilteredList(rdvs,e->true);
        recherchePoints.setOnKeyReleased(e->{
        recherchePoints.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate((Predicate<? super Offre >) rd->{
                if(newValue==null){
                    return true;
                }
                String toLowerCaseFilter = newValue.toLowerCase();
                if(Integer.toString(rd.getPoints()).contains(newValue)){
                    return true;
                } /*else if(rd.getPrenom().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if (rd.getEmail().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                }*/
                return false;
            });
        });
        final SortedList<Offre> rdvv = new SortedList<>(filterData);
        rdvv.comparatorProperty().bind(affichageOffrefx.comparatorProperty());
        affichageOffrefx.setItems(rdvv);
        });
    }
    
    
    
    
   
}

       
    
    

    

    
    
