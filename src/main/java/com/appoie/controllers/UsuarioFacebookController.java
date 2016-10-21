package com.appoie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.services.UsuarioFacebookService;

@RestController
@RequestMapping("/usuarioFacebook")
public class UsuarioFacebookController {
	@Autowired
	private UsuarioFacebookService serviceFacebook;
	
	@RequestMapping(method = RequestMethod.POST,value = "/salvar")
	public void salvarUsuarioFacebook(@RequestBody SalvarUsuarioFacebookCommand command) throws Exception {
		serviceFacebook.salvar(command);
	}
}
