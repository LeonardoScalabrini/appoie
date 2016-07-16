package com.appoie.ids;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
public abstract class BasicId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String id;
	
	public BasicId() {		
		this.id = UUID.randomUUID().toString();
	}
	
	public BasicId(String id) {
		this.id = id;
	}

	public final String getId() {
		return this.id;
	}

	
}
