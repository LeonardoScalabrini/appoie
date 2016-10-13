package com.appoie.models;

import com.appoie.utils.FotoRepository;

public enum Categoria {
<<<<<<< HEAD
	ILUMINACAO;
	

	
=======
	
	ARBORIZACAO, DEFESACIVIL, FUNDODEVALE, ILUMINACAO,
	PAVIMENTACAO, SANEAMENTOBASICO, SEGURANCA, TERRENOBALDIO, 
	TRANSPORTEPUBLICO;
	
	private FotoRepository repository = new FotoRepository(TipoImagem.PNG);
	
	private final String PATCH_MARCADORES_PADRAO = "c:\\fotosAppoie\\Categoria\\Marcadores\\";
	
	private String getPatchMarcadores(){
		return PATCH_MARCADORES_PADRAO + name(); 
	}
	
	public String getMarcador(){
		return repository.getBase64(getPatchMarcadores());
	}
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f
}
