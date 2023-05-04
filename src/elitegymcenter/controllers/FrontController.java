/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.controllers;

import elitegymcenter.controllers.Plat.PlatController;
import elitegymcenter.entities.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import elitegymcenter.entities.Plat;
import elitegymcenter.services.ServiceMenuCRUD;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontController implements Initializable {




    @FXML
    private TableColumn<Plat,Float> menuColProduitPrix;
    @FXML
    private TableColumn<Plat,String> menuColProduitName;


    @FXML
    private TableColumn<Plat,Integer> menuColProduitQuantite;
    
        @FXML
    private Label totalPrix;

    @FXML
    private Pane menuForm;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private TableView<Plat> menuTableView;

    @FXML
    private Label menuTot;

    private ObservableList<Plat> cardListData= FXCollections.observableArrayList();
    public Connection conx;
    private double totalP;
    public Statement stm;
    ServiceMenuCRUD serviceMenuCRUD =new ServiceMenuCRUD();
    
    
    public FrontController(){
        conx= MyDB.getInstance().getConn();
    }

    public ObservableList<Plat>menuGetData(){
        ObservableList<Plat>listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM plat";
            stm=conx.createStatement();
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next()){
            int prix = rs.getInt("prix");
            Plat p = new Plat(serviceMenuCRUD.get(rs.getInt("menu_id")),rs.getInt("id"),rs.getInt("calorie"),prix,rs.getBoolean("disponibilte"),rs.getString("nom"),rs.getString("description"),rs.getString("image"));
                listData.add(p);

            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return listData;
    }
    public void menuDisplayCard(){
        cardListData.clear();
        cardListData.addAll(menuGetData());
        int row=0;
        int column=0;

        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for(int q=0;q<cardListData.size();q++){
            try {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("../gui/card.fxml"));
                AnchorPane pane=loader.load();
                CardController cardC=loader.getController();
                cardC.setData(cardListData.get(q));
                if(column==3){
                    column=0;
                    row+=1;
                }
                menuGridPane.add(pane,column++,row);
               // GridPane.setMargin(pane,new Insets(10));
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
    
    
    
    

    public void menuDisplayTotal(){
        try{
            String total="SELECT sum(prix) from commande ";
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
    
    
    
    public ObservableList<Plat> menuGetOrder(){
        ObservableList<Plat> listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM commande";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);

            while (rs.next()){
                float prix = Float.parseFloat(rs.getString("prix"));
                Plat p = new Plat(rs.getInt("id"),rs.getString("nom"), (int) prix,rs.getInt("quantite_commande"),rs.getDate("date_commande"));

                listData.add(p);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return  listData;
    }


    private ObservableList<Plat> menuOrderListData ;
    public void menuShowOrderData(){
        menuOrderListData =menuGetOrder();
        menuColProduitName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        menuColProduitQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite_commande"));
        menuColProduitPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        menuTableView.setItems(menuOrderListData );



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuDisplayCard();
       // menuGetOrder();
        //menuShowOrderData();
    }
        @FXML
    private void VoirPanier(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/Panier.fxml"));
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