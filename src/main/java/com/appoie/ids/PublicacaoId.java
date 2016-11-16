package com.appoie.ids;

import javax.persistence.Embeddable;

@Embeddable
public class PublicacaoId extends BasicId{

	public PublicacaoId(String id) {
		super(id);
	}

	public PublicacaoId() {

	}
	
	private static final long serialVersionUID = 1L;
}
