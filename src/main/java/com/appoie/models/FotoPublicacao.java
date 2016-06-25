package com.appoie.models;

import javax.persistence.Entity;
import static com.appoie.utils.ValidationObject.*;

import com.appoie.ids.FotoPublicacaoId;

@Entity
public class FotoPublicacao extends BasicEntity<FotoPublicacaoId> {
	public byte[] foto;

	protected FotoPublicacao() {
		super(new FotoPublicacaoId());
		isNull(foto);
		this.foto = foto;

	}

}
