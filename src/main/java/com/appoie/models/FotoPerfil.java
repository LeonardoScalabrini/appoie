package com.appoie.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.FotoPerfilId;
import com.appoie.ids.UsuarioId;
import com.appoie.utils.ValidationObject;
import com.appoie.utils.ValidationString;

@Entity
public class FotoPerfil extends BasicEntity<FotoPerfilId> {
	@AttributeOverride(name = "id", column = @Column(name = "usuario_id"))
	private UsuarioId id;
	private String endereco;

	public FotoPerfil(UsuarioId id, String endereco) {
		this();
		ValidationObject.isNull(id);
		ValidationString.isNullOrEmpty(endereco);
		this.id = id;
		this.endereco = endereco;

	}

	private FotoPerfil() {
		super(new FotoPerfilId());
		
	}

	public String getEndereco() {
		return endereco;
	}
	
	public UsuarioId getUsuarioId(){
		return id;
	}
}
