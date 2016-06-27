package com.appoie.exceptions;

public class QuantidadeFotosPublicacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public QuantidadeFotosPublicacaoException() {
		super("Só é possivel inservir três fotos!");
	}

}
