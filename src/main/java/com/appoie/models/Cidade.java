package com.appoie.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.appoie.ids.CidadeId;
import static com.appoie.utils.ValidationObject.*;

@Entity
public class Cidade extends BasicEntity<CidadeId>{
	
    @ManyToOne
    @JoinColumn(name="estado_id", referencedColumnName="id")
	private Estado estado;
	
	private Cidade() {
		super(new CidadeId());
	}
	
	public Cidade(Estado estado){
		this();
		isNull(estado);
		this.estado = estado;
	}
	
	public Estado getEstadoId(){
		return estado;
	}

}
