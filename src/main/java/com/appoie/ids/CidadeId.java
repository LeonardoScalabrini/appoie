package com.appoie.ids;

import javax.persistence.Embeddable;

@Embeddable
public class CidadeId extends BasicId{

	public CidadeId(String id) {
		super(id);
	}
	
	public CidadeId() {
		super();
	}

	

	private static final long serialVersionUID = 1L;
}
