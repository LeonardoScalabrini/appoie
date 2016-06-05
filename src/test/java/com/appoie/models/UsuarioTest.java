package com.appoie.models;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.appoie.biulders.UsuarioBiulder;
import com.appoie.exceptions.EmailFormatoException;

public class UsuarioTest {
	
	private UsuarioBiulder biulder;
	private Usuario usuario;
	
	@Before
	public void antes() throws EmailFormatoException, SenhaTamanhoMinimoException{
		 biulder = new UsuarioBiulder();
	}
	
	@Test
	public void deveCriarUsuario() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		
		Calendar dataDeNascimento = Calendar.getInstance();
		Usuario usuario = biulder.nome("teste").
								  sobrenome("teste").
								  dataDeNascimento(dataDeNascimento).
								  email(new Email("teste@teste.com.br")).
								  senha(new Senha("123456")).
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
	public void nãoDeveAceitarSexoNulo() throws Exception {
		usuario = biulder.sexo(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarEmailNulo() throws Exception {
		usuario = biulder.email(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSenhaNula() throws Exception {
		usuario = biulder.senha(null).criar();
	}
	
	

}
