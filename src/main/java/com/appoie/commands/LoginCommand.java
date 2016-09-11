package com.appoie.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginCommand {

	public final String email;
	public final String senha;
	
	@JsonCreator
	public LoginCommand(@JsonProperty(value="email") String email, @JsonProperty(value="senha") String senha) {
		this.email = email;
		this.senha = senha;
	}

}
