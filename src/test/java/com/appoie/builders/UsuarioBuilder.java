package com.appoie.builders;

import java.util.Calendar;

import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;

public class UsuarioBuilder {
	
	private String nome;
	private String sobrenome;
	private Calendar dataDeNascimento;
	private Sexo sexo;
	private Email email;
	private Senha senha;
	
	public UsuarioBuilder() throws EmailFormatoException, SenhaTamanhoMinimoException {
		nome = "teste"; 
		sobrenome = "teste";
		dataDeNascimento = Calendar.getInstance();
		sexo = Sexo.MASCULINO;
		email = new Email("teste@teste.com.br");
		senha = new Senha("123456");
	}
	
	public Usuario criar() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		return new Usuario(nome, sobrenome, dataDeNascimento, sexo, email, senha);
	}
	
	public UsuarioBuilder nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public UsuarioBuilder sobrenome(String sobrenome){
		this.sobrenome = sobrenome;
		return this;
	}
	
	public UsuarioBuilder dataDeNascimento(Calendar dataDeNascimento){
		this.dataDeNascimento = dataDeNascimento;
		return this;
	}
	
	public UsuarioBuilder sexo(Sexo sexo){
		this.sexo = sexo;
		return this;
	}
	
	public UsuarioBuilder email(Email email){
		this.email = email;
		return this;
	}
	
	public UsuarioBuilder senha(Senha senha){
		this.senha = senha;
		return this;
	}
}
