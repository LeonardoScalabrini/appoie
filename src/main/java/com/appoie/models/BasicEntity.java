package com.appoie.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Entity
public abstract class BasicEntity<IdBasic extends BasicId>{

	@EmbeddedId
	private final IdBasic id;
	
	public BasicEntity(IdBasic id) {
		this.id = id;
	}
	
	public final IdBasic getId(){
		return id;
	}
}
