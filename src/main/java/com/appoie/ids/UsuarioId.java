package com.appoie.ids;

import javax.persistence.Embeddable;

@Embeddable
public class UsuarioId extends BasicId{

	private static final long serialVersionUID = 1L;
	
	public UsuarioId() {
		super();
	}
<<<<<<< HEAD
	
	public UsuarioId(String id) {
		super(id);
		
	}

	@Override
	public String toString() {
		return  getId() ;
=======

	public UsuarioId(String id) {
		super(id);
>>>>>>> c72c8b950a938951549416e14a65af3c8d072e39
	}
	
	
}
