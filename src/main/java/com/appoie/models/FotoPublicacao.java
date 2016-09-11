package com.appoie.models;

import javax.persistence.Entity;

import com.appoie.ids.FotoPublicacaoId;
import static com.appoie.utils.ValidationString.*;

@Entity
public class FotoPublicacao extends BasicEntity<FotoPublicacaoId> {
	
	private String endereco;
	
	private FotoPublicacao() {
		super(new FotoPublicacaoId());
	}

	public FotoPublicacao(String endereco) {
		this();
		isNullOrEmpty(endereco);
		this.endereco = endereco;
	}
	
	public String getEndereco(){
		return endereco;
	}
}
