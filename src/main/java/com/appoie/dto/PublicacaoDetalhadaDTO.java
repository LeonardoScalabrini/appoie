package com.appoie.dto;

import java.util.List;

import com.appoie.commands.SalvarFotoPublicacaoCommand;
import com.appoie.models.Categoria;
import com.appoie.models.CriticidadeProblema;
import com.appoie.models.Status;
import com.appoie.utils.Value;

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
	public final String cidade;
	public final String estado;
	public final String usuario;
	
	public PublicacaoDetalhadaDTO(String idPublicacao, String titulo, String descricao, String categoria,
			String dataPublicacao, int qtdApoiadores, Status status, List<SalvarFotoPublicacaoCommand> fotos, CriticidadeProblema criticidade, String publicacaoApoiada,
			Object idApoiador, String cidade, String estado, String usuario) {
		this.idPublicacao = idPublicacao;
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataPublicacao = dataPublicacao;
		this.qtdApoiadores = qtdApoiadores;
		this.status = status;
		this.fotos = fotos;
		this.criticidade = criticidade;
		this.categoria = Categoria.valueOf(categoria).getDescricao();
		this.apoiado = publicacaoApoiada.equalsIgnoreCase("s");
		this.idApoiador = Value.StringOf(idApoiador);
		this.cidade = cidade;
		this.estado = estado;
		this.usuario = usuario;
	}

}
