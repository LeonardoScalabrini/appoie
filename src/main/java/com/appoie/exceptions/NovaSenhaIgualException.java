package com.appoie.exceptions;

public class NovaSenhaIgualException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NovaSenhaIgualException() {
		super("A nova senha não pode ser igual ao anterior!");
	}

}
