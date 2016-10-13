package com.appoie.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecuperarSenhaCommand {
	public final String email;

	@JsonCreator
	public RecuperarSenhaCommand(@JsonProperty(value = "email") String email) {
		this.email = email;
	}
}
