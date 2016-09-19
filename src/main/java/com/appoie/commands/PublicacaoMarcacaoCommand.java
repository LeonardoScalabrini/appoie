package com.appoie.commands;

import com.appoie.models.Categoria;

public class PublicacaoMarcacaoCommand {

	public final String idPublicacao;
	public final Double lat;
	public final Double lgt;
	public final Categoria categoria;
	public final String foto;
	
	public PublicacaoMarcacaoCommand(String idPublicacao, Double lat, Double lgt, Categoria categoria, String foto) {
		this.idPublicacao = idPublicacao;
		this.lat = lat;
		this.lgt = lgt;
		this.categoria = categoria;
		this.foto = foto;
	}
}
