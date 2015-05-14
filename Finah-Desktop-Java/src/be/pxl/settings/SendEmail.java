package be.pxl.settings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public SendEmail(String emailadres, boolean client) {
		
		// Recipient's email ID needs to be mentioned.
		String to = emailadres;// change accordingly

		// Sender's email ID needs to be mentioned
		String from = "appdevit03@gmail.com";// change accordingly
		final String username = "appdevit03";// change accordingly
		final String password = "ADIT03ADIT03";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Vragenlijst");

			// Now set the actual message
			String txt = "http://127.0.0.1/survey/1/";
			
			// http://127.0.0.1/survey/1/4/7ffc4632a40ed69d2d643ef520bd08ef22d67e86/ 
			if (client) {
				txt += "4/";
			} else {
				txt += "3/";
			}
			txt += encrypt(String.valueOf(Math.random()));
			message.setText(txt);
			System.out.println(txt);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String encrypt(String x) {
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(x.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	       
	        //convert the byte to hex format for md5
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString.toString();
	}

}
