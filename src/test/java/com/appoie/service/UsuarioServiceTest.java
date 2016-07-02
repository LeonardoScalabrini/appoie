package com.appoie.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appoie.AppoieApplication;
import com.appoie.builders.UsuarioBuilder;
import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.LoginCommand;
import com.appoie.controllers.UsuarioController;
import com.appoie.exceptions.EmailFormatoException;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Usuario;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.services.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppoieApplication.class)
public class UsuarioServiceTest {
	@Autowired
	private UsuarioController targetController;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService targetService;
	
	@Mock
	private UsuarioRepository targetRepositoryMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);		
		targetService.setUsuarioRepository(targetRepositoryMock);
	}

	@Test
	public void naoCadastraUsuarioComDadosInvalidos() throws Exception {
			
		
		Calendar dataDeNascimento = Calendar.getInstance();
		
		CadastrarCommand command=new CadastrarCommand("Rafael", "Nochelli da Silva", dataDeNascimento, "Masculino"
				,"rafaelnochellidasilva@gmail.com", "rafaelnochellidasilva@gmail.com","12345678" , "12345678");
		
		 targetController.cadastrar(command); 
		 
		
		ArgumentCaptor<Usuario> argUsuario =ArgumentCaptor.forClass(Usuario.class);
		verify(targetRepositoryMock).save(argUsuario.capture());
		
		verifyNoMoreInteractions(targetRepositoryMock);	
		
	}
	
	@Test
	public void paraRecuperarSenhaTemQueExistirOEmailCadastrado() throws Exception{	
		 
		//RecuperarSenhaCommand command1=new RecuperarSenhaCommand("rafaelnochellidasilva@gmail.com");
		
		//targetController.recuperarSenha(command1);	
	}
	
	@Test
	public void deveRealizarLogin() throws EmailFormatoException, SenhaTamanhoMinimoException, Exception{
		Usuario usuario = new UsuarioBuilder().criar();
		repository.save(usuario);
		try {
			LoginCommand command = new LoginCommand(usuario.getEmail().getValue(), usuario.getSenha().getValue());
			UsuarioId id = targetService.realizarLogin(command);
			Assert.assertTrue(usuario.getId().equals(id.getId()));
		} catch (Exception e) {
			// TODO: handle exception
			repository.delete(usuario);
		}
	}
	
}
