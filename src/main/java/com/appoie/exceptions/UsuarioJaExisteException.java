package com.appoie.exceptions;

public class UsuarioJaExisteException extends Exception {

	
	private static final long serialVersionUID = 1L;
	public UsuarioJaExisteException(){
		super("Usuário Já foi logado no facebook!");
	}
	
	

}
