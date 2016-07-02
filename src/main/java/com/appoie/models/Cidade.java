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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((estadoId == null) ? 0 : estadoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (estadoId == null) {
			if (other.estadoId != null)
				return false;
		} else if (!estadoId.equals(other.estadoId))
			return false;
		return true;
	}
	
	
}
