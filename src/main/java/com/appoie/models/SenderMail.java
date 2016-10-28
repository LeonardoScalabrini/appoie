package com.appoie.models;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SenderMail {

	private static final String username = "contato.appoie@gmail.com";
	private static final String password = "escoladeti@2016";
	private Session session;

	
	public void configure() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		this.session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

	}

	public void sendMail(String mailBody, String subject, List<Email> destinatarios) {
		configure();
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			for (Email email : destinatarios) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getValue()));
			}

			message.setSubject(subject);
			message.setContent(mailBody, "text/html");
			//message.setText(mailBody);
			Transport.send(message);

			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
