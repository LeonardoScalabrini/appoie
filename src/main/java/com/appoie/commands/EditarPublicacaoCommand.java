package com.appoie.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditarPublicacaoCommand {

	public final String id;
	public final String titulo;
	public final String descricao;
	public final String categoria;

	public EditarPublicacaoCommand(@JsonProperty("id") String id, @JsonProperty("titulo") String titulo,
			@JsonProperty("descricao") String descricao, @JsonProperty("categoria") String categoria) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
	}

}
