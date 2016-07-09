package com.appoie.models;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.appoie.AppoieApplication;
import com.appoie.commands.CadastrarCommand;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =AppoieApplication.class)
@WebAppConfiguration
public class UsuarioFuncionalTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		mockMvc =MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	@Rollback
	public void cadastrarUsuario()throws Exception{
		Calendar dataDeNascimento = Calendar.getInstance();
		CadastrarCommand command =new CadastrarCommand("Rafael", "Nochelli da Silva", dataDeNascimento, "Masculino"
				,"rafaelnochellidasilva@gmail.com", "rafaelnochellidasilva@gmail.com","12345678" , "12345678");
		String json = new ObjectMapper().writeValueAsString(command);
		
		MockHttpServletRequestBuilder request = post("/usuario").content(json).contentType(MediaType.APPLICATION_JSON);
		ResultActions result =mockMvc.perform(request);
		
		result.andExpect(status().isOk());
<<<<<<< HEAD
		result.andReturn().getResponse();
		Usuario usuario= (Usuario) usuarioRepository.findAll();
		assertThat(usuario.getNome()).isEqualTo("rafael");
		assertThat(usuario.getSobrenome()).isEqualTo("Nochelli da Silva");
		
		
=======
		//Usuario usuario= usuarioRepository.findOne("rafaelnochellidasilva@gmail.com");
		//assertThat(usuario.getNome()).isEqualTo("rafael");
		//assertThat(usuario.getSobrenome()).isEqualTo("Nochelli da Silva");
>>>>>>> c72c8b950a938951549416e14a65af3c8d072e39
	}

}
