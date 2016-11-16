package com.appoie.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.CidadeId;
import com.appoie.ids.EstadoId;

import static com.appoie.utils.ValidationObject.*;
import static com.appoie.utils.ValidationString.*;

@Entity
public class Cidade extends BasicEntity<CidadeId> {

	@AttributeOverride(name = "id", column = @Column(name = "estado_id") )
	private EstadoId estadoId;
	private String nome;
	private Cidade() {
		super(new CidadeId());
	}

	public Cidade(EstadoId estadoId, String nome) {
		this();
		isNull(estadoId);
		isNullOrEmpty(nome);
		this.estadoId = estadoId;
		this.nome = nome;
	}

	public EstadoId getEstadoId() {
		return estadoId;
	}
	
	public String getNome() {
		return nome;
	}


}
