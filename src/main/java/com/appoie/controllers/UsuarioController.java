package com.appoie.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.EmailCommand;
import com.appoie.commands.LoginCommand;
import com.appoie.commands.PerfilCommand;
import com.appoie.commands.SenhaCommand;
import com.appoie.ids.UsuarioId;
import com.appoie.commands.RecuperarSenhaCommand;
import com.appoie.services.UsuarioService;
import com.appoie.utils.UsuarioLogado;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.POST)
	public void cadastrar(@RequestBody CadastrarCommand command) throws Exception {
		usuarioService.cadastrar(command);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public void realizarLogin(@RequestBody LoginCommand loginCommand, HttpSession session) throws Exception{
		UsuarioLogado usuario = new UsuarioLogado(session);
		UsuarioId id = usuarioService.realizarLogin(loginCommand);
		usuario.setId(id);
		usuario.incrementaContPublicacoesRecuperadas();
	}
	
	@RequestMapping(value="/perfil", method=RequestMethod.GET)
	public @ResponseBody PerfilCommand buscarPerfil(HttpSession session) throws Exception{
		UsuarioId id = new UsuarioLogado(session).getId();
		return usuarioService.buscarPerfil(id);
		
	}
	
	@RequestMapping(value="/perfil", method=RequestMethod.PUT)
	public void alterarPerfil(@RequestBody PerfilCommand perfilCommand, HttpSession session) throws Exception{
		UsuarioId id = new UsuarioLogado(session).getId();
		usuarioService.alterarPerfil(perfilCommand, id);
		
	}

	@RequestMapping(value="/email", method=RequestMethod.PUT)
	public void alterarEmail(@RequestBody EmailCommand emailCommand, HttpSession session) throws Exception{
		UsuarioId id = new UsuarioLogado(session).getId();
		usuarioService.alterarEmail(emailCommand, id);
	}
	
	@RequestMapping(value="/senha", method=RequestMethod.PUT)
	public void alterarSenha(@RequestBody SenhaCommand senhaCommand, HttpSession session) throws Exception{
		UsuarioId id = new UsuarioLogado(session).getId();
		usuarioService.alterarSenha(senhaCommand, id);	
	}

	@RequestMapping(method = RequestMethod.POST, value = "/recuperarSenha")
	public void recuperarSenha(@RequestBody RecuperarSenhaCommand command) throws Exception {
		usuarioService.recuperarSenha(command);

	}
}
