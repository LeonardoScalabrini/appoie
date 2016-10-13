package com.appoie.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasicPage{
	
	private String senha = "123456";
	private String email = "teste@teste.com.br";
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void visita(){
		super.visita("");
	}
	
	public void entrar(){
		esperar();
		driver.findElement(By.name("loginForm")).click();
	}
	
	public CadastrarPage cadastrar(){
		esperar();
		driver.findElement(By.name("cadastrar-se")).click();
		return new CadastrarPage(driver);
	}
	
	public void preencher(){
		esperar();
		driver.findElement(By.name("emailLogin")).sendKeys(email);
		driver.findElement(By.name("senhaLogin")).sendKeys(senha);
	}
	
	public void logar(){
		esperar();
		driver.findElement(By.name("botaoEntrar")).click();
	}
	
	public HomePage email(String email){
		this.email = email;
		return this;
	}
	
	public HomePage senha(String senha){
		this.senha = senha;
		return this;
	}
	
}
