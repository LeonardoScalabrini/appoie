package com.appoie.models;

import static com.appoie.utils.ValidationObject.isNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.CidadeId;
import com.appoie.ids.EstadoId;

@Entity
public class Cidade extends BasicEntity<CidadeId>{
	@AttributeOverride(name="cidade",column=@Column(name="nome_cidade"))
	private String nomeCidade;	
	@AttributeOverride(name="id",column=@Column(name="estado_id"))
	private EstadoId estadoId;
	
	Cidade(){
		super(new CidadeId());
	}
	
	public Cidade(EstadoId estadoId){
		this();
		isNull(estadoId);
		this.estadoId = estadoId;
		//this.nomeCidade=nomeCidade;
	}
	
	public EstadoId getEstadoId(){
		return estadoId;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	
	
	

}
