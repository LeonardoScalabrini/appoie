package com.appoie.biulders;

import com.appoie.exceptions.EmailFormatoException;
import com.appoie.models.Email;

public class EmailBiulder {

	private String email = "teste@teste.com.br";
	
	public EmailBiulder email(String email){
		this.email = email;
		return this;
	}
	
	public Email criar() throws EmailFormatoException{
		return new Email(email);
	}
}
