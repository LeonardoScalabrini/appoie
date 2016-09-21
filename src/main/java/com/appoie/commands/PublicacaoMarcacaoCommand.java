package com.appoie.commands;

import com.appoie.models.Categoria;

public class PublicacaoMarcacaoCommand {

	public final String idPublicacao;
	public final Double lat;
	public final Double lng;
	public final Categoria categoria;
	public final Long qtdApoiadores;
	
	public PublicacaoMarcacaoCommand(String idPublicacao, Double lat, Double lng, Categoria categoria, Long qtdCurtidas) {
		this.idPublicacao = idPublicacao;
		this.lat = lat;
		this.lng = lng;
		this.categoria = categoria;
		this.qtdApoiadores = qtdCurtidas;
	}
}
