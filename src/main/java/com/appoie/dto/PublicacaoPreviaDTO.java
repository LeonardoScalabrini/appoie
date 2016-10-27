package com.appoie.dto;

import com.appoie.models.Status;

public class PublicacaoPreviaDTO {

	public final String idPublicacao;
	public final String titulo;
	public final Long qtdApoiadores;
	public final Status status;
	public final String foto;
	public final boolean apoiado;
	public final String idApoiado;

	public PublicacaoPreviaDTO(String idPublicacao, String titulo, long qtdApoiadores, Status status, String foto,
			String publicacaoApoiada, Object idApoiado) {
		this.idPublicacao = idPublicacao;
		this.titulo = titulo;
		this.qtdApoiadores = qtdApoiadores;
		this.status = status;
		this.foto = foto;
		this.apoiado = publicacaoApoiada.equalsIgnoreCase("s");
		if (idApoiado == null)
			this.idApoiado = "";
		else
			this.idApoiado = idApoiado.toString();
	}
}
