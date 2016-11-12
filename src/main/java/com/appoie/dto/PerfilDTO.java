package com.appoie.dto;

import java.util.Calendar;

import com.appoie.models.Sexo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PerfilDTO {

	public final String idUsuario;
	public final String nome;
	public final String sobrenome;
	public final Sexo sexo;
	public final String dataDeNascimento;
	
	@JsonCreator
	public PerfilDTO(@JsonProperty("idUsuario") String idUsuario,
						 @JsonProperty("nome") String nome,
					 	 @JsonProperty("sobrenome") String sobrenome,
					 	 @JsonProperty("sexo") String sexo,
					 	 @JsonProperty("dataDeNascimento") String dataDeNascimento){
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
		this.dataDeNascimento = dataDeNascimento;
		
	}

}
