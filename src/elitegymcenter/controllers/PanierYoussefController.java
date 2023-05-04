/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.entities.Categorie;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import elitegymcenter.entities.Produit;
import elitegymcenter.entities.commandeYoussef;
import elitegymcenter.interfaces.ProduitCRUD;
import elitegymcenter.services.ServiceCategorieCRUD;
import elitegymcenter.services.ServiceProduitCRUD;
import elitegymcenter.utils.MyDB;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PanierYoussefController implements Initializable {


Connection conn = MyDB.getInstance().getConn();

    @FXML
    private TableColumn<Produit,Float> menuColProduitPrix;
    @FXML
    private TableColumn<Produit,String> menuColProduitName;


    @FXML
    private TableColumn<Produit,Integer> menuColProduitQuantite;

    @FXML
    private Pane menuForm;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private TableView<Produit> menuTableView;
    

    @FXML
    private Label menuTot;
    @FXML
    public Label totalPrix;

    private ObservableList<Produit> cardListData= FXCollections.observableArrayList();
    private int getid;
    public Connection conx;
    public Statement stm;
    public double totalP;
    ServiceCategorieCRUD serviceCategorieCRUD =new ServiceCategorieCRUD();
    private Alert alert;
    private Stage stage; 
    private Scene scene;
    private Parent root;

    
    public PanierYoussefController(){
        conx= MyDB.getInstance().getConn();
    }
    
    ServiceProduitCRUD serviceProduitCRUD = new ServiceProduitCRUD();
   @FXML
private void suppprod(ActionEvent event) {
    TableView<Produit> table = menuTableView;
    ProduitCRUD inter = new ServiceProduitCRUD();

    int selectedIndex = table.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Produit prd = table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer le pruoduit");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce produit ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            inter.supprimerProduit(prd.getId());
            table.getItems().remove(selectedIndex);

            Notifications notificationBuilder = Notifications.create()
                    .title("Rendez-vous supprimé")
                    .text("Le produit de l'id " + prd.getId() + " a été supprimé avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER)
                    .graphic(null)
                    .darkStyle();
            notificationBuilder.showInformation();
        }
    } else {
        showAlert("Sélectionner un produit pour le supprimer ");
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

    public ObservableList<Produit> menuGetOrder(){
        ObservableList<Produit> listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM commandeyoussef";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);

            while (rs.next()){
                float prix = Float.parseFloat(rs.getString("prix"));
                Produit p = new Produit(rs.getInt("id"),rs.getString("nom"), (int) prix,rs.getInt("quantite_commande"),rs.getDate("date_commande"));

                listData.add(p);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return  listData;
    }


    private ObservableList<Produit> menuOrderListData ;
    public void menuShowOrderData(){
        menuOrderListData =menuGetOrder();
        menuColProduitName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        menuColProduitQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite_commande"));
        menuColProduitPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        menuTableView.setItems(menuOrderListData );



    }
    public void menuDisplayTotal(){
        try{
            String total="SELECT sum(prix) from commandeyoussef ";
            stm=conx.createStatement();
            ResultSet rs =stm.executeQuery(total);
            if (rs.next()){
                 totalP=rs.getDouble("SUM(prix)");

            }
            totalPrix.setText("$"+totalP);

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       menuGetOrder();
      menuShowOrderData();
      menuDisplayTotal();
    }
    
     @FXML
    private void Retour(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/FrontYoussef.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        
    }
 @FXML   
private void Payer(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Payment.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
          
        } catch (IOException ex) {
            System.out.println("Erreur\n");
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);

        }  
    }

  
 
}