package com.appoie.models;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.appoie.ids.BasicId;

@MappedSuperclass
public abstract class BasicEntity<Id extends BasicId>{

	@EmbeddedId
	private final Id id;
	
	protected BasicEntity(Id id) {
		this.id = id;
	}
	
	public final Id getId(){
		return id;
	}
}
