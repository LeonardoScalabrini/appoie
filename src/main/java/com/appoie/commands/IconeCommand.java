package com.appoie.commands;

import com.appoie.models.Categoria;

public class IconeCommand {

	public final String categoria;
	public final String foto;
	
	public IconeCommand(Categoria categoria) {
		this.categoria = categoria.toString();
		this.foto = categoria.getMarcador();
	}

}
