/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;

import elitegymcenter.interfaces.MenuCRUD;
import elitegymcenter.entities.Menu;

import elitegymcenter.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ousam
 */
public class ServiceMenuCRUD implements MenuCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance().getConn();
    
    
    
    
    @Override
    
  /*  
    public void ajouterMenu(Menu M) {
       try {
            String req = "INSERT INTO `menu`( `nom`, `description`, `disponibilite`, `calorie`, `image`) VALUES ('"+M.getNom()+"','"+M.getDescription()+"','"+M.getDisponibilite()+"','"+M.getCalorie()+"','"+M.getImage()+"')";
           System.out.println(M.getNom());
           System.out.println(M.getDescription());
           System.out.println(M.getDisponibilite());
           System.out.println(M.getCalorie());
           System.out.println(M.getImage());
           
            ste = conn.createStatement();
           System.out.println("pressssssssss");

            ste.executeUpdate(req);
            System.out.println("updateddd");
            
            System.out.println("Menu ajouté avec success !!!");
        } catch (SQLException ex) {
            System.out.println("malheureusement le Menu n'est pas ajouté .. ");
    
    
}
    }
    
   */
    


    
        public void ajouterMenu(Menu M) {
        try {
            String req = "INSERT INTO `menu`( `nom`, `description`, `disponibilite`, `calorie`, `image`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, M.getNom());
            preparedStatement.setString(2, M.getDescription());
            preparedStatement.setBoolean(3, M.getDisponibilite());
            preparedStatement.setInt(4, M.getCalorie());
            preparedStatement.setString(5, M.getImage());
            preparedStatement.executeUpdate();
            System.out.println("Menu ajouté avec succès !!!");
        } catch (SQLException ex) {
            System.out.println("Malheureusement le menu n'est pas ajouté .. ");
            ex.printStackTrace();
        }
    }
       

          @Override
        public List<Menu> afficherMenu() {
        List<Menu> list = new ArrayList<>();
        try {
            String req = "Select * from menu";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Menu M = new Menu();
             M.setId(RS.getInt(1));
             M.setNom(RS.getString(2));
             M.setDescription(RS.getString(3));
             M.setDisponibilite(RS.getBoolean(4));
             M.setCalorie(RS.getInt(5));
             M.setImage(RS.getString(6));
             list.add(M);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
  

    @Override
    public void supprimerMenu(int id) {
        try {
            String req = "DELETE FROM `menu` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("le menu est supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
     @Override
  
    public void modifierMenu(Menu M) {
        try {
            String req = "UPDATE `Menu` SET `nom` = ?, `description` = ?, `disponibilite` = ?, `calorie` = ?, `image` = ? WHERE `menu`.`id` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setString(1, M.getNom());
            preparedStatement.setString(2, M.getDescription());
            preparedStatement.setBoolean(3, M.getDisponibilite());
            preparedStatement.setInt(4, M.getCalorie());
            preparedStatement.setString(5, M.getImage());
            preparedStatement.setInt(6, M.getId());
            preparedStatement.executeUpdate();
            System.out.println("Le menu est modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    @Override
    public Menu get(int id) {
        Menu menu=null;
        try {
            String req="SELECT * FROM menu " +
                    "where id=?";
            PreparedStatement ps=conn.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                Menu m = new Menu(rs.getInt("id"),  rs.getInt("calorie"), rs.getBoolean("disponibilite") ,rs.getString("nom"), rs.getString("description"), rs.getString("image"));
                menu=m;

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

        return menu;
    }
    
}



