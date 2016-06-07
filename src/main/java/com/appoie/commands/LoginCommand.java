package com.appoie.commands;

import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginCommand {

	public final String email;
	public final String senha;
	
	public Email getEmail() throws Exception {
		// TODO Auto-generated method stub
		return new Email(email);
	}

	public Senha getSenha() throws Exception{
		// TODO Auto-generated method stub
		return new Senha(senha);
	}
	
	@JsonCreator
	public LoginCommand(@JsonProperty(value="email") String email, @JsonProperty(value="senha") String senha) {
		this.email = email;
		this.senha = senha;
	}

}
