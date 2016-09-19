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
	public final String estado;
	public final String cidade;
	public final Double lat;
	public final Double lng;

	@JsonCreator
	public PublicacaoCommand(@JsonProperty(value = "titulo") String titulo,
							 @JsonProperty(value = "descricao") String descricao, 
							 @JsonProperty(value = "categoria") String categoria,
							 @JsonProperty(value = "cidade") String cidade, 
							 @JsonProperty(value = "estado") String estado, 
							 @JsonProperty(value = "lat") Double lat, 
							 @JsonProperty(value = "lng") Double lng, 
							 @JsonProperty(value = "fotos") List<String> fotos) {

		this.titulo = titulo;
		this.descricao = descricao;
		this.cidade = cidade;
		this.estado = estado;
		this.lat = lat;
		this.lng = lng;
		this.categoria = Categoria.valueOf(categoria.toUpperCase());
		this.fotos = fotos;
	}

}
