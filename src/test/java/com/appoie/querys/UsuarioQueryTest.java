package com.appoie.querys;

import org.junit.After;
import static org.junit.Assert.*;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appoie.AppoieApplication;
import com.appoie.builders.UsuarioBuilder;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.repositorys.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppoieApplication.class)
public class UsuarioQueryTest {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioQuery query;
	
	@After
	public void depois(){
		repository.deleteAll();
	}
	
	@Test
	public void deveBuscarUsuarioId() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		repository.save(usuario);
		UsuarioId recuperado = query.buscar(usuario.getEmail(), usuario.getSenha());
		assertTrue(recuperado.equals(usuario.getId()));
	}
	
	@Test(expected=NoResultException.class)
	public void nãodeveBuscarUsuarioId() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		
		query.buscar(usuario.getEmail(), usuario.getSenha());
	}
	
	@Test
	public void deveBuscarUsuario() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		repository.save(usuario);
		Usuario recuperado = query.buscar(usuario.getEmail());
		assertTrue(recuperado.equals(usuario));
	}
	
	@Test(expected=NoResultException.class)
	public void nãoDeveBuscarUsuario() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		
		query.buscar(usuario.getEmail());
	}
	
	@Test
	public void deveEncontrarEmail() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		repository.save(usuario);
		Boolean achou = query.existeEmail(usuario.getEmail());
		assertTrue(achou);
	}
	
	@Test
	public void nãoDeveEncontrarEmail() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		
		Boolean achou = query.existeEmail(usuario.getEmail());
		assertFalse(achou);
	}
	
	@Test
	public void deveExistirUsuario() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		repository.save(usuario);
		Boolean achou = query.existe(usuario.getEmail(), usuario.getSenha());
		assertTrue(achou);
	}
	
	public void nãoDeveExistirUsuario() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		
		Boolean achou = query.existe(usuario.getEmail(), usuario.getSenha());
		assertFalse(achou);
	}
	
	@Test
	public void deveEncontrarSenha() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		repository.save(usuario);
		Senha recuperada = query.buscarSenha(usuario.getEmail());
		assertTrue(recuperada.equals(usuario.getSenha()));
	}
	
	@Test(expected=NoResultException.class)
	public void nãoDeveEncontrarSenha() throws Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		
		query.buscarSenha(usuario.getEmail());
	}
}
