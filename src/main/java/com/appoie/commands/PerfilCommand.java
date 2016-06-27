package com.appoie.commands;

import java.util.Calendar;

import com.appoie.models.FotoPerfil;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PerfilCommand {

	public final String nome;
	public final String sobrenome;
	public final Sexo sexo;
	public final Calendar dataDeNascimento;
	public final byte[] foto;
	
	public PerfilCommand(Usuario usuario, FotoPerfil fotoPerfil) {
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.sexo = usuario.getSexo();
		this.dataDeNascimento = usuario.getDataDeNascimento();
		this.foto = fotoPerfil.foto;
	}
	
	@JsonCreator
	public PerfilCommand(@JsonProperty("nome") String nome,
					 	 @JsonProperty("sobrenome") String sobrenome,
					 	 @JsonProperty("sexo") String sexo,
					 	 @JsonProperty("dataDeNascimento") Calendar dataDeNascimento,
					 	 @JsonProperty("foto") byte[] foto){
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
		this.dataDeNascimento = dataDeNascimento;
		this.foto = foto;
		
	}

}
