package com.appoie.exceptions;

public class SenhaTamanhoMinimoException extends Exception {

	private static final long serialVersionUID = 1L;

	public SenhaTamanhoMinimoException() {
		super("A senha deve ter no minímo 6 caracters!");
	}
	
}
