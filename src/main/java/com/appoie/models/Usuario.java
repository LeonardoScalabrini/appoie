package com.appoie.models;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.appoie.utils.ValidationString.*;

@Entity
public class Usuario extends BasicEntity{
	
	private String nome;
	private String sobrenome;
	@Temporal(TemporalType.DATE)
	private Calendar dataDeNascimento;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@AttributeOverride(name="value",column=@Column(name="email"))
	private Email email;
	@AttributeOverride(name="value",column=@Column(name="senha"))
	private Senha senha;
	
	private Usuario() {
		super();
	}
	
	public Usuario(String nome, String sobrenome, Calendar dataDeNascimento, Sexo sexo, Email email, Senha senha) throws Exception{
		this();
		setNome(nome);
		setSobrenome(sobrenome);
		setDataDeNascimento(dataDeNascimento);
		setSexo(sexo);
		setEmail(email);
		setSenha(senha);
	}
	
	public void setNome(String nome) throws Exception{
		if (isNullOrEmpty(nome)){
			throw new IllegalArgumentException();
		}
		this.nome = nome;
	}
	
	public void setSobrenome(String sobrenome) throws Exception{
		if (isNullOrEmpty(sobrenome)){
			throw new IllegalArgumentException();
		}
		this.sobrenome = sobrenome;
	}
	
	public void setDataDeNascimento(Calendar dataDeNascimento) throws Exception{
		if (dataDeNascimento == null){
			throw new IllegalArgumentException();
		}
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public void setEmail (Email email) throws Exception{
		if (email == null){
			throw new IllegalArgumentException();
		}
		this.email = email;
	}
	
	public void setSexo (Sexo sexo) throws Exception{
		if (sexo == null){
			throw new IllegalArgumentException();
		}
		this.sexo = sexo;
	}
	
	public void setSenha(Senha senha) throws Exception{
		if (senha == null){
			throw new IllegalArgumentException();
		}
		this.senha = senha;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getSobrenome(){
		return sobrenome;
	}
	
	public Calendar getDataDeNascimento(){
		return dataDeNascimento;
	}
	
	public Sexo getSexo(){
		return sexo;
	}
	
	public Email getEmail(){
		return email;
	}
	
	public Senha getSenha(){
		return senha;
	}
	
}
