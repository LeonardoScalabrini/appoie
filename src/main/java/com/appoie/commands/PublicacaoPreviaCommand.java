package com.appoie.commands;

import com.appoie.models.Status;

public class PublicacaoPreviaCommand {

	public final String idPublicacao;
	public final String titulo;
	public final Long qtdApoiadores;
	public final Status status;
	public final String foto;

	public PublicacaoPreviaCommand(String idPublicacao, String titulo, long qtdApoiadores, Status status, String foto) {
		this.idPublicacao = idPublicacao;
		this.titulo = titulo;
		this.qtdApoiadores = qtdApoiadores;
		this.status = status;
		this.foto = foto;
	}
}
