package com.appoie.commands;

import java.util.Calendar;
import java.util.List;

import com.appoie.ids.CidadeId;
import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Categoria;
import com.appoie.models.FotoPublicacao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicacaoCommand {
	
	public final CidadeId cidadeId;
	public final String titulo;
	public final String descricao;
	public final Categoria categoria;
	public final Calendar dataPublicação;
	public final List<byte[]> fotos;

	@JsonCreator
	public PublicacaoCommand(@JsonProperty(value = "cidadeId") CidadeId cidadeId,
			@JsonProperty(value = "titulo") String titulo,
			@JsonProperty(value = "descricao") String descricao, @JsonProperty(value = "categoria") Categoria categoria,
			@JsonProperty(value = "dataPublicacao") Calendar dataPublicacao,
			@JsonProperty(value = "fotos") List<byte[]> fotos) {

		
		this.cidadeId = cidadeId;
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = Categoria.valueOf(categoria.toString());
		this.dataPublicação = dataPublicacao;
		this.fotos = fotos;
	}

}
