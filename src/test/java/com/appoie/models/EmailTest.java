package com.appoie.models;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.exceptions.EmailFormatoException;

public class EmailTest {

	private Email email;

	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarEmailNulo() throws Exception{
		email = new Email(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveAceitarEmailVazio() throws Exception{
		email = new Email("");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveAceitarEmailSemArroba() throws EmailFormatoException{
		email = new Email("teste");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveAceitarEmailComMaisDeUmaArroba() throws EmailFormatoException{
		email = new Email("teste@@");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveAceitarEmailComNadaAntesArroba() throws EmailFormatoException{
		email = new Email("@teste");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveAceitarEmailComNadaDepoisArroba() throws EmailFormatoException{
		email = new Email("teste@");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveAceitarEmailSemPonto() throws EmailFormatoException{
		email = new Email("teste@teste");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveAceitarEmailNadaDepoisDoPonto() throws EmailFormatoException{
		email = new Email("teste@teste.");
	}
	
	@Test
	public void DeveAceitarEmail() throws EmailFormatoException{
		
		String emailPontoCom = "teste@teste.com";
		Email email = new Email(emailPontoCom);
		email.setValue(emailPontoCom);
		Assert.assertTrue(email.getValue().equals(emailPontoCom));
		
		String emailPontoComPontoBr = "teste@teste.com.br";
		email = new Email(emailPontoComPontoBr);
		email.setValue(emailPontoComPontoBr);
		Assert.assertTrue(email.getValue().equals(emailPontoComPontoBr));
		
		String emailPontoAlgumaCoisa = "teste@teste.algumacoisa.com.br";
		email = new Email(emailPontoAlgumaCoisa);
		email.setValue(emailPontoAlgumaCoisa);
		Assert.assertTrue(email.getValue().equals(emailPontoAlgumaCoisa));
	}
	
	
}
