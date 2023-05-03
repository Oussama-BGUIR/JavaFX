/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elitegymcenter.services;
import elitegymcenter.entities.Planning;


import elitegymcenter.entities.Cours;

import elitegymcenter.utils.MyDB;
 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import elitegymcenter.interfaces.CoursCRUD;
import jakarta.mail.*;
import jakarta.mail.internet.*;
/**
 *
 * @author helmi
 */
public class ServiceCoursCRUD implements CoursCRUD{
    Statement ste;
    Connection conn = MyDB.getInstance(). getConnection();
    
    @Override

    
    public void ajouterCours(Cours cours) {
        try {
            String req = "INSERT INTO `cours`(`planning_id`, `nom`, `duree`, `salle`, `image`) VALUES (?,?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, cours.getPlanningId());
            preparedStatement.setString(2, cours.getNom());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(cours.getDuree()));
            preparedStatement.setString(4, cours.getSalle());
            preparedStatement.setString(5, cours.getImage());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                cours.setId(rs.getInt(1));
                System.out.println("Cours ajout� avec succ�s (id=" + cours.getId() + ")");
            }
       String subject = "Nouveaux Cours  ";
            String body = "Bonjour,\n\n Un  nouvelle cours "+ cours.getNom()+" a jouter pour vous ainsi le date est :"+Timestamp.valueOf(cours.getDuree())+"\n\nCordialement,\nL'équipe de support";
            sendEmail("helmi.rejabjriji@esprit.tn"
, subject, body); // Call function to send email
 
        } catch (SQLException ex) {
            System.out.println("Malheureusement le Cours n'est pas ajout� .. ");
            ex.printStackTrace();
        }
    }

    private void sendEmail(String to, String subject, String body) {
        String username = "helmi.rejabjriji@esprit.tn";
        String password = "ggkkughlvhpcghzv";
         Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // Change this to your SMTP server host(yahoo...)
            props.put("mail.smtp.port", "587"); // Change this to your SMTP server port
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session;
            session = Session.getInstance(props,new Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(username, password);
            }
        });
           
           
               try {
                // Create a MimeMessage object
     
    // Create a new message
                MimeMessage message = new MimeMessage(session);
                // Set the From, To, Subject, and Text fields of the message
                message.setFrom(new InternetAddress(username));
                message.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(body);

                // Send the message using Transport.send
                Transport.send(message);

                System.out.println("Email sent successfully");
            } catch (MessagingException ex) {
                System.err.println("Failed to send email: " + ex.getMessage());
            }
               
        }
    
    
    @Override
    public ObservableList<Cours> afficherCours() {
        ObservableList<Cours> list = FXCollections.observableArrayList();
        try {
            String req = "SELECT c.id, c.planning_id, c.nom, c.duree, c.salle,c.image, p.description,p.image_code_qr FROM cours c JOIN planning p ON c.planning_id = p.id;";
            		
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setPlanningId(rs.getInt("planning_id"));
                c.setNom(rs.getString("nom"));
                c.setDuree(rs.getTimestamp("duree").toLocalDateTime());
                c.setSalle(rs.getString("salle"));
                c.setImage(rs.getString("image"));
                c.setPlanningDescription(rs.getString("description")); 
                c.setPlanningQR(rs.getString("image_code_qr"));// Ajout de la description du planning
                System.out.println(c.toString());
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    
    @Override
    public void supprimerCours(int id) {
        try {
            String req = "DELETE FROM `cours` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Le cours a �t� supprim� !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


   @Override
  public void modifierCours(Cours C) {
        try {
            String req = "UPDATE `Cours` SET `planning_id` = ?,`nom` = ?, `duree` = ?, `salle` = ? WHERE `cours`.`id` = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, C.getPlanningId());
            preparedStatement.setString(2, C.getNom());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(C.getDuree()));
            preparedStatement.setString(4, C.getSalle());
            preparedStatement.setInt(5, C.getId());

            preparedStatement.executeUpdate();
            System.out.println("Le Cours est modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
