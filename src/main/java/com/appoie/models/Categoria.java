package com.appoie.models;

import com.appoie.utils.FotoRepository;

public enum Categoria {

	ARBORIZACAO("Arborização"), DEFESACIVIL("Defesa Civil"), FUNDODEVALE("Fundo de Vale"), ILUMINACAO("Iluminação"),
	PAVIMENTACAO("Pavimentação"), SANEAMENTOBASICO("Saneamento Básico"), SEGURANCA("Segurança"), TERRENOBALDIO("Terreno Baldio"), 
	TRANSPORTEPUBLICO("Transporte Público");
	
	private FotoRepository repository = new FotoRepository();
	
	private final String PATCH_MARCADORES_PADRAO = "c:\\fotosAppoie\\Categoria\\Marcadores\\";

	private String descricao;
	
	private Categoria(String descricao){
		this.descricao = descricao;
	}
	
	private String getPatchMarcadores(){
		return PATCH_MARCADORES_PADRAO + name(); 
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public String getMarcador(){
		return repository.getBase64(getPatchMarcadores());
	}

}
