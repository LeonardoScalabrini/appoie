package com.appoie.ids;

import javax.persistence.Embeddable;

@Embeddable
public class FotoPublicacaoId extends BasicId {

	public FotoPublicacaoId() {
	}
	
	public FotoPublicacaoId(String id) {
		super(id);
	}

	private static final long serialVersionUID = 1L;

}
