package com.appoie.models;

import javax.persistence.Embeddable;

import static com.appoie.utils.ValidationString.*;

@Embeddable
public class Senha {
	
	private String value;
	private static final int TAMANHO_MINIMO = 6;
	
	private Senha() {
		// TODO Auto-generated constructor stub
	}
	
	public Senha(String value) throws SenhaTamanhoMinimoException{	
		this();
		setSenha(value);
	}	
	
	public void setSenha(String value) throws SenhaTamanhoMinimoException{
		isNullOrEmpty(value);
		if(value.length()< TAMANHO_MINIMO){
			throw new SenhaTamanhoMinimoException();
		}
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

}
