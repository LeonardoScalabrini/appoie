package com.appoie.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

@Component
public class FotoRepository {

	private final String CAMINHO_PADRAO = "c:\\fotosAppoie\\";
	
	public FotoRepository() {
		new File(CAMINHO_PADRAO).mkdirs();
	}

	private String trataBase64(String base64) {
		String base64Tratado = "";
		for (int i = 0; i < base64.length(); i++) {
			int indice;
			if (base64.charAt(i) == ',') {
				indice = i;
				base64Tratado = base64.substring(indice + 1, base64.length());
				break;
			}
		}
		return base64Tratado;
	}

	private String Encoder(String endereco, String tipoImagem) {
		String base64 = "";
		try {
			File file = new File(endereco);
		    BufferedImage img = ImageIO.read(file);

		    ByteArrayOutputStream bao = new ByteArrayOutputStream();

		    ImageIO.write(img, tipoImagem, bao);

		    byte[] arrayByte = bao.toByteArray();;
		   
		    base64 = "data:image/" + tipoImagem + ";base64," + Base64.getEncoder().encodeToString(arrayByte);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64;
	}
	
	private String Decoder(String base64, String diretorio) {
		try {
			byte[] decoded = Base64.getDecoder().decode(trataBase64(base64));
			diretorio += ".jpg";
			FileOutputStream file = new FileOutputStream(diretorio);
			file.write(decoded);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return diretorio;
	}
	
	public String salvar(String base64, String id) {
		
		String diretorio = CAMINHO_PADRAO + id;

		diretorio = Decoder(base64, diretorio);

		return diretorio;
	}
	
	public String getBase64(String endereco){
		
		return Encoder(endereco, "png");
	}
}
