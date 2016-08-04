package com.appoie.ids;

import javax.persistence.Embeddable;

@Embeddable
public class UsuarioId extends BasicId{

	private static final long serialVersionUID = 1L;
	
	public UsuarioId() {
		super();
	}

	public UsuarioId(String id) {
		super(id);
	}
	
	
}
