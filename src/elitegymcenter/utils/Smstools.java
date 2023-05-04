/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elitegymcenter.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import elitegymcenter.entities.Utilisateur;

/**
 *
 * @author ahmed_jemai
 */
public class Smstools {
     public static void sendSms( Utilisateur user, String code ) {
        String ACCOUNT_SID = "AC1d2737bd858a467baad0291d63c9d8fa";
        String AUTH_TOKEN = "645e7482b9716ac1ef82c6f5504893a2";
        
         
          Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                // to phone number
                new com.twilio.type.PhoneNumber("+216"+user.getCin()),
                new com.twilio.type.PhoneNumber("+16813123347"),
                " Votre code de reinitialisation du not de passe est : "+code)
            .create();

        System.out.println(message.getSid());
        }
    
    
}
