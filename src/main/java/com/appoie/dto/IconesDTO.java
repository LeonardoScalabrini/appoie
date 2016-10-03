package com.appoie.dto;

import com.appoie.models.Categoria;

public class IconesDTO {

	public final String categoria;
	public final String foto;
	
	public IconesDTO(Categoria categoria) {
		this.categoria = categoria.toString();
		this.foto = categoria.getMarcador();
	}
}
