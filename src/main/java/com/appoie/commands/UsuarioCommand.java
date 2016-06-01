package com.appoie.commands;

import java.util.Calendar;

import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioCommand {

	private String nome;
	private String sobrenome;
	
	private Calendar dataDeNascimento;
	private Sexo sexo;
	private String email;
	private String confirmarEmail;
	private String senha;
	private String confirmarSenha;
	
	@JsonCreator
	public UsuarioCommand(@JsonProperty(value="nome") String nome, 
						  @JsonProperty(value="sobrenome") String sobrenome, 
						  @JsonProperty(value="dataDeNascimento") Calendar dataDeNascimento,
						  @JsonProperty(value="sexo") Sexo sexo,
						  @JsonProperty(value="email") String email,
						  @JsonProperty(value="confirmarEmail") String confirmarEmail,
						  @JsonProperty(value="senha") String senha,
						  @JsonProperty(value="confirmarSenha") String confirmarSenha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.sexo = sexo;
		this.email = email;
		this.confirmarEmail = confirmarEmail;
		this.senha = senha;
		this.confirmarSenha = confirmarSenha;
	}
	
	public Usuario getUsuario() throws Exception{
		return new Usuario(nome, sobrenome, dataDeNascimento, sexo, new Email(email, confirmarEmail), new Senha(senha, confirmarSenha));
	}
}
