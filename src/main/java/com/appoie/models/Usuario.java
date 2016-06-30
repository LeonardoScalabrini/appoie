package com.appoie.models;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.PerfilCommand;
import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;

import static com.appoie.utils.ValidationObject.*;

import static com.appoie.utils.ValidationString.*;

@Entity
public class Usuario extends BasicEntity<UsuarioId>{
	
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
	
	private CidadeId cidadeId;
	
	private Usuario() {
		super(new UsuarioId());
	}
	
	public Usuario(String nome, String sobrenome, Calendar dataDeNascimento, Sexo sexo, Email email, Senha senha, CidadeId cidadeId) throws Exception{
		this();
		setNome(nome);
		setSobrenome(sobrenome);
		setDataDeNascimento(dataDeNascimento);
		setSexo(sexo);
		setEmail(email);
		setSenha(senha);
		setCidadeId(cidadeId);
	}
	
	private void setCidadeId(CidadeId cidadeId) {
		this.cidadeId = cidadeId;
		
	}

	public Usuario(CadastrarCommand command) throws Exception{
		this();
		isNull(command);
		setNome(command.nome);
		setSobrenome(command.sobrenome); 
		setDataDeNascimento(command.dataDeNascimento); 
		setSexo(command.sexo); 
		setEmail(new Email(command.email)); 
		setSenha(new Senha(command.senha));
		setCidadeId(command.getCidadeId());
	}
	
	public void setNome(String nome) throws Exception{
		isNullOrEmpty(nome);
		this.nome = nome;
	}
	
	public void setSobrenome(String sobrenome) throws Exception{
		isNullOrEmpty(sobrenome);
		this.sobrenome = sobrenome;
	}
	
	public void setDataDeNascimento(Calendar dataDeNascimento) throws Exception{
		isNull(dataDeNascimento);
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public void setEmail (Email email) throws Exception{
		isNull(email);
		this.email = email;
	}
	
	public void setSexo (Sexo sexo) throws Exception{
		isNull(sexo);
		this.sexo = sexo;
	}
	
	public void setSenha(Senha senha) throws Exception{
		isNull(senha);
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

	public void alterarPerfil(PerfilCommand perfilCommand) throws Exception {
		setNome(perfilCommand.nome);
		setSobrenome(perfilCommand.sobrenome);
		setSexo(perfilCommand.sexo);
		setDataDeNascimento(perfilCommand.dataDeNascimento);
	}
	
}
