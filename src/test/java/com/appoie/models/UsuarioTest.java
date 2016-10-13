package com.appoie.models;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.appoie.builders.UsuarioBuilder;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;

public class UsuarioTest {
	
	private UsuarioBuilder biulder;
	private Usuario usuario;
	
	@Before
	public void antes() throws EmailFormatoException, SenhaTamanhoMinimoException{
		 biulder = new UsuarioBuilder();
		 usuario = null;
	}
	
	@Test
	public void deveCriarUsuario() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		
		Calendar dataDeNascimento = Calendar.getInstance();
		Usuario usuario = biulder.nome("teste").
								  sobrenome("teste").
								  dataDeNascimento(dataDeNascimento).
								  email("teste@teste.com.br").
								  senha("123456").
								  criar();

		Assert.assertTrue(usuario.getNome().equals("teste"));
		Assert.assertTrue(usuario.getSobrenome().equals("teste"));
		Assert.assertTrue(usuario.getDataDeNascimento().equals(dataDeNascimento));
		Assert.assertTrue(usuario.getEmail().getValue().equals("teste@teste.com.br"));
		Assert.assertTrue(usuario.getSenha().getValue().equals("123456"));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarNomeNulo() throws Exception {
		usuario = biulder.nome(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarNomeVazio() throws Exception {
		usuario = biulder.nome("").criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSobrenomeNulo() throws Exception {
		usuario = biulder.sobrenome(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSobrenomeVazio() throws Exception {
		usuario = biulder.sobrenome("").criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarDataDeNascimentoNula() throws Exception {
		usuario = biulder.dataDeNascimento(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarEmailNulo() throws Exception {
		usuario = biulder.email(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSenhaNula() throws Exception {
		usuario = biulder.senha(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarNomeNulo() throws Exception {
		usuario = biulder.criar();
		usuario.setNome(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarNomeVazio() throws Exception {
		usuario = biulder.criar();
		usuario.setNome("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarSobrenomeNulo() throws Exception {
		usuario = biulder.criar();
		usuario.setSobrenome(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarSobrenomeVazio() throws Exception {
		usuario = biulder.criar();
		usuario.setSobrenome("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarDataDeNascimentoNula() throws Exception {
		usuario = biulder.criar();
		usuario.setDataDeNascimento(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarSexoNulo() throws Exception {
		usuario = biulder.criar();
		usuario.setSexo(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarEmailNulo() throws Exception {
		usuario = biulder.criar();
		usuario.setEmail(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarSenhaNula() throws Exception {
		usuario = biulder.criar();
		usuario.setSenha(null);
	}
	

}
