package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;

public class ApoiadorTest {

	@Test
	public void deveCriarApoiador(){
	
		PublicacaoId publicacaoId = new PublicacaoId();
		UsuarioId usuarioId = new UsuarioId();
		
		Apoiador apoiador =  new Apoiador(publicacaoId, usuarioId);
		Assert.assertTrue(apoiador.getIdPublicacao().equals(publicacaoId));
		Assert.assertTrue(apoiador.getApoiador().equals(usuarioId));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarApoiadorComPublicacaoNull(){
		new Apoiador(new PublicacaoId(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarApoiadorComUsuarioNull(){
		new Apoiador(null, new UsuarioId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarApoiadorComPublicacaoUsuarioNull(){
		new Apoiador(null, null);
	}
}
