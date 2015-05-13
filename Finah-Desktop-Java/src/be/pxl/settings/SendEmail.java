package be.pxl.settings;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	public SendEmail(String emailadres, String url) {
		
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
			message.setText(
					"Beste meneer\n"
					+ "Beste mevrouw\n\n"
					+ "Er is aanvraag geweest door uw dokter om deze vragenlijst in te vullen. Hierdoor gaan wij meer informatie krijgen over uw situatie.\n"
					+ "Deze vragenlijst is volledig anoniem, enkel uw dokter gaat weten dat u deze vragenlijst heeft ingevuld.\n\n"
					+ "Om de vragenlijst in te vullen moet u op deze link klikken: " + url + "\n\n"
					+ "Met vriendelijke groeten"
					);

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
