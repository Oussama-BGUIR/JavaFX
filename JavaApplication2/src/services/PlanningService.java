/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Planning;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanningService {
    private Connection connection;
    
    public PlanningService(Connection connection) {
        this.connection = connection;
    }
    
    public void addPlanning(Planning planning) throws SQLException {
        String query = "INSERT INTO planning (id, semaine, description) VALUES (?, ?, ?)";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, planning.getId());
        statement.setString(2, planning.getSemaine());
        statement.setString(3, planning.getDescription());
        
        statement.executeUpdate();
        statement.close();
    }
    
    public void updatePlanning(Planning planning) throws SQLException {
        String query = "UPDATE planning SET semaine = ?, description = ? WHERE id = ?";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, planning.getSemaine());
        statement.setString(2, planning.getDescription());
        statement.setInt(3, planning.getId());
        
        statement.executeUpdate();
        statement.close();
    }
    
    public void deletePlanning(int id) throws SQLException {
        String query = "DELETE FROM planning WHERE id = ?";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        
        statement.executeUpdate();
        statement.close();
    }
    
    public Planning getPlanningById(int id) throws SQLException {
        String query = "SELECT * FROM planning WHERE id = ?";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        
        ResultSet resultSet = statement.executeQuery();
        Planning planning = null;
        
        if (resultSet.next()) {
            String semaine = resultSet.getString("semaine");
            String description = resultSet.getString("description");
            planning = new Planning(id, semaine, description);
        }
        
        resultSet.close();
        statement.close();
        
        return planning;
    }
    
    public List<Planning> getAllPlanning() throws SQLException {
        String query = "SELECT * FROM planning";
        
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        List<Planning> planningList = new ArrayList<>();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String semaine = resultSet.getString("semaine");
            String description = resultSet.getString("description");
            
            Planning planning = new Planning(id, semaine, description);
            planningList.add(planning);
        }
        
        resultSet.close();
        statement.close();
        
        return planningList;
    }
}
