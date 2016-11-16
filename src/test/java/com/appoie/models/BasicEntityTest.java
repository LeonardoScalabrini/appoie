package com.appoie.models;

import org.junit.Test;

import io.jsonwebtoken.lang.Assert;

public class BasicEntityTest {
	
	@Test
	public void deveCriarEntidade(){
		EntidadeTest entidadeTest = new EntidadeTest(new IdTest());
		Assert.notNull(entidadeTest.getId().getValue());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarEntidadeIdNull(){
		new EntidadeTest(null);
	}

}
