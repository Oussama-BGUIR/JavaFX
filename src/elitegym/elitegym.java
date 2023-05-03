/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegym;

import elitegymcenter.services.ServicePlanningCRUD;
import elitegymcenter.services.ServiceCoursCRUD;
import elitegymcenter.entities.Planning;
import elitegymcenter.entities.Cours;
import elitegymcenter.utils.MyDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ousam
 */
public class elitegym extends Application{
	public static Stage stg;
    public void start(Stage primaryStage) throws IOException  
    {
    	elitegym.stg = primaryStage;
     
         
           //FXMLLoader loader= new FXMLLoader(getClass().getResource("../elitegymcenter/gui/Planning/Planning.fxml"));
     FXMLLoader loader= new FXMLLoader(getClass().getResource("../elitegymcenter/gui/Cours/Cours.fxml"));
	            //FXMLLoader loader= new FXMLLoader(getClass().getResource("../elitegymcenter/gui/Cours/Chart.fxml"));


	           // FXMLLoader loader= new FXMLLoader(getClass().getResource("../elitegymcenter/gui/Cours/AffichageCoursFront.fxml"));

	            Parent root= loader.load();
	            Scene scene= new Scene(root);
	            primaryStage.setTitle("Bievennue");
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        
	        
	    }
	    

	 
	    
	   public static void main(String[] args) {
	           launch(args);
	         
	    }}
 
