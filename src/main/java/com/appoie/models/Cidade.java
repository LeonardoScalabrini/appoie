package com.appoie.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.CidadeId;
import com.appoie.ids.EstadoId;

import static com.appoie.utils.ValidationObject.*;

@Entity
public class Cidade extends BasicEntity<CidadeId> {

	@AttributeOverride(name = "id", column = @Column(name = "estado_id") )
	private EstadoId estadoId;
<<<<<<< HEAD
	
	private Cidade(){
=======
	private String nome;

	private Cidade() {
>>>>>>> c689ad998e6d1dda6c9864ec9d71c77ecc5d9757
		super(new CidadeId());
	}

	public Cidade(EstadoId estadoId, String nome) {
		this();
		isNull(estadoId);
		this.estadoId = estadoId;
		this.nome = nome;
	}

	public EstadoId getEstadoId() {
		return estadoId;
	}

<<<<<<< HEAD
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
	
	
=======
	public String getNome() {
		return nome;
	}

>>>>>>> c689ad998e6d1dda6c9864ec9d71c77ecc5d9757
}
