package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.builders.PublicacaoBuilder;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;

public class PublicacaoTest {

	@Test
	public void deveCriar() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComTituloVazio() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().titulo("").criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComTituloNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().titulo(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComDescricaoVazia() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().descricao("").criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComDescricaoNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().descricao(null).criar();
	}
	
	@Test(expected=NullPointerException.class)
	public void naodeveCriarSemCategoria() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().categoria(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComCidadeIdNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().cidadeId(null).criar();
	}
	
	@Test(expected=QuantidadeFotosPublicacaoException.class)
	public void naodeveCriarComFotoIdNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().fotoPublicacaoId(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComLatitudeNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().latitude(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComLongitudeNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().longitude(null).criar();
	}
	
	@Test
	public void deveApoiarPublicacao() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		publicacao.apoiar();
		Assert.assertTrue(publicacao.getQtdApoiadores().equals(1L));
	}
	
	@Test
	public void deveFecharPublicacao() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		Assert.assertNull(publicacao.getDataFechamento());
		publicacao.fechar();
		Assert.assertTrue(publicacao.getStatus().equals(Status.FECHADO));
		Assert.assertNotNull(publicacao.getDataFechamento());
	}
}
