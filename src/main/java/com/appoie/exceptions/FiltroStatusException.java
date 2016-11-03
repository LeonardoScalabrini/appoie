package com.appoie.exceptions;

public class FiltroStatusException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public FiltroStatusException() {
		super("Deve selecionar um status antes de filtrar!");
	}
}
