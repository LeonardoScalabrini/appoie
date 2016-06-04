package com.appoie.models;

//import static com.appoie.utils.ValidationString.isNullOrEmpty;

import javax.persistence.Embeddable;

@Embeddable
public class Email {

	private String value;
	public Email() {
		// TODO Auto-generated constructor stub
	}
	
	public Email(String value){		
		this.value = value;
	}	
	
	public String getValue(){
		return value;
	}
}
