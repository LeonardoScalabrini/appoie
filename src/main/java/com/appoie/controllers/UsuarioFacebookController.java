package com.appoie.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.dto.InformacoesUsuarioDTO;
import com.appoie.services.UsuarioFacebookService;

@RestController
@RequestMapping("/usuarioFacebook")
public class UsuarioFacebookController {
	@Autowired
	private UsuarioFacebookService serviceFacebook;
	
	@RequestMapping(method = RequestMethod.POST,value = "/salvar")
	public InformacoesUsuarioDTO salvarUsuarioFacebook(@RequestBody SalvarUsuarioFacebookCommand command,HttpSession session) throws Exception {
		return serviceFacebook.salvar(command,session);
	}
	
	
}
