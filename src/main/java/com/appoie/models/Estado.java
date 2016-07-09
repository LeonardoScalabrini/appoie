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

<<<<<<< HEAD
	public String getNomeEstado() {
		return nomeEstado;
	}

	public List<CidadeId> getCidades() {
		return cidades;
	}
	

=======
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cidades == null) ? 0 : cidades.hashCode());
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
		Estado other = (Estado) obj;
		if (cidades == null) {
			if (other.cidades != null)
				return false;
		} else if (!cidades.equals(other.cidades))
			return false;
		return true;
	}

	
>>>>>>> c72c8b950a938951549416e14a65af3c8d072e39
}
