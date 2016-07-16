package com.appoie.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.ids.CidadeId;
import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicacaoEditarCommand {

	public final PublicacaoId id;
	public final String titulo;
	public final String descricao;
	public final Categoria categoria;

	public PublicacaoEditarCommand(@JsonProperty("id") String id, @JsonProperty("titulo") String titulo,
			@JsonProperty("descricao") String descricao, @JsonProperty("categoria") String categoria) {
		this.id = new PublicacaoId(id);

		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = Categoria.valueOf(Categoria.class, categoria);

	}

}
