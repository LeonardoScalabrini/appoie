package com.appoie.models;

import static com.appoie.utils.ValidationString.isNullOrEmpty;

import javax.persistence.Embeddable;

@Embeddable
public class Email {

	private String value;
	
	private Email(){
		
	}
	
	public Email(String value) throws Exception {
		this();
		
		if (isNullOrEmpty(value)){
			throw new IllegalArgumentException();
		}
		this.value = value;
	}
	
	public Email(String value, String confirmarEmail)throws Exception {
		this();
		
		if (isNullOrEmpty(value) || isNullOrEmpty(confirmarEmail)){
			throw new IllegalArgumentException();
		}
		
		if(!value.equals(confirmarEmail)){
			throw new IllegalArgumentException();
		}
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
