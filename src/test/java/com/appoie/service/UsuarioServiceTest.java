package com.appoie.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Calendar;

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
import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.RecuperarSenhaCommand;
import com.appoie.controllers.UsuarioController;
import com.appoie.models.Usuario;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.services.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppoieApplication.class)
public class UsuarioServiceTest {
	@Autowired
	private UsuarioController targetController;
	
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

		 
		RecuperarSenhaCommand command1=new RecuperarSenhaCommand("rafael3450@gmail.com.br");
		
		targetController.recuperarSenha(command1);	
		
		
	}
	@Test
	public void emailDeConfirmacao(){
		
		
	}
	

}
