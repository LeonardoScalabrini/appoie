package com.appoie.models;

import static com.appoie.utils.ValidationString.*;

import javax.persistence.Embeddable;

@Embeddable
public class Senha {
	
	private String value;
	
	private Senha() {

	}
	
	public Senha(String value) throws Exception {
		this();
		
		if(isNullOrEmpty(value)){
			throw new IllegalArgumentException();
		}
		this.value = value;
	}
	
	public Senha(String value, String confirmarSenha)throws Exception{
		this();
		
		if(isNullOrEmpty(value)||isNullOrEmpty(confirmarSenha)){
			throw new IllegalArgumentException();
		}
		
		if(!value.equals(confirmarSenha)){
			throw new IllegalArgumentException();
		}
		
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

}
