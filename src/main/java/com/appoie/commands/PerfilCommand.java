package com.appoie.commands;

import java.util.Calendar;

import com.appoie.models.Sexo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PerfilCommand {

	public final String nome;
	public final String sobrenome;
	public final Sexo sexo;
	public final Calendar dataDeNascimento;
	public final String foto;
	
	@JsonCreator
	public PerfilCommand(@JsonProperty("nome") String nome,
					 	 @JsonProperty("sobrenome") String sobrenome,
					 	 @JsonProperty("sexo") String sexo,
					 	 @JsonProperty("dataDeNascimento") Calendar dataDeNascimento,
					 	 @JsonProperty("foto") String foto){
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
		this.dataDeNascimento = dataDeNascimento;
		this.foto = foto;
		
	}

}
