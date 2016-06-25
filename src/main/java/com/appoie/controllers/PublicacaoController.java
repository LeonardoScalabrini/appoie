package com.appoie.controllers;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.PublicacaoCommand;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {
	
	
	
	public void salvar(@RequestBody PublicacaoCommand command) {
		
		
		
	}
	
	
}
