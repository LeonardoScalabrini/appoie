package com.appoie.models;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicEntity {

	@Id
	private final String id;
	
	public BasicEntity() {
		id = UUID.randomUUID().toString(); 
	}
	
	public String getId(){
		return id;
	}
}
