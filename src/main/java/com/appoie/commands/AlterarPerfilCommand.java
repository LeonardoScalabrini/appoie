package com.appoie.commands;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlterarPerfilCommand {

	public final String idUsuario;
	public final String nome;
	public final String sobrenome;
	public final String sexo;
	public final Calendar dataDeNascimento;
	public final String foto;
	public final String cep;
	public final String cidade;
	public final String estado;
	
	@JsonCreator
	public AlterarPerfilCommand(@JsonProperty("idUsuario") String idUsuario,
						 @JsonProperty("nome") String nome,
					 	 @JsonProperty("sobrenome") String sobrenome,
					 	 @JsonProperty("sexo") String sexo,
					 	 @JsonProperty("cep") String cep,
					 	 @JsonProperty("cidade") String cidade,
					 	 @JsonProperty("estado") String estado,
					 	 @JsonProperty("dataDeNascimento") Calendar dataDeNascimento,
					 	 @JsonProperty("foto") String foto){
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = sexo;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.dataDeNascimento = dataDeNascimento;
		this.foto = foto;
	}

}
