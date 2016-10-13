package com.appoie.models;

import static com.appoie.utils.ValidationString.isNullOrEmpty;

import javax.persistence.Entity;

import com.appoie.ids.EstadoId;

@Entity
public class Estado extends BasicEntity<EstadoId>{
	
	private String nome;
	
	private Estado() {
		super(new EstadoId());
	}
	
	public Estado(String nome){
		this();
		isNullOrEmpty(nome);
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
}
