package com.appoie.commands;

import java.util.List;

import com.appoie.models.Status;

public class PublicacaoDetalhadaCommand {

	public final String idPublicacao;
	public final String titulo;
	public final String descricao;
	public final String dataPublicacao;
	public final int qtdApoiadores; 
	public final Status status;
	public final List<FotoPublicacaoCommand> fotos;
	
	public PublicacaoDetalhadaCommand(String idPublicacao, String titulo, String descricao, String categoria,
			String dataPublicacao, int qtdApoiadores, Status status, List<FotoPublicacaoCommand> fotos) {
		this.idPublicacao = idPublicacao;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		this.qtdApoiadores = qtdApoiadores;
		this.status = status;
		this.fotos = fotos;
	}

}
