package com.appoie.models;

import static com.appoie.utils.ValidationString.*;

import javax.persistence.Embeddable;

import com.appoie.exceptions.CampoObrigatorioException;

@Embeddable
public class Senha {
	
	private String value;
	
	private Senha() {

	}
	
	public Senha(String value) throws Exception {
		this();
		
		if(isNullOrEmpty(value)){
			throw new CampoObrigatorioException();
		}
		this.value = value;
	}
	
	public Senha(String value, String confirmarSenha)throws Exception{
		this();
		
		if(isNullOrEmpty(value)||isNullOrEmpty(confirmarSenha)){
			throw new CampoObrigatorioException();
		}
		
		if(!value.equals(confirmarSenha)){
			throw new CampoObrigatorioException();
		}
		
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

}
