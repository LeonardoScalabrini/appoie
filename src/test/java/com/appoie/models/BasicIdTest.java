package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.ids.BasicId;

public class BasicIdTest {
	
	public void deveCriar(){
		BasicId idTest = new IdTest();
		Assert.assertNotNull(idTest.getValue());
	}
	
	@Test
	public void deveCriarComOutroValor(){
		IdTest idTeste = new IdTest("ID");
		Assert.assertTrue(idTeste.getValue().equals("ID"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarNull(){
		new IdTest(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveCriarVazio(){
		new IdTest("");
	}

}
