/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursService {
    private Connection connection;
    
    public CoursService(Connection connection) {
        this.connection = connection;
    }
    
    public void addCours(Cours cours) throws SQLException {
        String sql = "INSERT INTO cours (id, planning_id, nom, duree, salle) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cours.getId());
        statement.setInt(2, cours.getPlanning_id());
        statement.setString(3, cours.getNom());
        statement.setInt(4, cours.getDuree());
        statement.setString(5, cours.getSalle());
        statement.executeUpdate();
    }
    
    public void updateCours(Cours cours) throws SQLException {
        String sql = "UPDATE cours SET planning_id=?, nom=?, duree=?, salle=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cours.getPlanning_id());
        statement.setString(2, cours.getNom());
        statement.setInt(3, cours.getDuree());
        statement.setString(4, cours.getSalle());
        statement.setInt(5, cours.getId());
        statement.executeUpdate();
    }
    
    public void deleteCours(int id) throws SQLException {
        String sql = "DELETE FROM cours WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    
    public Cours getCoursById(int id) throws SQLException {
        String sql = "SELECT * FROM cours WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Cours cours = new Cours(
                resultSet.getInt("id"),
                resultSet.getInt("planning_id"),
                resultSet.getString("nom"),
                resultSet.getInt("duree"),
                resultSet.getString("salle")
            );
            return cours;
        }
        return null;
    }
    
    public List<Cours> getAllCours() throws SQLException {
        String sql = "SELECT * FROM cours";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Cours> coursList = new ArrayList<>();
        while (resultSet.next()) {
            Cours cours = new Cours(
                resultSet.getInt("id"),
                resultSet.getInt("planning_id"),
                resultSet.getString("nom"),
                resultSet.getInt("duree"),
                resultSet.getString("salle")
            );
            coursList.add(cours);
        }
        return coursList;
    }
}
