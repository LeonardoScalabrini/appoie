package com.appoie.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AutenticarUsuarioFacebookCommand {
	public final String email;

	@JsonCreator
	public AutenticarUsuarioFacebookCommand(@JsonProperty(value = "email") String email) {
		this.email = email;
	}

}
