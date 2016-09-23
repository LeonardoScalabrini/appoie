package com.appoie.models;

import com.appoie.utils.FotoRepository;

public enum Categoria {
	
	ARBORIZACAO("Arborização"), DEFESACIVIL("DefCivil"), FUNDODEVALE("Fundo"), ILUMINACAO("Iluminação"),
	PAVIMENTACAO("Pavimentacao"), SANEAMENTOBASICO("Saneamento"), SEGURANCA("Segurança"), TERRENOBALDIO("Terreno"), 
	TRANSPORTEPUBLICO("Transporte");
	
	private FotoRepository repository = new FotoRepository();
	
	private final String PATCH_PADRAO = "c:\\fotosAppoie\\Categoria\\";
	private final String PATCH_MARCADORES_PADRAO = "c:\\fotosAppoie\\Categoria\\Marcadores\\";
	
	private String nome;
	
	private Categoria(String nome) {
		this.nome = nome;
	}
	
	private String getPatch(){
		return PATCH_PADRAO + nome; 
	}
	
	private String getPatchMarcadores(){
		return PATCH_MARCADORES_PADRAO + nome + ".png"; 
	}
	
	public String getImage(){
		return repository.getBase64(getPatch());
	}
	
	public String getMarcador(){
		return repository.getBase64(getPatchMarcadores());
	}

}
