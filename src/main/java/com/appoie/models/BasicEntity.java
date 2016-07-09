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
}
