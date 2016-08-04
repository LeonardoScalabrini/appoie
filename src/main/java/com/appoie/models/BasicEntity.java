package com.appoie.models;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.appoie.ids.BasicId;

@MappedSuperclass
public abstract class BasicEntity<EntidadeId extends BasicId>{

	@EmbeddedId
	private EntidadeId id;
	
	protected BasicEntity(EntidadeId id) {
		this.id = id;
	}
	
	public final EntidadeId getId(){
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		@SuppressWarnings("unchecked")
		BasicEntity<BasicId> other = (BasicEntity<BasicId>) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
