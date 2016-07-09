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

<<<<<<< HEAD
	public String getNomeCidade() {
		return nomeCidade;
	}

	
	
	

=======
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
	
	
>>>>>>> c72c8b950a938951549416e14a65af3c8d072e39
}
