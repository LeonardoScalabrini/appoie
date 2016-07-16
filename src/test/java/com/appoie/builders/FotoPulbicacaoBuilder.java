package com.appoie.builders;

import java.awt.image.BufferedImage;


import com.appoie.manipuladorDeFotos.ConverteFoto;
import com.appoie.models.FotoPublicacao;

public class FotoPulbicacaoBuilder {
	BufferedImage imagem;
	private byte[] foto;

	public FotoPulbicacaoBuilder() {
		imagem = ConverteFoto.alterarDimensaoDaFoto("src/imagem/buraco.jpg", 600, 600);
        foto = ConverteFoto.converterImgagemEmBytes(imagem);
		
	}

	public FotoPublicacao criar() {
		return new FotoPublicacao( foto);
	}
	public FotoPulbicacaoBuilder nome(byte[] foto){
		this.foto = foto;
		return this;
	}
}
