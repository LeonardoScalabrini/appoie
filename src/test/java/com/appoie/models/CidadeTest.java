package com.appoie.models;

import org.junit.Test;

import com.appoie.ids.EstadoId;

import io.jsonwebtoken.lang.Assert;

public class CidadeTest {

	@Test
	public void deveCriar(){
		EstadoId estadoId = new EstadoId();
		String nomeCidade = "Maringá";
		Cidade cidade = new Cidade(estadoId, nomeCidade);
		Assert.notNull(cidade);
		Assert.isTrue(cidade.getEstadoId().equals(estadoId));
		Assert.isTrue(cidade.getNome().equals(nomeCidade));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarCidadeComNomeVazio(){
		new Cidade(new EstadoId(), "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarCidadeComNomeNull(){
		new Cidade(new EstadoId(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarCidadeComEstadoNull(){
		new Cidade(null, "Maringá");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarCidadeComCamposNullEVazio(){
		new Cidade(null, "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarCidadeComCamposNullENull(){
		new Cidade(null, null);
	}
}
