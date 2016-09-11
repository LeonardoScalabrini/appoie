package com.appoie.models;

import javax.persistence.Entity;

import com.appoie.ids.LocalizacaoId;

@Entity
public class Localizacao extends BasicEntity<LocalizacaoId> {
	
	private String coordenadas;
	
	public Localizacao(String coordenadas) {
		super(new LocalizacaoId());

	}

	public String getCoordenadas() {
		return coordenadas;
	}
}
