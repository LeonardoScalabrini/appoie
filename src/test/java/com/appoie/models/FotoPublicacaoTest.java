package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.ids.FotoPublicacaoId;

public class FotoPublicacaoTest {
	
	@Test
	public void deveCriar(){
		FotoPublicacaoId fotoPublicacaoId = new FotoPublicacaoId();
		String endereco = "endereco";
		FotoPublicacao fotoPublicacao = new FotoPublicacao(fotoPublicacaoId, endereco);
		Assert.assertTrue(fotoPublicacao.getEndereco().equals(endereco));
		Assert.assertTrue(fotoPublicacao.getId().equals(fotoPublicacaoId));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComIdNull(){
		new FotoPublicacao(null, "endereco");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComEnderecoVazio(){
		new FotoPublicacao(new FotoPublicacaoId(), "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComEnderecoNull(){
		new FotoPublicacao(new FotoPublicacaoId(), null);
	}


}
