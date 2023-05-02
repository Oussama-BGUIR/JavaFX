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
import elitegymcenter.services.ServicePlatCRUD;
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
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PanierController implements Initializable {




    @FXML
    private TableColumn<Plat,Float> menuColPlatPrix;
    @FXML
    private TableColumn<Plat,String> menuColPlatName;
    
    @FXML
    private Label totalPrix;
    @FXML
    private TableColumn<Plat,Integer> menuColPlatQuantite;

private Stage stage; 
    private Scene scene;
    private Parent root;

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
    private int getid;
    private Alert alert;
    
    public Statement stm;
    ServiceMenuCRUD serviceMenuCRUD =new ServiceMenuCRUD();
    
    
    public PanierController(){
        conx= MyDB.getInstance().getConn();
    }

    ServicePlatCRUD servicePlatCRUD = new ServicePlatCRUD();
    @FXML
    private void suppplat(ActionEvent event) throws IOException 
    {

        Plat plat= new Plat();
            plat=menuTableView.getSelectionModel().getSelectedItem();
        
        int selectedIndex = menuTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Plat E = menuTableView.getSelectionModel().getSelectedItem();
            servicePlatCRUD.supprimerPanier(E.getId());
            menuTableView.getItems().remove(selectedIndex);
        } else {
            showAlert("Veuillez sélectionner un Plat à supprimer.");
        }
        root = FXMLLoader.load(getClass().getResource("../gui/Panier.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setTitle("Panier");
                stage.setScene(scene);
                stage.show();
    }
        private void showAlert(String message) 
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
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
        menuTableView.refresh(); // Refresh the TableView after setting the data

    }
    public void menuselectedOrder(){
        //renvoie l'élément actuellement sélectionné dans le modèle de sélection. Ici,
        Plat plat=menuTableView.getSelectionModel().getSelectedItem();
        int num=menuTableView.getSelectionModel().getSelectedIndex();
        if((num -1)<-1)return;
        getid=plat.getId();
    }
   
    /*public void menuRemoveBtn(){
        if(getid==0){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the item you want to remove");
            alert.showAndWait();
        }else {
            String delete="DELETE FROM Commande WHERE ID ="+getid;
            try {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you want sure to delete this product");
                Optional<ButtonType> option=alert.showAndWait();
                if (option.get().equals(ButtonType.OK)){
                    PreparedStatement statement=conx.prepareStatement(delete);
                    statement.executeUpdate();
                }


            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
*/
    public ObservableList<Plat> menuGetOrder(){
        ObservableList<Plat> listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM commande";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);

            while (rs.next()){
                float prix = Float.parseFloat(rs.getString("prix"));
                Plat p = new Plat(rs.getInt("id"),rs.getString("nom"),
                       (int) prix,rs.getInt("quantite_commande"),rs.getDate("date_commande"));

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
        menuColPlatName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        menuColPlatQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite_commande"));
        menuColPlatPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        menuTableView.setItems(menuOrderListData );



    }
    
    
    @FXML
public void menuPrintBtn() {
    PrinterJob job = PrinterJob.createPrinterJob();
    if (job != null) {
        boolean success = job.printPage(createMenuPrintPage());
        if (success) {
            job.endJob();
        }
    }
}

private Node createMenuPrintPage() {
    VBox printPage = new VBox();
    printPage.setAlignment(Pos.TOP_CENTER);
    printPage.setSpacing(10);
    printPage.setPadding(new Insets(20, 20, 20, 20));

    // Add a header to the print page
    Label headerLabel = new Label("Elite Gym Center - Order Details");
    headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    printPage.getChildren().add(headerLabel);

    // Add a table to display the order details
    TableView<Plat> orderTableView = new TableView<>();
    orderTableView.setItems(menuOrderListData);

    TableColumn<Plat, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

    TableColumn<Plat, Integer> quantityColumn = new TableColumn<>("Quantity");
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantite_commande"));

    TableColumn<Plat, Float> priceColumn = new TableColumn<>("Price");
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

    orderTableView.getColumns().addAll(nameColumn, quantityColumn, priceColumn);

    printPage.getChildren().add(orderTableView);

    // Add a total price label to the print page
    Label totalPriceLabel = new Label("Total: $" + totalP);
    totalPriceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    printPage.getChildren().add(totalPriceLabel);

    // Return the print page as a Node object
    return printPage;
}
    
    
        @FXML
        public void retourFront(ActionEvent event) throws IOException{        
        root = FXMLLoader.load(getClass().getResource("../gui/Front.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("ListPlats");
        stage.setScene(scene);
        stage.show();
                
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuGetOrder();
        menuShowOrderData();
        menuDisplayTotal();
        menuTableView.refresh(); // Refresh the TableView after setting the data

    }
}