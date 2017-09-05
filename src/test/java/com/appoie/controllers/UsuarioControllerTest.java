package com.appoie.controllers;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appoie.AppoieApplication;
import com.appoie.builders.UsuarioBuilder;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.EstadoId;
import com.appoie.models.Cidade;
import com.appoie.models.Usuario;
import com.appoie.pages.CadastrarPage;
import com.appoie.pages.HomePage;
import com.appoie.repositorys.CidadeRepository;
import com.appoie.repositorys.UsuarioRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppoieApplication.class)
public class UsuarioControllerTest {
	
	private final String LOGIN_SENHA_INVALIDOS = "Email e/ou senha inválidos";
	private final String CAMPOS_ERRADOS = "Preencha os campos corretamente!";
	
	@Autowired
	private UsuarioRepository repository; 
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	private WebDriver driver;
	
	private HomePage home;
	
	private CadastrarPage cadastrarPage;
	
	@Before
	public void antes(){
		driver = new FirefoxDriver();
		home = new HomePage(driver);
		home.visita();
		home.entrar();
	}
	
	@After
	public void depois(){
		driver.close();
		repository.deleteAll();
	}
	
	@Test
	public void deveCadastrarUsuario(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		cadastrarPage.esperar();
		assertTrue(cadastrarPage.contem("Cadastrado com sucesso"));
	}
	
	@Test
	public void nãoDeveCadastrarSemNome(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.nome("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem("Informe o nome"));
	}
	
	@Test
	public void nãoDeveCadastrarComSobrenomeVazio(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.sobrenome("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem("Informe o sobrenome"));
	}
	
	@Test
	public void nãoDeveCadastrarSemDataDeNascimento(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.nascimento("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	@Test
	public void nãoDeveCadastrarSemInformarSexo(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.sexo("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	@Test
	public void nãoDeveCadastrarSemEmail(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.email("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem("Informe corretamente seu email"));
	}
	
	@Test
	public void nãoDeveCadastrarSemConfirmarEmail(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.confirmarEmail("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem("Informe corretamente seu email"));
	}
	
	@Test
	public void nãoDeveCadastrarComEmailDiferente(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.confirmarEmail("diferente@diferente.com.br");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	@Test
	public void nãoDeveCadastrarComEmailInvalido(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.email("emailInvalido");
		cadastrarPage.confirmarEmail("emailInvalido");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	@Test
	public void nãoDeveCadastrarSemSenha(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.senha("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem("Informe a senha"));
	}
	
	@Test
	public void nãoDeveCadastrarSemConfirmarSenha(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.confirmarSenha("");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	@Test
	public void nãoDeveCadastrarComSenhaDiferente(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.confirmarSenha("diferente");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	@Test
	public void nãoDeveCadastrarComSenhaMenorDe6Digitos(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.senha("12345");
		cadastrarPage.confirmarSenha("12345");
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	@Test
	public void nãoDeveCadastrarComCamposVazios(){
		cadastrarPage = home.cadastrar();
		cadastrarPage.enviar();
		assertTrue(cadastrarPage.contem(CAMPOS_ERRADOS));
	}
	
	
	public void nãoDeveCadastrarComEmailJaExistente() throws Exception{
		String email = "teste@teste.com.br";
		Usuario usuario = new UsuarioBuilder().email(email).criar();
		repository.save(usuario);
		
		cadastrarPage = home.cadastrar();
		cadastrarPage.email(email);
		cadastrarPage.confirmarEmail(email);
		cadastrarPage.preencher();
		cadastrarPage.enviar();
		cadastrarPage.esperar();
		assertTrue(cadastrarPage.contem("O email já existe no sistema!"));
	}
	
	@Test
	public void deveRealizarLogin() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		String email = "teste@teste.com.br";
		String senha = "123456";
		
		Cidade cidade = new Cidade(new EstadoId(), "Maringá");
		cidadeRepository.save(cidade);
		
		Usuario usuario = new UsuarioBuilder().cidadeId(cidade.getId()).criar();
		repository.save(usuario);
		
		home.email(email);
		home.senha(senha);
		home.preencher();
		home.logar();
		home.esperar();
		assertTrue(driver.getCurrentUrl().contains("home"));
		cidadeRepository.delete(cidade);
	}
	
	@Test
	public void naoDeveRealizarLoginComCamposVazios(){
		home.logar();
		assertTrue(home.contem(LOGIN_SENHA_INVALIDOS));
	}
	
	@Test
	public void naoDeveRealizarLoginSemEmail(){
		home.email("");
		home.preencher();
		home.logar();
		assertTrue(home.contem(LOGIN_SENHA_INVALIDOS));
	}
	
	@Test
	public void naoDeveRealizarLoginSemSenha(){
		home.senha("123456");
		home.preencher();
		home.logar();
		assertTrue(home.contem(LOGIN_SENHA_INVALIDOS));
	}

}
