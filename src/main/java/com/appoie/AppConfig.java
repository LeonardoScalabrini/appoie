package com.appoie;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.appoie.controllers.AutorizadorInterceptor;

//@Configuration 
//@EnableWebMvc   
public class AppConfig extends WebMvcConfigurerAdapter  {  
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(new AutorizadorInterceptor());
	}
}
