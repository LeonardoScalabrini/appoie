package com.appoie.exceptions;

public class FiltroTipoPublicacaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public FiltroTipoPublicacaoException() {
		super("Deve ser selecionado pelo menos um tipo!");
	}
}
