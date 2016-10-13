package com.appoie.ids;

import javax.persistence.Embeddable;

@Embeddable
public class PublicacaoId extends BasicId{

	public PublicacaoId(String id) {
		super(id);
	}

	public PublicacaoId() {
<<<<<<< HEAD
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return super.getId();
=======
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f
		
	}

	private static final long serialVersionUID = 1L;
}
