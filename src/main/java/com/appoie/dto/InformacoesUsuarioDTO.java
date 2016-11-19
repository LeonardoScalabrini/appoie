package com.appoie.dto;

public class InformacoesUsuarioDTO {
	public final String nome;
	public final String sobrenome;
	public final String email;
	public final String cidade;
	public final String estado;
	public final boolean primeiroAcesso;
	public final String dataDeNascimento;
	public final String sexo;
	
	public InformacoesUsuarioDTO(String nome, String sobrenome, String email, String cidade, String estado, boolean acesso, String dataNascimento, String sexo) {		
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.cidade = cidade;
		this.estado = estado;
		this.primeiroAcesso = acesso;
		this.dataDeNascimento = dataNascimento;
		this.sexo = sexo;
	}

}
