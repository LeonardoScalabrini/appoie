package com.appoie.commands;

import java.util.Calendar;
import com.appoie.models.Sexo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CadastrarCommand{

	public final String nome;
	public final String sobrenome;	
	public final Calendar dataDeNascimento;
	public final Sexo sexo;
	public final String email;
	public final String confirmarEmail;
	public final String senha;
	public final String confirmarSenha;
	
	@JsonCreator
	public CadastrarCommand(@JsonProperty(value="nome") String nome, 
						  @JsonProperty(value="sobrenome") String sobrenome, 
						  @JsonProperty(value="dataDeNascimento") Calendar dataDeNascimento,
						  @JsonProperty(value="sexo") Sexo sexo,
						  @JsonProperty(value="email") String email,
						  @JsonProperty(value="confirmarEmail") String confirmarEmail,
						  @JsonProperty(value="senha") String senha,
						  @JsonProperty(value="confirmarSenha") String confirmarSenha){		

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.sexo = sexo;
		this.email = email;
		this.confirmarEmail = confirmarEmail;
		this.senha = senha;
		this.confirmarSenha = confirmarSenha;
		
	}

}
