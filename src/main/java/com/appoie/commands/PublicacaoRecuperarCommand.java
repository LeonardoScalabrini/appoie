package com.appoie.commands;

import java.util.Calendar;
import java.util.List;

import com.appoie.models.Categoria;
import com.appoie.models.Publicacao;

public class PublicacaoRecuperarCommand {
	public final String id;
	public final String titulo;
	//public final String usuarioId;
	//public final String cidadeId;
	public final String descricao;
	public final Categoria categoria;
	public final Calendar dataPublicação;
	public final List<String> fotos;

	public PublicacaoRecuperarCommand(Publicacao publicacao, List<String> fotos) {
		// TODO Auto-generated constructor stub
		this.id = publicacao.getId().getValue();
		//this.usuarioId = publicacao.getUsuarioId().toString();
		//this.cidadeId = publicacao.getCidadeId().toString();
		this.titulo = publicacao.getTitulo();
		this.descricao = publicacao.getDescricao();
		this.categoria = publicacao.getCategoria();
		this.dataPublicação = publicacao.getDataPublicacao();
		this.fotos = fotos;

	}

}
