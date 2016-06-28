package com.appoie.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.CidadeId;
import com.appoie.ids.EstadoId;

import static com.appoie.utils.ValidationObject.*;

@Entity
public class Cidade extends BasicEntity<CidadeId>{
	
	@AttributeOverride(name="id",column=@Column(name="estado_id"))
	private EstadoId estadoId;
	
	private Cidade(){
		super(new CidadeId());
	}
	
	public Cidade(EstadoId estadoId){
		this();
		isNull(estadoId);
		this.estadoId = estadoId;
	}
	
	public EstadoId getEstadoId(){
		return estadoId;
	}

}
