package persistent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class EmailUtility {
	
	// Sends a confirmation email to a user for registering.

    public static void sendConfirmation(String userEmail, String host, String senderEmail, String senderPassword, String port, int code) throws Exception{

        /*
        Email contents
         */
        String subject = "Account Confirmation - Welcome";
        String messageText = "Hello user, your 4 digit security code is " + code + ". Please input this code to verify your account";

        /*
        Properties to create email request
         */
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");

        /*
        Authenticator to validate credentials
         */
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };

        /*
        Session to create make a message
         */
        Session session = Session.getInstance(props, authenticator);

        /*
        Populating message parameters
         */
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setText(messageText);

        /*
        Sending message
         */
        Transport.send(message);
    }
    
    // Sends a new password to a user that has forgotten their password.
    
    public static void sendNewPassword(String userEmail, String host, String senderEmail, String senderPassword, String port, String newPass) throws Exception {
        String subject = "Account Confirmation - Welcome";
        String messageText = "Hello user, your new Password is " + newPass + ". Please go ahead and change your password.";


        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };


        Session session = Session.getInstance(props, authenticator);


        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setText(messageText);


        Transport.send(message);
    }

    /*
    Sends an email to all users about the new Promotion
    Author: Lakshay Sharma
    */
        public static void sendPromotion(String userEmail, String host, String senderPassword, String port, int promoID, String name, double percent, String expiration, ArrayList<String> emailList) throws Exception {
          String subject = "New Offer! Ends soon!";
          String body = "Hello,\n\nWe would like to invite you to consider our new promotion while you shop at xyz.com. The offer details can be found below:\n\nPromoID: " + promoID + " Name: " + name + " Percent Discount: " + percent +
           " Expiration Date: " + expiration + "\n\nHope you enjoy your time at xyz.com and don't forget to use our new code.";

           Properties properties = new Properties();
           properties.put("mail.smtp.host", host);
           properties.put("mail.smtp.port", port);
           properties.put("mail.smtp.starttls.enable", true);
           properties.put("mail.smtp.auth", true);

           Authenticator authenticator = new Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(userEmail, senderPassword);
               }
           };

           Session session = Session.getInstance(properties, authenticator);

           InternetAddress[] toAddresses = new InternetAddress[emailList.size()];
           for(int i = 0; i < emailList.size(); i++) {
             toAddresses[i] = new InternetAddress(emailList.get(i));
           }

           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(userEmail));
           message.setRecipients(Message.RecipientType.TO, toAddresses);
           message.setSentDate(new Date());
           message.setSubject(subject);
           message.setText(body);

           Transport.send(message);

        }
        
        // Sends an order confirmation after checkout to the user.

        public static void sendOrderConfirmation(String customerEmail, String host, String port, String user, String pass, ArrayList<String> orderNumberList, ArrayList<String> shippingAddressList, ArrayList<String> billingAddressList, ArrayList<String> paymentMethodList, ArrayList<String> confirmationNumberList, ArrayList<String> titles, ArrayList<Integer> quantities, double emailTotal) throws Exception {
        	
        	
        	String subject = "Your Order is Confirmed!";
            String body = "Hello,\n\nThis email includes a copy of your order confirmation. Please email us back if you have any questions.\n\nOrderNumber: " + orderNumberList.get(0) + "\nShipping Address: " + shippingAddressList.get(0) + "\nBilling Address: " + billingAddressList.get(0) +
             "\nPayment Method: " + paymentMethodList.get(0) + "\nConfirmation Number: " + confirmationNumberList.get(0) + "\n\n";

            for(int i = 0; i < titles.size(); i++) {
            	body = body + titles.get(i) + "      Quantity: " + quantities.get(i) + "\n"; 
            }
            body = body + "\n\nTotal Price: $" + emailTotal;
            
             Properties properties = new Properties();
             properties.put("mail.smtp.host", host);
             properties.put("mail.smtp.port", port);
             properties.put("mail.smtp.starttls.enable", true);
             properties.put("mail.smtp.auth", true);

             Authenticator authenticator = new Authenticator() {
                 @Override
                 protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(user, pass);
                 }
             };

             Session session = Session.getInstance(properties, authenticator);


             Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress(user));
             message.addRecipient(Message.RecipientType.TO, new InternetAddress(customerEmail));
             message.setSentDate(new Date());
             message.setSubject(subject);
             message.setText(body);

             Transport.send(message);
        }
}
