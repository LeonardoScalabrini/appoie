package com.appoie.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appoie.AppoieApplication;
import com.appoie.commands.LoginCommand;
import com.appoie.controllers.LoginController;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppoieApplication.class)
public class LoginServiceTest {
	@Autowired
	private LoginController targetController;
	
	
	

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void naoFazLoginSemEmailSenhaCadastrada() throws Exception {
		LoginCommand command =new LoginCommand("rafael3450@gmail.com.br","1222224");
		targetController.realizarLogin(command);
	}

}
