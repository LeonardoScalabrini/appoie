package com.appoie.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasicPage {
	private final int UMSEGUNDO = 1000;
	protected String getLocalHost(){ return "http://localhost:9092/";};
	protected final WebDriver driver; 
	
	protected final void visita(String page){
		esperar();
		driver.get(getLocalHost() + page); 
	}
	
	public BasicPage(WebDriver driver) {
		this.driver = driver;
		driver.manage().window().maximize();
	}
	
	public final void esperar(){
		try {
			Thread.sleep(UMSEGUNDO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean contem(String mensagem){
		esperar();
		String pageSource = driver.getPageSource();
		return pageSource.contains(mensagem);
	}
}
