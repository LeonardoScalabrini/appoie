package com.appoie.exceptions;

public class PublicacaoFechadaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PublicacaoFechadaException() {
		super("Esta publicação já se encontra fechada!");
	}

}
