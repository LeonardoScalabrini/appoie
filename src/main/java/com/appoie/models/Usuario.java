package com.appoie.models;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.commands.AlterarPerfilCommand;
import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.exceptions.EmailFormatoException;
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
	
	@AttributeOverride(name="id",column=@Column(name="cidade_id"))
	private CidadeId cidadeId;
	
	private Usuario() {
		super(new UsuarioId());
	}
	
	public Usuario(SalvarUsuarioFacebookCommand facebookCommand) throws EmailFormatoException{
		this();
		this.nome = facebookCommand.nome;
		this.sobrenome = facebookCommand.sobrenome;	
		this.dataDeNascimento = facebookCommand.dataDeNascimento;
		this.sexo = Sexo.valueOf(facebookCommand.sexo);
		this.email = new Email(facebookCommand.email);		
	}
	
	public void setCidadeId(CidadeId cidadeId) {
		isNull(cidadeId);
		this.cidadeId = cidadeId;
	}


	public Usuario(CadastrarCommand command, CidadeId id) throws Exception{
		this();
		isNull(command);
		setNome(command.nome);
		setSobrenome(command.sobrenome); 
		setDataDeNascimento(command.dataDeNascimento); 
		setSexo(command.sexo); 
		setEmail(new Email(command.email)); 
		setSenha(new Senha(command.senha));
		setCidadeId(id);
	}
	
	public void alterarPerfil(AlterarPerfilCommand perfilCommand) throws Exception {
		isNull(perfilCommand);
		setNome(perfilCommand.nome);
		setSobrenome(perfilCommand.sobrenome);
		setSexo(perfilCommand.sexo);
		setDataDeNascimento(perfilCommand.dataDeNascimento);
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
	
	public void setSexo (String sexo) throws Exception{
		isNullOrEmpty(sexo);
		this.sexo = Sexo.valueOf(sexo.toUpperCase());
	}
	
	public void setSenha(Senha senha) throws Exception{
		isNull(senha);
		this.senha = senha;
	}
	
	public CidadeId getCidadeId() {
		return cidadeId;
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
