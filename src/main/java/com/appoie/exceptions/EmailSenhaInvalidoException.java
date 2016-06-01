package com.appoie.exceptions;

public class EmailSenhaInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EmailSenhaInvalidoException() {
		super("Email e/ou senha inválidos!");
	}
}
