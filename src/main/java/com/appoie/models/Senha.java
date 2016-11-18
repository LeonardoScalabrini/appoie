package com.appoie.models;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.appoie.exceptions.SenhaTamanhoMinimoException;

import static com.appoie.utils.ValidationString.*;

@Embeddable
public class Senha {
	
	@Transient
	private final int TAMANHO_MINIMO = 6;
	
	private String value;
	
	private Senha() {

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
