package com.appoie.models;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.appoie.builders.UsuarioBuilder;
import com.appoie.commands.AlterarPerfilCommand;
import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.CidadeId;

public class UsuarioTest {
	
	private UsuarioBuilder builder;
	private Usuario usuario;
	
	@Before
	public void antes() throws EmailFormatoException, SenhaTamanhoMinimoException{
		 builder = new UsuarioBuilder();
		 usuario = null;
	}
	
	@Test
	public void deveCadastrarUsuario() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		
		Calendar dataDeNascimento = Calendar.getInstance();
		CidadeId cidadeId = new CidadeId();
		Usuario usuario = builder.nome("teste").
								  sobrenome("teste").
								  dataDeNascimento(dataDeNascimento).
								  email("teste@teste.com.br").
								  senha("123456").
								  cidadeId(cidadeId).
								  cadastrar();

		Assert.assertTrue(usuario.getNome().equals("teste"));
		Assert.assertTrue(usuario.getSobrenome().equals("teste"));
		Assert.assertTrue(usuario.getDataDeNascimento().equals(dataDeNascimento));
		Assert.assertTrue(usuario.getEmail().getValue().equals("teste@teste.com.br"));
		Assert.assertTrue(usuario.getSenha().getValue().equals("123456"));
		Assert.assertTrue(usuario.getSexo().equals(Sexo.MASCULINO));
		Assert.assertTrue(usuario.getCidadeId().equals(cidadeId));
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarNomeNulo() throws Exception {
		usuario = builder.nome(null).cadastrar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarNomeVazio() throws Exception {
		usuario = builder.nome("").cadastrar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSobrenomeNulo() throws Exception {
		usuario = builder.sobrenome(null).cadastrar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSobrenomeVazio() throws Exception {
		usuario = builder.sobrenome("").cadastrar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarDataDeNascimentoNula() throws Exception {
		usuario = builder.dataDeNascimento(null).cadastrar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarEmailNulo() throws Exception {
		usuario = builder.email(null).cadastrar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarSenhaNula() throws Exception {
		usuario = builder.senha(null).cadastrar();
	}
	
	@Test
	public void deveAlterarPerfil() throws Exception {
		usuario = builder.cadastrar();
		Calendar agora = Calendar.getInstance();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().dataDeNascimento(agora).getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
		Assert.assertTrue(usuario.getNome().equals("nomeAlterado"));
		Assert.assertTrue(usuario.getSobrenome().equals("sobrenomeAlterado"));
		Assert.assertTrue(usuario.getDataDeNascimento().equals(agora));	
		Assert.assertTrue(usuario.getSexo().equals(Sexo.MASCULINO));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAlterarComNomeVazio() throws Exception {
		usuario = builder.cadastrar();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().nome("").getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAlterarComNomeNull() throws Exception {
		usuario = builder.cadastrar();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().nome(null).getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAlterarComSobrenomeVazio() throws Exception {
		usuario = builder.cadastrar();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().sobrenome("").getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAlterarComSobrenomeNull() throws Exception {
		usuario = builder.cadastrar();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().sobrenome(null).getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAlterarComSexoVazio() throws Exception {
		usuario = builder.cadastrar();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().sexo("").getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAlterarComSexoNull() throws Exception {
		usuario = builder.cadastrar();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().sexo(null).getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAlterarComDataNascimentoNull() throws Exception {
		usuario = builder.cadastrar();
		AlterarPerfilCommand alterarPerfilCommand = builder.alterarPerfil().dataDeNascimento(null).getAlterarPerfilCommand();
		usuario.alterarPerfil(alterarPerfilCommand);
	}
	
	@Test
	public void deveCriarUsuarioFacebook() throws EmailFormatoException{
		Calendar nascimento = Calendar.getInstance();
		SalvarUsuarioFacebookCommand facebookCommand = new SalvarUsuarioFacebookCommand("ID_FACEBOOK", "Leonardo", "Scalabrini", nascimento, "Masculino", "leoanrdo_scalabrini@hotmail.com", "Maringá", "foto");
		Usuario usuario = new Usuario(facebookCommand);
		Assert.assertTrue(usuario.getNome().equals(facebookCommand.nome));
		Assert.assertTrue(usuario.getSobrenome().equals(facebookCommand.sobrenome));
		Assert.assertTrue(usuario.getDataDeNascimento().equals(facebookCommand.dataDeNascimento));
		Assert.assertTrue(usuario.getEmail().getValue().equals(facebookCommand.email));
		Assert.assertTrue(usuario.getSexo().equals(Sexo.MASCULINO));
	}

}
