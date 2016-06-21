package com.appoie.exceptions;

public class EmailFormatoException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailFormatoException() {
		super("Email com formato inválido");
	}
}
