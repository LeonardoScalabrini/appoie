package com.appoie.models;

import javax.persistence.Embeddable;

import com.appoie.exceptions.SenhaTamanhoMinimoException;

import static com.appoie.utils.ValidationString.*;

@Embeddable
public class Senha {
	
	private String value;
	private static final int TAMANHO_MINIMO = 6;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Senha other = (Senha) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
