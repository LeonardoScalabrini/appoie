package com.appoie.models;

import javax.persistence.Entity;

import com.appoie.ids.UsuarioId;
import static com.appoie.utils.ValidationObject.*;

@Entity
public class FotoPerfil extends BasicEntity<UsuarioId>{
	
	public String foto;
	
	public FotoPerfil(UsuarioId id, String foto) {
		super(id);
		isNull(id);
		this.foto = foto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
}
