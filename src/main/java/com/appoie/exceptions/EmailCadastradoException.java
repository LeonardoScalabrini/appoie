package com.appoie.exceptions;

public class EmailCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EmailCadastradoException() {
		super("O email já existe no sistema!");
	}

}
