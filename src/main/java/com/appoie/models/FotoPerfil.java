package com.appoie.models;

import javax.persistence.Entity;

import com.appoie.ids.UsuarioId;

@Entity
public class FotoPerfil extends BasicEntity<UsuarioId>{
	
	private String endereco;
	
	public FotoPerfil(UsuarioId id, String base64) {
		super(id);
	}
	
	public String getEndereco(){
		return endereco; 
	}
}
