package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

public class EstadoTest {
	
	@Test
	public void deveCriar(){
		Estado estado = new Estado("Paraná");
		Assert.assertTrue(estado.getNome().equals("Paraná"));
		Assert.assertNotNull(estado.getId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveCriarComNomeVazio(){
		new Estado("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveCriarComNomeNulo(){
		new Estado(null);
	}

}
