package com.appoie.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SenhaCommand {

	public final String senhaAtual;
	public final String novaSenha;
	public final String confirmarNovaSenha;
	
	@JsonCreator
	public SenhaCommand(@JsonProperty("senhaAtual") String senhaAtual,
						@JsonProperty("novaSenha") String novaSenha,
						@JsonProperty("confirmarNovaSenha") String confirmarNovaSenha) {
	
		this.senhaAtual = senhaAtual;
		this.novaSenha = novaSenha;
		this.confirmarNovaSenha = confirmarNovaSenha;
	}
}
