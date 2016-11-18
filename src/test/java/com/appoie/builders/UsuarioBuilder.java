package com.appoie.builders;

import java.util.Calendar;

import com.appoie.commands.AlterarPerfilCommand;
import com.appoie.commands.CadastrarCommand;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.CidadeId;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;

public class UsuarioBuilder {
	
	private String nome;
	private String sobrenome;
	private Calendar dataDeNascimento;
	private String sexo;
	private String email;
	private String senha;
	private CidadeId cidadeId;
	private String cidade;
	private String cep;
	private String confirmarEmail;
	private String estado;
	private String confirmarSenha;
	private String foto;
	
	public UsuarioBuilder() throws EmailFormatoException, SenhaTamanhoMinimoException {
		nome = "teste"; 
		sobrenome = "teste";
		dataDeNascimento = Calendar.getInstance();
		sexo = Sexo.MASCULINO.name();
		email = "teste@teste.com.br";
		senha = "123456";
		cidadeId = new CidadeId("CIDADE_ID");
		
	}
	
	public Usuario cadastrar() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		CadastrarCommand cadastrarCommand = new CadastrarCommand(nome, sobrenome, sexo, cep, cidade, estado, dataDeNascimento, email, confirmarEmail, senha, confirmarSenha);
		return new Usuario(cadastrarCommand, cidadeId);
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
	
	public UsuarioBuilder sexo(String sexo){
		this.sexo = sexo;
		return this;
	}
	
	public UsuarioBuilder email(String email){
		this.email = email;
		return this;
	}
	
	public UsuarioBuilder senha(String senha){
		this.senha = senha;
		return this;
	}
	
	public UsuarioBuilder cidadeId(CidadeId cidadeId){
		this.cidadeId = cidadeId;
		return this;
	}
	
	public UsuarioBuilder alterarPerfil(){
		this.nome = "nomeAlterado";
		this.sobrenome = "sobrenomeAlterado";
		this.sexo = "Masculino";
		this.cidade = "cidadeAlterada";
		this.estado = "estadoAlterado";
		this.foto = "foto";
		this.dataDeNascimento = Calendar.getInstance();
		return this;
	}
	
	public AlterarPerfilCommand getAlterarPerfilCommand(){
		return new AlterarPerfilCommand("USUARIO_ID", nome, sobrenome, sexo, cep, cidade, estado, dataDeNascimento, foto);
	}
}
