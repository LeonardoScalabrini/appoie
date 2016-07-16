package com.appoie.models;

import javax.persistence.Entity;

import org.hamcrest.core.IsNull;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.ids.LocalizacaoId;

@Entity
public class Localizacao extends BasicEntity<LocalizacaoId> {
	private String coordenadas;
	
	public Localizacao() {
		super(new LocalizacaoId());

	}
	
	public Localizacao(PublicacaoCommand command){
		this();
		
		this.coordenadas = command.coordenadasLocalizacao;
		
	}

	public String getCoordenadas() {
		return coordenadas;
	}
	
	
	

}
