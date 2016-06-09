package com.appoie.commands;

import com.appoie.models.Email;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecuperarSenhaCommand {
	public final String email;

	public RecuperarSenhaCommand(@JsonProperty(value = "email") String email) {
		this.email = email;

	}

	public String getEmail() {

		return this.email;
	}
}
