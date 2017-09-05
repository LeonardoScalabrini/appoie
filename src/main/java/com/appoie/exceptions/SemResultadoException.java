package com.appoie.exceptions;

public class SemResultadoException extends Exception{

	private static final long serialVersionUID = -4929064949930420439L;

	public SemResultadoException() {
		new Exception("Ocorreu um problema, tente novamente!");
	}

}
