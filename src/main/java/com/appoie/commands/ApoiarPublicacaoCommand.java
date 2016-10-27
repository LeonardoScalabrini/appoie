package com.appoie.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApoiarPublicacaoCommand {
	public final String idPublicacao;
	public final String idApoiador;
	
	@JsonCreator
	public ApoiarPublicacaoCommand(@JsonProperty("idPublicacao") String idPublicacao, @JsonProperty("idApoiador") String idApoiador) {
		this.idPublicacao = idPublicacao;
		this.idApoiador = idApoiador;		
	}

}
