package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.ids.UsuarioId;

public class FotoPerfilTest {

	@Test
	public void deveCriar(){
		UsuarioId usuarioId = new UsuarioId();
		String endereco = "enderecoDaFoto";
		FotoPerfil fotoPerfil = new FotoPerfil(usuarioId, endereco);
		Assert.assertTrue(fotoPerfil.getEndereco().equals(endereco));
		Assert.assertTrue(fotoPerfil.getUsuarioId().equals(usuarioId));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveCriarComUsuarioIdNulo(){
		new FotoPerfil(null, "enderecoDaFoto");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveCriarComEnderecoNull(){
		new FotoPerfil(new UsuarioId(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveCriarComEnderecoVazio(){
		new FotoPerfil(new UsuarioId(), "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveCriarComArgumentosNull(){
		new FotoPerfil(null, null);
	}
	
}
