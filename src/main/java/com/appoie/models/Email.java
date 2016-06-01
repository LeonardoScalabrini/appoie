package com.appoie.models;

import static com.appoie.utils.ValidationString.*;

import javax.persistence.Embeddable;

import com.appoie.exceptions.CampoObrigatorioException;

@Embeddable
public class Email {

	private String value;
	
	private Email(){
		
	}
	
	public Email(String value) throws Exception {
		this();
		
		if (isNullOrEmpty(value)){
			throw new CampoObrigatorioException();
		}
		this.value = value;
	}
	
	public Email(String value, String confirmarEmail)throws Exception {
		this();
		
		if (isNullOrEmpty(value) || isNullOrEmpty(confirmarEmail)){
			throw new CampoObrigatorioException();
		}
		
		if(!value.equals(confirmarEmail)){
			throw new CampoObrigatorioException();
		}
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
