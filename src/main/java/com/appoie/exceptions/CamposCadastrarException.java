package com.appoie.exceptions;

public class CamposCadastrarException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CamposCadastrarException() {
		super("Preencha todos os campos corretamente!");
	}

}
