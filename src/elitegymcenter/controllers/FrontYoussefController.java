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
import elitegymcenter.services.ServiceCategorieCRUD;
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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FrontYoussefController implements Initializable {




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

    private ObservableList<Produit> cardYoussefListData= FXCollections.observableArrayList();
    public Connection conx;
    public Statement stm;
    ServiceCategorieCRUD serviceCategorieCRUD =new ServiceCategorieCRUD();
    
    
    public FrontYoussefController(){
        conx= MyDB.getInstance().getConn();
    }

    public ObservableList<Produit>menuGetData(){
        ObservableList<Produit>listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM produit";
            stm=conx.createStatement();
            ResultSet rs =stm.executeQuery(sql);
            while (rs.next()){
            int prix = rs.getInt("prix");
            Produit p = new Produit(rs.getInt("id"),prix, serviceCategorieCRUD.get(rs.getInt("categorie_id")),rs.getString("nom"),rs.getString("description"),rs.getString("image"));
                listData.add(p);

            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return listData;
    }
    public void menuDisplayCardYoussef(){
        cardYoussefListData.clear();
        cardYoussefListData.addAll(menuGetData());
        int row=0;
        int column=0;

        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for(int q=0;q<cardYoussefListData.size();q++){
            try {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("../gui/cardYoussef.fxml"));
                AnchorPane pane=loader.load();
                CardYoussefController cardYoussefC=loader.getController();
                cardYoussefC.setData(cardYoussefListData.get(q));
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
    
    
    
    

//    public void menuDisplayTotal(){
//       String total="SELECT COUNT(prix) from commande ";
//
//    }
    
    
    
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuDisplayCardYoussef();
       // menuGetOrder();
        //menuShowOrderData();
    }
        @FXML
    private void VoirPanier(ActionEvent event) 
    {
         try {

            Parent page1= FXMLLoader.load(getClass().getResource("../gui/PanierYoussef.fxml"));
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
    private void retourClient(ActionEvent event) 
    {
        
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/elitegymcenter/gui/BIENVENUE.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontYoussefController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }
    
    
}