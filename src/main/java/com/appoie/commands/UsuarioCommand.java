package com.appoie.commands;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.appoie.exceptions.UsuarioException;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;
import com.appoie.utils.ValidationString;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioCommand extends ValidationString {
	private static final int TAMANHO_MINIMO = 6;

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
						  @JsonProperty(value="confirmarSenha") String confirmarSenha) throws UsuarioException {
		isNullOrEmpty(nome);		
		this.nome=nome;
		isNull(sobrenome);
		this.sobrenome=sobrenome;
		this.dataDeNascimento = dataDeNascimento;
		this.sexo = sexo;
		validaEmail(email,confirmarEmail);				
		validaSenha(senha,confirmarSenha);
		isNull(confirmarSenha);
	}
	public void validaEmail(String email,String confirmarEmail) throws UsuarioException {
		if (isNull(email)) {
			throw new UsuarioException("Erro email nulo");

		} else {

			if (email.equals(confirmarEmail)) {
				Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
				Matcher matcher = pattern.matcher(email);
				if (!matcher.find()) {
					throw new UsuarioException("Formato invalido");
				}
				this.email = email;

			} else {
				throw new UsuarioException("Email diferente");
			}
		}
		
	}

	

	public void validaSenha(String senha, String confirmarSenha) throws UsuarioException {
		if (isNull(senha)) {
			throw new UsuarioException("Campo senha é Obrigatório");
		} else {
			if (senha.length() <= TAMANHO_MINIMO) {
				throw new UsuarioException("A senha deve ter no minimo 6 caracteres");
			} else {
				if (!senha.equals(confirmarSenha)) {
					throw new UsuarioException("A senha informada não confere");					
				}
				this.senha=senha;
				
			}
		
		}

	}
	
	public Usuario criarUsuario() throws Exception{
		return new Usuario(nome, sobrenome, dataDeNascimento, sexo, new Email(email), new Senha(senha));
	}
}
