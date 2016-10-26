package com.appoie.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.FotoPerfilId;
import com.appoie.ids.UsuarioId;

@Entity
public class FotoPerfil extends BasicEntity<FotoPerfilId> {
	@AttributeOverride(name = "id", column = @Column(name = "usuario_id"))
	private UsuarioId id;
	private String endereco;

	public FotoPerfil(UsuarioId id, String endereco) {
		this();
		this.id = id;
		this.endereco = endereco;

	}

	public FotoPerfil() {
		super(new FotoPerfilId());
		super.setId(new FotoPerfilId());
	}

	public String getEndereco() {
		return endereco;
	}
}
