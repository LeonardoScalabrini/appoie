package com.appoie.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.appoie.ids.CidadeId;
import com.appoie.ids.EstadoId;
import static com.appoie.utils.ValidationObject.*;

@Entity
public class Estado extends BasicEntity<EstadoId>{
	
	@ElementCollection
	@CollectionTable(name="Cidade",joinColumns=@JoinColumn(name="estado_id"))
	private List<CidadeId> cidades = new ArrayList<>(); 
	
	private Estado() {
		super(new EstadoId());
	}
	
	public Estado(CidadeId cidadeId){
		this();
		isNull(cidadeId);
		this.cidades.add(cidadeId);
	}

}
