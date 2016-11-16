package com.appoie.commands;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificaFechamentoPublicacaoCommand {
	public final List<String> idsPublicacoes ;
	
	@JsonCreator
	public VerificaFechamentoPublicacaoCommand(@JsonProperty("idsPublicacoes") List<String> idsPublicacoes) {
		
		this.idsPublicacoes = idsPublicacoes;
				
	}
	
	
}
