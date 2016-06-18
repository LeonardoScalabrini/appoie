package com.appoie.models;

import java.io.IOException;
import java.util.ArrayList;

import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;

public class SenderEmail {
	private static final String key = "Y7bAzIx3pBlYk4oLWshn_Q";
	private static final String fromEmail = "time05escoladeti@gmail.com";
	private static final String fromName = "Appoie";
	private Email toEmail;
	//private String toName;
	private String subject;
	
	

	
	public SenderEmail(Email toEmail, String subject) {
		this.toEmail = toEmail;
		this.subject = subject;
	}
	/* Falta fazer integração com a mandrillAPI */	 
	 
	 
	 
	
	public void sendSenha(String senha) throws MandrillApiError, IOException {
		MandrillApi mandrillApi = new MandrillApi(key);
		
		
		MandrillMessage msg = new MandrillMessage();
		msg.setSubject(this.subject);
		msg.setHtml("<h1>ATENÇÃO!</h1><br />A senha vinculada à este email é: " + senha);
		msg.setAutoText(true);
		msg.setFromEmail(this.fromEmail.toString());
		msg.setFromName(this.fromName.toString());
		// add recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail(this.toEmail.toString());
		//recipient.setName(this.toName);
		recipients.add(recipient);
		
		
		msg.setPreserveRecipients(true);
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Recuperação de senha");
		msg.setTags(tags);
		
		MandrillMessageStatus[] messageStatusReports = mandrillApi
		        .messages().send(msg, false);
		
		
	}
	
	public void sendConfirmacaoCadastro(String conteudoEmail) throws MandrillApiError, IOException {
		MandrillApi mandrillApi = new MandrillApi(key);
		
		
		MandrillMessage msg = new MandrillMessage();
		msg.setSubject(this.subject);
		msg.setHtml(conteudoEmail);
		msg.setAutoText(true);
		msg.setFromEmail(this.fromEmail.toString());
		msg.setFromName(this.fromName.toString());
		// add recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail(this.toEmail.toString());
		//recipient.setName(this.toName);
		recipients.add(recipient);
		
		
		msg.setPreserveRecipients(true);
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Confirmação de cadastro");
		msg.setTags(tags);
		
		MandrillMessageStatus[] messageStatusReports = mandrillApi
		        .messages().send(msg, false);
		
		
	}
	
	public void sendAlteracaoSenhaCadastro(String conteudoEmail) throws MandrillApiError, IOException {
		MandrillApi mandrillApi = new MandrillApi(key);
		
		
		MandrillMessage msg = new MandrillMessage();
		msg.setSubject(this.subject);
		msg.setHtml(conteudoEmail);
		msg.setAutoText(true);
		msg.setFromEmail(this.fromEmail);
		msg.setFromName(this.fromName);
		// add recipients
		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail(this.toEmail.toString());
		//recipient.setName(this.toName);
		recipients.add(recipient);
		
		
		msg.setPreserveRecipients(true);
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Alteração senha");
		msg.setTags(tags);
		
		MandrillMessageStatus[] messageStatusReports = mandrillApi
		        .messages().send(msg, false);
		
		
	}
	

}
