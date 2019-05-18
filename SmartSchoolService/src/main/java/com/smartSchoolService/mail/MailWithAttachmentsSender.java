package com.smartSchoolService.mail;

	import javax.activation.DataHandler;
	import javax.activation.DataSource;
	import javax.activation.FileDataSource;
	import javax.mail.Authenticator;
	import javax.mail.BodyPart;
	import javax.mail.Multipart;
	import javax.mail.PasswordAuthentication;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMessage.RecipientType;
	import javax.mail.internet.MimeMultipart;

	import java.util.Properties;

	public class MailWithAttachmentsSender {
	    @SuppressWarnings("static-access")
	    public static void main(String args[]) {
	    	
	    	final String username = "test123@gmail.com";
	    	final String password = "Test$123";

	    	
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.stmp.user", username);          
	        //If you want you use TLS 
	        props.put("mail.smtp.auth", "true");

	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.password", password);
	        // If you want to use SSL
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class",
	                   "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "465");
	        Session session = Session.getDefaultInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username,password); 
	            }
	        });
	        String[] to = {"test123@gmail.com"};
	        String from = "test123@gmail.com";
	        String subject = "Testing...";
	        MimeMessage msg = new MimeMessage(session);
	        try {
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] addressTo = new InternetAddress[to.length];
	            for (int i = 0; i < to.length; i++)
	            {
	                addressTo[i] = new InternetAddress(to[i]);
	            }
	            msg.setRecipients(RecipientType.TO, addressTo); 
	            // msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
	            msg.setSubject(subject);
	            // msg.setText("JAVA is the BEST");

	            // Create the message part 
	            BodyPart messageBodyPart = new MimeBodyPart();

	            // Fill the message
	            messageBodyPart.setText("This is message body");

	            // Create a multipar message
	            Multipart multipart = new MimeMultipart();

	            // Set text message part
	            multipart.addBodyPart(messageBodyPart);

	            // Part two is attachment
	            messageBodyPart = new MimeBodyPart();
	            String filename = "C:\\Users\\srande\\Desktop\\test.txt";
	            DataSource source = new FileDataSource(filename);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName("test.txt");
	            multipart.addBodyPart(messageBodyPart);

	            // Send the complete message parts
	            msg.setContent(multipart );

	            Transport transport = session.getTransport("smtp");
	            transport.send(msg);
	            System.out.println("E-mail sent !");
	        }
	        catch(Exception exc) {
	            System.out.println(exc);
	        }
	    }
}
