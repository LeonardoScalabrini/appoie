package com.appoie.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SenhaRecuperarCommand {
	public final String email;

	@JsonCreator
	public SenhaRecuperarCommand(@JsonProperty(value = "email") String email) {
		this.email = email;
	}
}
