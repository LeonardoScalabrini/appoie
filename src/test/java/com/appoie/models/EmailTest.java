package com.appoie.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.appoie.builders.EmailBuilder;
import com.appoie.exceptions.EmailFormatoException;

public class EmailTest {

	private Email email;
	
	@Before
	public void antes(){
		email = null;
	}
	
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
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarEmailNulo() throws Exception{
		email = new EmailBuilder().criar();
		email.setValue(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nãoDeveSetarEmailVazio() throws Exception{
		email = new EmailBuilder().criar();
		email.setValue("");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveSetarEmailSemArroba() throws EmailFormatoException{
		email = new EmailBuilder().criar();
		email.setValue("teste");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveSetarEmailComMaisDeUmaArroba() throws EmailFormatoException{
		email = new EmailBuilder().criar();
		email.setValue("teste@@");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveSetarEmailComNadaAntesArroba() throws EmailFormatoException{
		email = new EmailBuilder().criar();
		email.setValue("@teste");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveSetarEmailComNadaDepoisArroba() throws EmailFormatoException{
		email = new EmailBuilder().criar();
		email.setValue("teste@");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveSetarEmailSemPonto() throws EmailFormatoException{
		email = new EmailBuilder().criar();
		email.setValue("teste@teste");
	}
	
	@Test(expected=EmailFormatoException.class)
	public void nãoDeveSetarEmailNadaDepoisDoPonto() throws EmailFormatoException{
		email = new EmailBuilder().criar();
		email.setValue("teste@teste.");
	}
	
	@Test
	public void deveAceitarEmailComPontoCom() throws EmailFormatoException{
		String emailPontoCom = "teste@teste.com";
		email = new Email(emailPontoCom);
		email.setValue(emailPontoCom);
		Assert.assertTrue(email.getValue().equals(emailPontoCom));
		
	}
	
	@Test
	public void deveAceitarEmailComPontoComPontoBr() throws EmailFormatoException{
		String emailPontoComPontoBr = "teste@teste.com.br";
		email = new Email(emailPontoComPontoBr);
		email.setValue(emailPontoComPontoBr);
		Assert.assertTrue(email.getValue().equals(emailPontoComPontoBr));
	}
	
	@Test
	public void deveAceitarEmailComAlgumaCoisa() throws EmailFormatoException{
		String emailPontoAlgumaCoisa = "teste@teste.algumacoisa.com.br";
		email = new Email(emailPontoAlgumaCoisa);
		email.setValue(emailPontoAlgumaCoisa);
		Assert.assertTrue(email.getValue().equals(emailPontoAlgumaCoisa));
	}
	
	
}
