package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

public class SenhaTest {

	private Senha senha;
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSenhaNula() throws SenhaTamanhoMinimoException{
		senha = new Senha(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSenhaVazia() throws SenhaTamanhoMinimoException{
		senha = new Senha("");
	}
	
	@Test(expected=SenhaTamanhoMinimoException.class)
	public void nãoDeveAceitarSenhaComMenosDe6Digitos() throws SenhaTamanhoMinimoException{
		senha = new Senha("12345");
	}
	
	@Test
	public void deveAceitarSenhaCom6Digitos() throws SenhaTamanhoMinimoException{
		String senhaCom6Digitos = "123456";
		senha = new Senha(senhaCom6Digitos);
		senha.setSenha(senhaCom6Digitos);
		
		Assert.assertTrue(senha.getValue().equals(senhaCom6Digitos));
	}
	
	@Test
	public void deveAceitarSenhaComMaisDe6Digitos() throws SenhaTamanhoMinimoException{
		String senhaCom7Digitos = "1234567";
		senha = new Senha(senhaCom7Digitos);
		senha.setSenha(senhaCom7Digitos);
		
		Assert.assertTrue(senha.getValue().equals(senhaCom7Digitos));
	}
	
}
