package com.appoie.commands;

import java.util.ArrayList;
import java.util.List;

import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificaFechamentoPublicacaoCommand {
	public final List<PublicacaoId> idsPublicacoes ;
	
	@JsonCreator
	public VerificaFechamentoPublicacaoCommand(@JsonProperty("idPublicacao") List<PublicacaoId> idsPublicacoes) {
		
		this.idsPublicacoes = idsPublicacoes;
				
	}
	
	
}
