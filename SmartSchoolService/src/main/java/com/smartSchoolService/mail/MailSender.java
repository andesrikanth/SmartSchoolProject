package com.smartSchoolService.mail;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender {
   
    public static void main(String[] args) {
                
                /*
    	
    	// Recipient's email ID needs to be mentioned.
        String to = "loveysrikanthande@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "srikanthande1990@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", "10.145.17.152");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try{
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("This is the Subject Line!");

           // Send the actual HTML message, as big as you like
           message.setContent("<h1>This is actual message</h1>", "text/html" );

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
*/        
        
    	final String username = "javaprojects2013@gmail.com";
                                final String password = "pwd";
                                
                                //support : https://support.google.com/accounts/answer/6010255
                                
                                //https://www.google.com/settings/security/lesssecureapps    ==> open this URL in browser and temporarily turn on your less secure apps. 
                                //Once your mail purpose is done, then turn off the setting. 
                                
                                Properties props = new Properties();
                                props.put("mail.transport.protocol", "smtp");
                                props.put("mail.from"  ,                 username);
                                props.put("mail.host"                   ,"gmail.com");
                                props.put("mail.smtp.starttls.enable"   , true);
                                props.put("mail.smtp.starttls.required" , true);
                                props.put("mail.smtp.host" , "smtp.gmail.com");
                                props.put("mail.smtp.auth" ,true);
                                props.put("mail.smtp.port" , 587);
                                props.put("mail.user" , username);
                                props.put("mail.password" , password);
                                props.put("proxySet","true"); 
                                props.put("socksProxyHost","www-proxy.us.oracle.com");
                                props.put("socksProxyPort","80");
                                

                                Session session = Session.getInstance(props,
                                  new javax.mail.Authenticator() {
                                                protected PasswordAuthentication getPasswordAuthentication() {
                                                                return new PasswordAuthentication(username, password);
                                                }
                                  });

                                try {
                                                
                                                ArrayList<String> arr = new ArrayList<String>();
                                                arr.add("srikanthande1990@gmail.com");
                                                arr.add("lovelysrikanthande@gmail.com");
                                                
                                                for(String mail : arr){
                                                                Message message = new MimeMessage(session);
                                                                message.setFrom(new InternetAddress("lovelysrikanthande@gmail.com"));
                                                                message.setRecipients(Message.RecipientType.TO,
                                                                                InternetAddress.parse(mail));
                                                                message.setSubject("Testing Subject");
                                                                message.setText("My Mail Body");

                                                                Transport.send(message);

                                                                System.out.println("Done");
                                                }
                                                

                                } catch (MessagingException e) {
                                                throw new RuntimeException(e);
                                }
    }

}

