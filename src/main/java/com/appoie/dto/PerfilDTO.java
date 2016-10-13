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
	public final Calendar dataDeNascimento;
<<<<<<< HEAD:src/main/java/com/appoie/commands/PerfilCommand.java
	public final byte[] foto;
	
	public PerfilCommand(Usuario usuario, FotoPerfil fotoPerfil) {
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.sexo = usuario.getSexo();
		this.dataDeNascimento = usuario.getDataDeNascimento();
		this.foto = fotoPerfil.foto;
	}
=======
	public final String foto;
	public final String cep;
	public final String cidade;
	public final String estado;
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f:src/main/java/com/appoie/dto/PerfilDTO.java
	
	@JsonCreator
	public PerfilDTO(@JsonProperty("idUsuario") String idUsuario,
						 @JsonProperty("nome") String nome,
					 	 @JsonProperty("sobrenome") String sobrenome,
					 	 @JsonProperty("sexo") String sexo,
					 	 @JsonProperty("cep") String cep,
					 	 @JsonProperty("cidade") String cidade,
					 	 @JsonProperty("estado") String estado,
					 	 @JsonProperty("dataDeNascimento") Calendar dataDeNascimento,
<<<<<<< HEAD:src/main/java/com/appoie/commands/PerfilCommand.java
					 	 @JsonProperty("foto") byte[] foto){
=======
					 	 @JsonProperty("foto") String foto){
		this.idUsuario = idUsuario;
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f:src/main/java/com/appoie/dto/PerfilDTO.java
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.dataDeNascimento = dataDeNascimento;
		this.foto = foto;
		
	}

}
