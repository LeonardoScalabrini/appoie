package com.appoie.builders;

import com.appoie.exceptions.EmailFormatoException;
import com.appoie.models.Email;

public class EmailBuilder {

	private String email = "teste@teste.com.br";
	
	public EmailBuilder email(String email){
		this.email = email;
		return this;
	}
	
	public Email criar() throws EmailFormatoException{
		return new Email(email);
	}
}
