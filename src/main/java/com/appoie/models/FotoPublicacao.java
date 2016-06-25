package com.appoie.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import static com.appoie.utils.ValidationObject.*;

import com.appoie.ids.FotoPublicacaoId;

@Entity
public class FotoPublicacao extends BasicEntity<FotoPublicacaoId> {
	@AttributeOverride(name="id",column=@Column(name="fotoPublicacaoId"))
	private FotoPublicacaoId id;
	public byte[] foto;

	protected FotoPublicacao() {
		super(new FotoPublicacaoId());
		isNull(foto);
		this.foto = foto;

	}

}
