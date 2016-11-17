package com.appoie.dto;

import java.util.List;

import com.appoie.commands.SalvarFotoPublicacaoCommand;
import com.appoie.models.CriticidadeProblema;
import com.appoie.models.Status;

public class PublicacaoDetalhadaDTO {

	public final String idPublicacao;
	public final String titulo;
	public final String descricao;
	public final String dataPublicacao;
	public final int qtdApoiadores; 
	public final Status status;
	public final List<SalvarFotoPublicacaoCommand> fotos;
	public final CriticidadeProblema criticidade;
	public final boolean apoiado;
	public final String idApoiador;
	public final String categoria;
	
	public PublicacaoDetalhadaDTO(String idPublicacao, String titulo, String descricao, String categoria,
			String dataPublicacao, int qtdApoiadores, Status status, List<SalvarFotoPublicacaoCommand> fotos, CriticidadeProblema criticidade, String publicacaoApoiada,
			Object idApoiador) {
		this.idPublicacao = idPublicacao;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		this.qtdApoiadores = qtdApoiadores;
		this.status = status;
		this.fotos = fotos;
		this.criticidade = criticidade;
		this.categoria = categoria;
		this.apoiado = publicacaoApoiada.equalsIgnoreCase("s");
		if (idApoiador == null)
			this.idApoiador = "";
		else
			this.idApoiador = idApoiador.toString();
	}

}
