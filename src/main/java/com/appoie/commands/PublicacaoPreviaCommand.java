package com.appoie.commands;

public class PublicacaoPreviaCommand {

	public final String idPublicacao;
	public final String titulo;
	public final int qtdeApoiadores;
	public final String status;
	public final String foto;

	public PublicacaoPreviaCommand(String idPublicacao, String titulo, int qtdeApoiadores, String status, String foto) {
		this.idPublicacao = idPublicacao;
		this.titulo = titulo;
		this.qtdeApoiadores = qtdeApoiadores;
		this.status = status;
		this.foto = foto;
	}

}
