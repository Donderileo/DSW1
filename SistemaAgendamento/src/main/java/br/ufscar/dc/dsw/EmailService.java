package br.ufscar.dc.dsw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService {

	public void send(InternetAddress from, InternetAddress to, String subject, String body) {
	
		try {

			Properties prop = new Properties();
			InputStream is = new FileInputStream("../../../../../resources/config.properties");
			
			if (is != null) {
				prop.load(is);
			} 
			
			final String username = prop.getProperty("agendamentodonaderi@gmail.com");
			final String password = prop.getProperty("donaderi");

			Session session = Session.getInstance(prop, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(from);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(subject);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(body, "text/plain");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			
			message.setContent(multipart);
			Transport.send(message);
			
			System.out.println("Mensagem enviada com sucesso!");
			
		} catch (Exception e) {
			System.out.println("Mensagem não enviada!");
			e.printStackTrace();
		}
	}
}
