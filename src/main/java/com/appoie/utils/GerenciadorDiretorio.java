package com.appoie.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import com.appoie.exceptions.ErroAoCriarDiretoriosExeception;
import com.appoie.models.FotoPerfil;
import com.appoie.models.FotoPublicacao;

public class GerenciadorDiretorio {

	private static void criaDiretoriosPadrao() {
		new File("c:\\fotos\\perfil").mkdirs();

		new File("c:\\fotos\\publicacao").mkdirs();

	}

	private static String trataBase64(String base64) {
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

	public static String salvarFotoPublicacao(FotoPublicacao fotoPublicacao, String usuarioId)
			throws ErroAoCriarDiretoriosExeception {
		String diretorio = "c:\\fotos\\publicacao\\" + usuarioId + "\\" + fotoPublicacao.getId().getValue();
		criaDiretoriosPadrao();

		new File(diretorio).mkdirs();
		
		try {

			byte[] decoded = Base64.getDecoder().decode(trataBase64(fotoPublicacao.getFoto()));
			diretorio += ".jpg";
			FileOutputStream file = new FileOutputStream(diretorio);
			file.write(decoded);
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return diretorio;

	}

	public static String salvarFotoPerfil(FotoPerfil fotoPerfil, String usuarioId)
			throws ErroAoCriarDiretoriosExeception {
		String diretorio = "c:\\fotos\\perfil\\" + usuarioId + "\\" + fotoPerfil.getId().getValue();
		criaDiretoriosPadrao();

		try {

			byte[] decoded = Base64.getDecoder().decode(trataBase64(fotoPerfil.getFoto()));
			FileOutputStream file = new FileOutputStream(diretorio);
			file.write(decoded);
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return diretorio;

	}
	
	public static String recuperarFoto(String endereco){
		try {
			
			File file = new File(endereco);
		    BufferedImage img = ImageIO.read(file);

		    ByteArrayOutputStream bao = new ByteArrayOutputStream();

		    ImageIO.write(img, "jpg", bao);

		    byte[] arrayByte = bao.toByteArray();;
		   
		    return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(arrayByte);
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
	}

}
