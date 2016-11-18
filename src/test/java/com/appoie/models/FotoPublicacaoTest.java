package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.ids.FotoPublicacaoId;

public class FotoPublicacaoTest {
	
	@Test
	public void deveCriar(){
		String endereco = "endereco";
		FotoPublicacao fotoPublicacao = new FotoPublicacao(endereco);
		Assert.assertTrue(fotoPublicacao.getEndereco().equals(endereco));
		Assert.assertNotNull(fotoPublicacao.getId());
	}
	
	@Test
	public void deveCriarComId(){
		FotoPublicacaoId fotoPublicacaoId = new FotoPublicacaoId();
		String endereco = "endereco";
		FotoPublicacao fotoPublicacao = new FotoPublicacao(fotoPublicacaoId, endereco);
		Assert.assertTrue(fotoPublicacao.getEndereco().equals(endereco));
		Assert.assertTrue(fotoPublicacao.getId().equals(fotoPublicacaoId));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComEnderecoVazio(){
		new FotoPublicacao("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComEnderecoNull(){
		new FotoPublicacao(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComIdNull(){
		new FotoPublicacao(null, "endereco");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComIdEhEnderecoVazio(){
		new FotoPublicacao(new FotoPublicacaoId(), "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComIdEhEnderecoNull(){
		new FotoPublicacao(new FotoPublicacaoId(), null);
	}


}
