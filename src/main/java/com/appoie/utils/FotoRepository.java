package com.appoie.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.appoie.models.TipoImagem;

@Component
public class FotoRepository {

	private final String CAMINHO_PADRAO = "c:\\fotosAppoie\\";
	private final TipoImagem tipoImagem;
	
	public FotoRepository(TipoImagem tipoImagem) {
		new File(CAMINHO_PADRAO).mkdirs();
		this.tipoImagem = tipoImagem;
	}
	
	public FotoRepository() {
		new File(CAMINHO_PADRAO).mkdirs();
		this.tipoImagem = TipoImagem.JPG;
	}
	
	private String tratarBase64(String base64) {
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
	
	private String formatarBase64(byte[] arrayByte) {
		return "data:image/" + tipoImagem.name() + ";base64," 
				+ Base64.getEncoder().encodeToString(arrayByte);
	}
	
	private String Encoder(String enderecoCompleto) {
		
		String base64 = null;
		try {
			File file = new File(getEnderecoCompleto(enderecoCompleto));
		    BufferedImage img = ImageIO.read(file);

		    ByteArrayOutputStream bao = new ByteArrayOutputStream();

		    ImageIO.write(img, tipoImagem.name(), bao);

		    byte[] arrayByte = bao.toByteArray();;
		   
		    base64 = formatarBase64(arrayByte);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64;
	}
	
	private String getEnderecoCompleto(String endereco) {
		return endereco + "." + tipoImagem.name();
	}

	private String Decoder(String base64, String endereco, TipoImagem tipoImagem) {
		
		String enderecoCompleto = getEnderecoCompleto(endereco);
		try {
			byte[] decoded = Base64.getDecoder().decode(tratarBase64(base64));
			FileOutputStream file = new FileOutputStream(enderecoCompleto);
			file.write(decoded);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return enderecoCompleto;
	}
	
	public String save(String base64, String id) {
		
		String diretorio = CAMINHO_PADRAO + id;
		
		diretorio = Decoder(base64, diretorio, tipoImagem);

		return diretorio;
	}
	
	public String getBase64(String endereco){
		
		return Encoder(endereco);
	}
}
