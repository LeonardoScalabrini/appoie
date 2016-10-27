package com.appoie.commands;

import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltroCommand {
	public final Calendar dataInicio;
	public final Calendar dataFim;
	public final List<String> categorias;
	public final List<String> situacoes;
	public final boolean filtrarMinhasPublicacoes;

	@JsonCreator
	public FiltroCommand(@JsonProperty("dataInicio") Calendar dataInicio, @JsonProperty("dataFim") Calendar dataFim,
			@JsonProperty("categorias") List<String> categorias, @JsonProperty("situacoes") List<String> situacoes,
			@JsonProperty("filtrarMinhasPublicacoes") boolean filtrarMinhasPublicacoes) {

		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.categorias = categorias;
		this.situacoes = situacoes;
		this.filtrarMinhasPublicacoes = filtrarMinhasPublicacoes;
	}

}
