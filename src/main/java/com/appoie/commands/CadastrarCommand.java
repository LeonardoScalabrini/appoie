package com.appoie.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
						  	@JsonProperty(value="nascimento") Calendar dataDeNascimento,
						  	@JsonProperty(value="sexo") String sexo,
						  	@JsonProperty(value="email") String email,
						  	@JsonProperty(value="confirmaEmail") String confirmarEmail,
						  	@JsonProperty(value="senha") String senha,
						  	@JsonProperty(value="confirmaSenha") String confirmarSenha){		

		this.nome = nome;
		this.sobrenome = sobrenome; 
		this.dataDeNascimento = dataDeNascimento;
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
		this.email = email;
		this.confirmarEmail = confirmarEmail;
		this.senha = senha;
		this.confirmarSenha = confirmarSenha;
		
	}

}
