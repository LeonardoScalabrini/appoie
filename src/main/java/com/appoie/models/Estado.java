package com.appoie.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.appoie.ids.CidadeId;
import com.appoie.ids.EstadoId;
import static com.appoie.utils.ValidationObject.*;

@Entity
public class Estado extends BasicEntity<EstadoId>{
	@AttributeOverride(name="estado",column=@Column(name="nome_estado"))
	private String nomeEstado;
	@ElementCollection
	@CollectionTable(name="Cidade",joinColumns=@JoinColumn(name="estado_id"))
	private List<CidadeId> cidades = new ArrayList<>(); 
	
	Estado() {
		super(new EstadoId());
	}
	
	public Estado(CidadeId cidadeId){
		this();
		isNull(cidadeId);
		this.nomeEstado=nomeEstado;
		this.cidades.add(cidadeId);
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public List<CidadeId> getCidades() {
		return cidades;
	}
	

}
