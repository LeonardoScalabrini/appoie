package com.appoie.commands;

import java.util.List;

import com.appoie.models.CriticidadeProblema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalvarPublicacaoCommand {

	public final String titulo;
	public final String descricao;
	public final String categoria;
	public final String foto;
	public final String estado;
	public final String cidade;
	public final Double lat;
	public final Double lng;
	public final CriticidadeProblema criticidade;

	@JsonCreator
	public SalvarPublicacaoCommand(@JsonProperty(value = "titulo") String titulo,
							 @JsonProperty(value = "descricao") String descricao, 
							 @JsonProperty(value = "categoria") String categoria,
							 @JsonProperty(value = "cidade") String cidade, 
							 @JsonProperty(value = "estado") String estado, 
							 @JsonProperty(value = "lat") Double lat, 
							 @JsonProperty(value = "lng") Double lng, 
							 @JsonProperty(value = "fotos") List<String> foto,
							 @JsonProperty(value = "criticidade") CriticidadeProblema criticitade) {

		this.titulo = titulo;
		this.descricao = descricao;
		this.cidade = cidade;
		this.estado = estado;
		this.lat = lat;
		this.lng = lng;
		this.categoria = categoria;
		this.foto = foto.get(0);
		this.criticidade = criticitade;
	}

}
