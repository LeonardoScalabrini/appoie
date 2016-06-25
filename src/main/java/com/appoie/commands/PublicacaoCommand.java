package com.appoie.commands;

import java.util.Calendar;

import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Categoria;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicacaoCommand {
	public final UsuarioId usuarioId;
	public final CidadeId cidadeId;
	public final String titulo;
	public final String descricao;
	public final Categoria categoria;
	public final Calendar dataPublicação;

	@JsonCreator
	public PublicacaoCommand(@JsonProperty(value = "usuarioId") UsuarioId usuarioId,
			@JsonProperty(value = "cidadeId") CidadeId cidadeId, @JsonProperty(value = "titulo") String titulo,
			@JsonProperty(value = "descricao") String descricao, @JsonProperty(value = "categoria") Categoria categoria,
			@JsonProperty(value = "dataPublicacao") Calendar dataPublicacao) {

		this.usuarioId = usuarioId;
		this.cidadeId = cidadeId;
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = Categoria.valueOf(categoria.toString());
		this.dataPublicação = dataPublicacao;
	}

}
