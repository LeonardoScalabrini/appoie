package com.appoie.commands;

import java.util.Calendar;

import com.appoie.ids.CidadeId;
import com.appoie.models.Sexo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CadastrarCommand {

	public final String nome;
	public final String sobrenome;
	public final Calendar dataDeNascimento;
	public final Sexo sexo;
	public final String email;
	public final String confirmarEmail;
	public final String senha;
	public final String confirmarSenha;
	public final String cidade;
	private CidadeId cidadeId;

	@JsonCreator
	public CadastrarCommand(@JsonProperty(value = "nome") String nome,
			@JsonProperty(value = "sobrenome") String sobrenome,
			@JsonProperty(value = "nascimento") Calendar dataDeNascimento, @JsonProperty(value = "sexo") String sexo,
			@JsonProperty(value = "email") String email, @JsonProperty(value = "confirmaEmail") String confirmarEmail,
			@JsonProperty(value = "senha") String senha, @JsonProperty(value = "confirmaSenha") String confirmarSenha,
			@JsonProperty(value = "cidade") String cidade) {

		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
		this.email = email;
		this.confirmarEmail = confirmarEmail;
		this.senha = senha;
		this.confirmarSenha = confirmarSenha;
		this.cidade = cidade;

	}
	public void setCidadeId(CidadeId cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	public CidadeId getCidadeId() {
		return this.cidadeId;
	}

}
