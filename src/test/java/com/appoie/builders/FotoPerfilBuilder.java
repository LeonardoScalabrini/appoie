package com.appoie.builders;

import java.awt.image.BufferedImage;

import com.appoie.ids.UsuarioId;
import com.appoie.manipuladorDeFotos.ConverteFoto;
import com.appoie.models.FotoPerfil;

public class FotoPerfilBuilder {
	BufferedImage imagem;
	private byte[] foto;

	public FotoPerfilBuilder() {
		imagem = ConverteFoto.alterarDimensaoDaFoto("src/imagem/buraco.jpg", 600, 600);
        foto = ConverteFoto.converterImgagemEmBytes(imagem);
		
	}

	public FotoPerfil criar(UsuarioId usuarioId) {
		return new FotoPerfil(usuarioId, foto);
	}
	public FotoPerfilBuilder nome(byte[] foto){
		this.foto = foto;
		return this;
	}

}
