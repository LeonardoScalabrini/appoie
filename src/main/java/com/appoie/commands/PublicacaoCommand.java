package com.appoie.commands;

import java.util.List;

import com.appoie.models.Categoria;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicacaoCommand {

	public final String titulo;
	public final String descricao;
	public final Categoria categoria;
	public final List<String> fotos;

	@JsonCreator
	public PublicacaoCommand(@JsonProperty(value = "titulo") String titulo,
			@JsonProperty(value = "descricao") String descricao, @JsonProperty(value = "categoria") String categoria,
			@JsonProperty(value = "fotos") List<String> fotos) {

		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = Categoria.valueOf(categoria);
		this.fotos = fotos;
	}

}
