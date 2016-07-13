package com.appoie.exceptions;

public class NumeroFotosPublicacaoInvalido extends Exception {
	public NumeroFotosPublicacaoInvalido() {
		super("Quantidade de fotos inválida! É permitido no máximo 3 fotos.");
	}

}
