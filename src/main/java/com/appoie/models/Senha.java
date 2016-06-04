package com.appoie.models;



import javax.persistence.Embeddable;

@Embeddable
public class Senha {
	
	private String value;
	public Senha() {
		// TODO Auto-generated constructor stub
	}
	
	public Senha(String value){		
		this.value = value;
	}	
	
	public String getValue(){
		return value;
	}

}
