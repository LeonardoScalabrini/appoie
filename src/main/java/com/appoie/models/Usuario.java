package com.appoie.models;

import static com.appoie.utils.ValidationObject.isNull;
import static com.appoie.utils.ValidationString.isNullOrEmpty;

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
import com.appoie.ids.UsuarioId;

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
	
	private Usuario() {
		super(new UsuarioId());
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

	public Usuario(CadastrarCommand command) throws Exception{
		this();
		isNull(command);
		setNome(command.nome);
		setSobrenome(command.sobrenome); 
		setDataDeNascimento(command.dataDeNascimento); 
		setSexo(command.sexo); 
		setEmail(new Email(command.email)); 
		setSenha(new Senha(command.senha));
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataDeNascimento == null) ? 0 : dataDeNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (dataDeNascimento == null) {
			if (other.dataDeNascimento != null)
				return false;
		} else
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (sexo != other.sexo)
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}
	
}
