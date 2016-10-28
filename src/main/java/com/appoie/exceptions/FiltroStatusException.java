package com.appoie.exceptions;

public class FiltroStatusException extends Exception {
	public FiltroStatusException() {
		super("Deve selecionar um status antes de filtrar!");
	}
}
