package com.appoie.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class FotoRepository {

	private final String CAMINHO_PADRAO = "c:\\fotosAppoie\\";
	
	public FotoRepository() {
		new File(CAMINHO_PADRAO).mkdirs();
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
	
	private String formatarBase64(byte[] arrayByte, String tipo) {
		return "data:image/" + tipo + ";base64," 
				+ Base64.getEncoder().encodeToString(arrayByte);
	}
	
	private String Encoder(String enderecoCompleto) {
		String base64 = null;
		try {
			File file = new File(enderecoCompleto);
		    BufferedImage img = ImageIO.read(file);
		    
		    ByteArrayOutputStream bao = new ByteArrayOutputStream();
		    
		    String tipo = getTipoImagemEnderecoCompleto(enderecoCompleto);
		    
		    ImageIO.write(img, tipo, bao);

		    byte[] arrayByte = bao.toByteArray();;
		   
		    base64 = formatarBase64(arrayByte, tipo);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64;
	}
	
	private String getTipoImagemEnderecoCompleto(String enderecoCompleto) {
		return enderecoCompleto.substring(enderecoCompleto.lastIndexOf(".")+1, enderecoCompleto.length());
	}

	private String getEnderecoCompleto(String base64, String endereco) {
		String tipo = getTipoImagem(base64);
		return endereco + "." + tipo;
	}

	private String getTipoImagem(String base64) {
		//data:image/jpeg; 
		int inicio = base64.indexOf("/");
		int fim = base64.indexOf(";");
		return base64.substring(inicio+1, fim);
	}

	private String Decoder(String base64, String endereco) {
		String enderecoCompleto = getEnderecoCompleto(base64, endereco);
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
		
		diretorio = Decoder(base64, diretorio);

		return diretorio;
	}
	
	public String getBase64(String endereco){
		
		return Encoder(endereco);
	}
}
