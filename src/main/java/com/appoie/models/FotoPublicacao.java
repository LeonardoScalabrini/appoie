package com.appoie.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import static com.appoie.utils.ValidationObject.*;

import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.PublicacaoId;

@Entity

public class FotoPublicacao extends BasicEntity<FotoPublicacaoId> {
	@AttributeOverride(name="id",column=@Column(name="publicacaoId"))
	private PublicacaoId publicacaoId;
	
	
	public String foto;
	public FotoPublicacao() {
		super(new FotoPublicacaoId());
	}

	public FotoPublicacao(String foto) {
		this();
		isNull(foto);
		this.foto = foto;

	}

	public String getFoto() {
		return foto;
	}
	
	
	public void setPublicacaoId(PublicacaoId publicacaoId) {
		this.publicacaoId = publicacaoId;
	}

	public PublicacaoId getPublicacaoId() {
		return this.publicacaoId;
	}
	

}
