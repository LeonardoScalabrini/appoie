package com.appoie.models;

import javax.persistence.Entity;

import com.appoie.ids.UsuarioId;
import static com.appoie.utils.ValidationObject.*;

@Entity
public class FotoPerfil extends BasicEntity<UsuarioId>{
	
	public byte[] foto;
	
	public FotoPerfil(UsuarioId id, byte[] foto) {
		super(id);
		isNull(id);
		this.foto = foto;
	}
	
}
