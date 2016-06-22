package com.appoie.exceptions;

public class NovoEmailIgualException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NovoEmailIgualException() {
		super("O novo email não pode ser igual ao anterior!");
	}

}
