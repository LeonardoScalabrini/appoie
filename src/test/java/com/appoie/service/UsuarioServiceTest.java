/*package com.appoie.service;
import static com.appoie.utils.ValidationString.isNullOrEmpty;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
import com.appoie.builders.FotoPerfilBuilder;
import com.appoie.builders.UsuarioBuilder;
import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.EmailCommand;
import com.appoie.commands.LoginCommand;
import com.appoie.commands.PerfilCommand;
import com.appoie.commands.RecuperarSenhaCommand;
import com.appoie.commands.SenhaCommand;
import com.appoie.controllers.UsuarioController;
import com.appoie.exceptions.CamposCadastrarException;
import com.appoie.exceptions.EmailSenhaInvalidoException;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Email;
import com.appoie.models.FotoPerfil;
import com.appoie.models.Senha;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;
import com.appoie.repositorys.FotoPerfilRepository;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.services.UsuarioService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppoieApplication.class)
public class UsuarioServiceTest {
	@Autowired
	private UsuarioController targetController;	
	@Autowired
	private UsuarioService targetService;	
	@Autowired
	private UsuarioRepository targetRepository;	
	@Autowired
	private FotoPerfilRepository targetRepositoryFotoPerfil;	
	@Mock
	private UsuarioRepository targetRepositoryMock;	
	Usuario usuarioBuilder;
	
	
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);		
		targetService.setUsuarioRepository(targetRepositoryMock);
	    usuarioBuilder = new UsuarioBuilder().criar();	    
	    targetRepository.deleteAll();
	}

	@Test
	public void naoCadastraUsuarioComDadosInvalidos() throws Exception {
			
		Calendar dataDeNascimento =Calendar.getInstance();
		CadastrarCommand command =new CadastrarCommand("Rafael", "Nochelli da Silva", dataDeNascimento, "MASCULINO",
		       	                                       "rafael@gmail.com", "rafael@gmail.com", "1234567", "1234567");
		targetController.cadastrar(command);
		ArgumentCaptor<Usuario> argUsuario =ArgumentCaptor.forClass(Usuario.class);
		verify(targetRepositoryMock).save(argUsuario.capture());		
		Usuario usuarioSalvo =argUsuario.getValue();
		assertThat(usuarioSalvo.getNome()).isEqualTo("Rafael");
		assertThat(usuarioSalvo.getSobrenome()).isEqualTo("Nochelli da Silva");
		assertThat(usuarioSalvo.getDataDeNascimento()).isEqualTo(dataDeNascimento);
		assertThat(usuarioSalvo.getSexo()).isEqualTo(Sexo.MASCULINO);
		assertThat(usuarioSalvo.getEmail().getValue()).isEqualTo("rafael@gmail.com");
		assertThat(usuarioSalvo.getSenha().getValue()).isEqualTo("1234567");	
		
	}
	 @Test(expected=EmailSenhaInvalidoException.class)
	 public void soRealizaLoginUsuarioComCadastrado() throws Exception{
		 LoginCommand command = new LoginCommand("","");		 
		 targetService.realizarLogin(command);
	 }
	 
	    @Test
	    public void deveRealizarLogin() throws Exception{ 		    
		    targetRepository.save(usuarioBuilder);
		    LoginCommand command = new LoginCommand(usuarioBuilder.getEmail().getValue(), usuarioBuilder.getSenha().getValue());
		    UsuarioId id = targetService.realizarLogin(command);  
		    assertEquals(usuarioBuilder.getId().getId(),id.getId());
		    assertThat(usuarioBuilder.getId().getId()).isEqualTo(id.getId());   
		   
	    }
	
	@Test
	public void paraRecuperarSenhaTemQueExistirOEmailCadastrado() throws Exception{			
		targetRepository.save(usuarioBuilder);
		Usuario usuario =targetRepository.findOne(usuarioBuilder.getId());
		RecuperarSenhaCommand command=new RecuperarSenhaCommand(usuarioBuilder.getEmail().getValue());
		targetService.recuperarSenha(command);
		assertEquals(command.email, usuario.getEmail().getValue());		
	}
	
	@Test
	public void paraAlterarUmPerfilTemqueExistirUmUsuario()  throws CamposCadastrarException {
		targetRepository.save(usuarioBuilder);
		Usuario usuario =targetRepository.findOne(usuarioBuilder.getId());		
		assertEquals(usuarioBuilder.getId().getId(), usuario.getId().getId());
		assertEquals(usuarioBuilder.getNome(), usuario.getNome());
		assertTrue(usuario.getSobrenome().equals(usuarioBuilder.getSobrenome()));		
	}
	
	@Test
	public void deveAlerarPerfil() throws Exception {
		Email emailMock =mock(Email.class);
		when(emailMock.getValue()).thenReturn("rafael@gmail.com");
		Senha senhaMock=mock(Senha.class);
		when(senhaMock.getValue()).thenReturn("12345678910");
		Usuario usuarioMock =mock(Usuario.class);
		when(usuarioMock.getNome()).thenReturn("Rafael");
		when(usuarioMock.getSobrenome()).thenReturn("Nochelli");
		when(usuarioMock.getDataDeNascimento()).thenReturn(Calendar.getInstance());
		when(usuarioMock.getSexo()).thenReturn(Sexo.MASCULINO);
		when(usuarioMock.getEmail()).thenReturn(emailMock);
		when(usuarioMock.getSenha()).thenReturn(senhaMock);
		targetRepository.save(usuarioBuilder);
		Usuario usuario =targetRepository.findOne(usuarioBuilder.getId());
		FotoPerfil fotoPerfil = new FotoPerfilBuilder().criar(usuarioBuilder.getId());				
		PerfilCommand command =new PerfilCommand(usuarioMock, fotoPerfil);
		usuario.alterarPerfil(command);
		targetRepository.save(usuario);		
		targetRepositoryFotoPerfil.save(fotoPerfil);
		Usuario novoUsuario =targetRepository.findOne(usuarioBuilder.getId());
		assertEquals(usuarioMock.getNome(), novoUsuario.getNome());
		assertEquals(usuarioMock.getSobrenome(), novoUsuario.getSobrenome());		
		assertEquals(Sexo.MASCULINO, novoUsuario.getSexo());		

	}
	
	@Test
	public void paraAlterarEmailTemQueExistirnoBanco() throws Exception {	
		targetRepository.save(usuarioBuilder);
		Usuario usuario = targetRepository.findOne(usuarioBuilder.getId());
		EmailCommand command = new EmailCommand(usuarioBuilder.getEmail().getValue(),"rafael@gmail.com","rafael@gmail.com");
		assertEquals(command.emailAtual,usuario.getEmail().getValue());		
	}
	
	@Test(expected =IllegalArgumentException.class)
	public void paraAlterarOEmailAtualDeveSerInformado() throws Exception {		
		EmailCommand command = new EmailCommand("", "rafael@teste.com", "rafael@teste.com");		
		isNullOrEmpty(command.emailAtual);		
	}
	
	@Test(expected =IllegalArgumentException.class)
	public void naoAlterarOEmail() throws CamposCadastrarException {		
		EmailCommand command = new EmailCommand(null,null,null);		
		isNullOrEmpty(command.emailAtual);
		isNullOrEmpty(command.confirmarNovoEmail);
		isNullOrEmpty(command.novoEmail);		
	}
	@Test
	public void deveAlterarEmaildoUsuario() throws Exception {
		targetRepository.save(usuarioBuilder);
		Usuario usuario =targetRepository.findOne(usuarioBuilder.getId());
		EmailCommand command = new EmailCommand("test@test.com.", "rafael@teste.com", "rafael@teste.com");		
		Email email =usuario.getEmail();
		email.setValue(command.novoEmail);
		targetRepository.save(usuario);
		Usuario usuarioEmailAlterado =targetRepository.findOne(usuarioBuilder.getId());
		assertEquals(command.novoEmail, usuarioEmailAlterado.getEmail().getValue());
	}
	
	@Test
	public void paraAlterarSenhaTemQueExistirnoBanco() throws Exception {	
		targetRepository.save(usuarioBuilder);
		Usuario usuario = targetRepository.findOne(usuarioBuilder.getId());
		SenhaCommand  command =new SenhaCommand(usuarioBuilder.getSenha().getValue(), "12345678910", "12345678910");
		assertEquals(command.senhaAtual,usuario.getSenha().getValue());		
	}
	
	@Test(expected =IllegalArgumentException.class)
	public void paraAlterarASenhaAtualDeveSerInformado() throws Exception {		
		SenhaCommand command = new SenhaCommand("", "223444533", "223444533");		
		isNullOrEmpty(command.senhaAtual);
		
	}
	
	@Test(expected =IllegalArgumentException.class)
	public void naoAlterarASenha() throws CamposCadastrarException {		
		SenhaCommand command = new SenhaCommand(null,null,null);		
		isNullOrEmpty(command.senhaAtual);
		isNullOrEmpty(command.novaSenha);
		isNullOrEmpty(command.confirmarNovaSenha);		
		
	}
	@Test
	public void deveAlterarSenhadoUsuario() throws Exception {
		targetRepository.save(usuarioBuilder);
		Usuario usuario =targetRepository.findOne(usuarioBuilder.getId());
		SenhaCommand command = new SenhaCommand("123456", "1234567", "1234567");		
		Senha senha =usuario.getSenha();
		senha.setSenha(command.novaSenha);
		targetRepository.save(usuario);
		Usuario usuarioSenhaAlterada =targetRepository.findOne(usuarioBuilder.getId());
		assertEquals(command.novaSenha, usuarioSenhaAlterada.getSenha().getValue());
	}
	@Test
	public void deveBuscarperfil() throws Exception {
		FotoPerfil fotoPerfil = new FotoPerfilBuilder().criar(usuarioBuilder.getId());
		targetRepositoryFotoPerfil.save(fotoPerfil);
		targetRepository.save(usuarioBuilder);		
		Usuario usuario =targetRepository.findOne(usuarioBuilder.getId());
		FotoPerfil foto =targetRepositoryFotoPerfil.findOne(usuario.getId());
		assertEquals(fotoPerfil.getId(), foto.getId());
		assertEquals(usuarioBuilder.getId(),usuario.getId() );
	}
	
		
	}
	
  
	
	
	*/
	
	


