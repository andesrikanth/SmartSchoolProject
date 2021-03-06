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
import javax.mail.internet.MimeMessage.RecipientType;


public class MailSender {
   
    public static void main(String[] args) {
                
                /*
    	
    	// Recipient's email ID needs to be mentioned.
        String to = "test@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "test@gmail.com";

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
        
    	final String username = "test@gmail.com";
    	final String password = "test$2019";

    	//support : https://support.google.com/accounts/answer/6010255

    	//https://www.google.com/settings/security/lesssecureapps    ==> open this URL in browser and temporarily turn on your less secure apps. 
    	//Once your mail purpose is done, then turn off the setting. 

    	Properties props = new Properties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.from"  ,                 username);
    	props.put("mail.host"  ,"gmail.com");
    	props.put("mail.smtp.starttls.enable"   , true);
    	props.put("mail.smtp.starttls.required" , true);
    	props.put("mail.smtp.host" , "smtp.gmail.com");
    	props.put("mail.smtp.auth" ,true);
    	props.put("mail.smtp.port" , 587);
    	props.put("mail.user" , username);
    	props.put("mail.password" , password);
    	//props.put("proxySet","true"); 
    	//props.put("socksProxyHost","www-proxy.us.oracle.com");
    	//props.put("socksProxyPort","80");


    	Session session = Session.getInstance(props,
    	  new javax.mail.Authenticator() {
    	                protected PasswordAuthentication getPasswordAuthentication() {
    	                                return new PasswordAuthentication(username, password);
    	                }
    	  });

    	try {
    	                
    	        ArrayList<String> arr = new ArrayList<String>();
    	        arr.add("test@gmail.com");
    	        arr.add("test@gmail.com");
    	                
    	        for(String mail : arr){
    	            Message message = new MimeMessage(session);
    	            message.setFrom(new InternetAddress("test@gmail.com"));
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
    
    
    public boolean sendMail(ArrayList<String> toList, ArrayList<String> ccList, ArrayList<String> bccList, String mailHeading, String subject){
    
    	
    	//http://tomcat.apache.org/tomcat-6.0-doc/jndi-resources-howto.html#JavaMail_Sessions
    	
    	
    	boolean status =false;
    	
    	final String username = "test@gmail.com";
    	final String password = "test$005";

    	//support : https://support.google.com/accounts/answer/6010255

    	//https://www.google.com/settings/security/lesssecureapps    ==> open this URL in browser and temporarily turn on your less secure apps. 
    	//Once your mail purpose is done, then turn off the setting. 

    	Properties props = new Properties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.from"  ,  username);
    	props.put("mail.host"  ,"gmail.com");
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
    			Message message = new MimeMessage(session);
                
    			if(toList!= null && toList.size()>0){
                	InternetAddress[] addressTo = new InternetAddress[toList.size()];
        	        
        	        for (int i = 0; i < toList.size(); i++){
        	            addressTo[i] = new InternetAddress(toList.get(i));
        	        }
    	            message.setRecipients(RecipientType.TO, addressTo); 
        	         
                }
    			
    	           message.setFrom(new InternetAddress("test@gmail.com"));
    	            message.setSubject("Testing Subject");
    	            message.setText("My Mail Body");

    	            Transport.send(message);

    	            System.out.println("Done");
    	                

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return status;
    }

}

