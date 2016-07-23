package com.appoie.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import com.appoie.exceptions.ErroAoCriarDiretoriosExeception;
import com.appoie.models.FotoPerfil;
import com.appoie.models.FotoPublicacao;

public class GerenciadorDiretorio {

	private static boolean criaDiretoriosPadrao() {
		boolean success = (new File("/fotos/perfil")).mkdirs();
		if (!success) {
			return false;
		}
		success = (new File("/fotos/publicacao")).mkdirs();
		if (!success) {
			return false;
		}
		return true;
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
		String diretorio = "/fotos/publicacao/" + usuarioId + "/" + fotoPublicacao.getId().getValue();
		if (!criaDiretoriosPadrao()) {
			throw new ErroAoCriarDiretoriosExeception();

		} else {

			try {

				byte[] decoded = Base64.getDecoder().decode(trataBase64(fotoPublicacao.getFoto()));
				FileOutputStream file = new FileOutputStream(diretorio);
				file.write(decoded);
				file.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return diretorio;

	}
	
	public static String salvarFotoPerfil(FotoPerfil fotoPerfil, String usuarioId)
			throws ErroAoCriarDiretoriosExeception {
		String diretorio = "/fotos/perfil/" + usuarioId + "/" + fotoPerfil.getId().getValue();
		if (!criaDiretoriosPadrao()) {
			throw new ErroAoCriarDiretoriosExeception();

		} else {

			try {

				byte[] decoded = Base64.getDecoder().decode(trataBase64(fotoPerfil.getFoto()));
				FileOutputStream file = new FileOutputStream(diretorio);
				file.write(decoded);
				file.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return diretorio;

	}

}
