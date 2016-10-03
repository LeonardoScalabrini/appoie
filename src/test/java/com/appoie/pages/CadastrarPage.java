package com.appoie.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastrarPage extends BasicPage{
	
	private String nome = "usuarioTeste";
	private String sobrenome = "usuarioTeste";
	private String nascimento = "2000-01-01";
	private String sexo = "Masculino";
	private String email = "teste@teste.com.br";
	private String confirmarEmail = "teste@teste.com.br";
	private String senha = "123456";
	private String confirmarSenha = "123456";
	
	public CadastrarPage(WebDriver driver) {
		super(driver);
	}
	
	public void enviar(){
		esperar();
		driver.findElement(By.name("cadastrar")).click();
		
	}
	
	public void preencher(){
		esperar();
		driver.findElement(By.name("nome")).sendKeys(nome);
		driver.findElement(By.name("sobrenome")).sendKeys(sobrenome);
		driver.findElement(By.name("dataNascimento")).sendKeys(nascimento);
		driver.findElement(By.name("sexo")).sendKeys(sexo);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("confirmaEmail")).sendKeys(confirmarEmail);
		driver.findElement(By.name("senha")).sendKeys(senha);
		driver.findElement(By.name("confirmaSenha")).sendKeys(confirmarSenha);
		driver.findElement(By.name("cep")).sendKeys("87111220");
	}
	
	public CadastrarPage nome(String nome){
		this.nome = nome;
		return this;
	}
	
	public CadastrarPage sobrenome(String sobrenome){
		this.sobrenome = sobrenome;
		return this;
	}
	
	public CadastrarPage nascimento(String nascimento){
		this.nascimento = nascimento;
		return this;
	}
	
	public CadastrarPage sexo(String sexo){
		this.sexo = sexo;
		return this;
	}
	
	public CadastrarPage email(String email){
		this.email = email;
		return this;
	}
	
	public CadastrarPage confirmarEmail(String confirmarEmail){
		this.confirmarEmail = confirmarEmail;
		return this;
	}
	
	public CadastrarPage senha(String senha){
		this.senha = senha;
		return this;
	}
	
	public CadastrarPage confirmarSenha(String confirmarSenha){
		this.confirmarSenha = confirmarSenha;
		return this;
	}
}
