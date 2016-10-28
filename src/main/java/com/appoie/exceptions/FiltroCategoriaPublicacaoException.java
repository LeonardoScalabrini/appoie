package com.appoie.exceptions;

public class FiltroCategoriaPublicacaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FiltroCategoriaPublicacaoException() {
		super("Deve ser selecionada pelo menos uma categoria!");
	}
}
