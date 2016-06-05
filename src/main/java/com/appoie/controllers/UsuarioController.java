package com.appoie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.CadastrarCommand;
import com.appoie.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method=RequestMethod.POST)
	public void cadastrar(@RequestBody CadastrarCommand command) throws Exception{
		usuarioService.cadastrar(command);
	}
	
	
}
