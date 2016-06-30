package com.appoie.biulders;

import java.util.Calendar;

import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.CidadeId;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;

public class UsuarioBiulder {
	
	private String nome;
	private String sobrenome;
	private Calendar dataDeNascimento;
	private Sexo sexo;
	private Email email;
	private Senha senha;
	private CidadeId cidadeId;
	
	public UsuarioBiulder() throws EmailFormatoException, SenhaTamanhoMinimoException {
		nome = "teste"; 
		sobrenome = "teste";
		dataDeNascimento = Calendar.getInstance();
		sexo = Sexo.MASCULINO;
		email = new Email("teste@teste.com.br");
		senha = new Senha("123456");
		cidadeId = new CidadeId();
		
	}
	
	public Usuario criar() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		return new Usuario(nome, sobrenome, dataDeNascimento, sexo, email, senha, cidadeId);
	}
	
	public UsuarioBiulder nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public UsuarioBiulder sobrenome(String sobrenome){
		this.sobrenome = sobrenome;
		return this;
	}
	
	public UsuarioBiulder dataDeNascimento(Calendar dataDeNascimento){
		this.dataDeNascimento = dataDeNascimento;
		return this;
	}
	
	public UsuarioBiulder sexo(Sexo sexo){
		this.sexo = sexo;
		return this;
	}
	
	public UsuarioBiulder email(Email email){
		this.email = email;
		return this;
	}
	
	public UsuarioBiulder senha(Senha senha){
		this.senha = senha;
		return this;
	}
}
