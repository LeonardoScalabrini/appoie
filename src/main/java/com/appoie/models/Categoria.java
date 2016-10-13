package com.appoie.models;

import com.appoie.utils.FotoRepository;

public enum Categoria {

	
	

	

	
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

}
