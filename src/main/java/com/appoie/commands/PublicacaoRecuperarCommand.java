package com.appoie.commands;

import java.util.List;

import com.appoie.models.Categoria;
import com.appoie.models.Publicacao;

public class PublicacaoRecuperarCommand {
	public final String id;
	public final String titulo;
	public final String descricao;
	public final Categoria categoria;
	public final String dataPublicacao;
	public final List<String> fotos;
	public final int numFotos;

	public PublicacaoRecuperarCommand(Publicacao publicacao, List<String> fotos) {
		this.id = publicacao.getId().getValue();
		this.titulo = publicacao.getTitulo();
		this.descricao = publicacao.getDescricao();
		this.categoria = publicacao.getCategoria();
		this.dataPublicacao = publicacao.getDataPublicacao().toString();
		this.fotos = fotos;
		this.numFotos = fotos.size();

	}

}
