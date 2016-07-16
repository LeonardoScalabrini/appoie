package com.appoie.ids;

import javax.persistence.Embeddable;

@Embeddable
public class UsuarioId extends BasicId{

	public UsuarioId(String id) {
		super(id);
	}
	public UsuarioId() {
		super();
	}

	private static final long serialVersionUID = 1L;
}
