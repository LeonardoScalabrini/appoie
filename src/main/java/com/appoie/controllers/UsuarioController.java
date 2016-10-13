package com.appoie.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.CadastrarCommand;
import com.appoie.commands.AlterarEmailCommand;
import com.appoie.commands.AutenticarCommand;
import com.appoie.commands.AlterarSenhaCommand;
import com.appoie.ids.UsuarioId;
import com.appoie.commands.RecuperarSenhaCommand;
import com.appoie.dto.PerfilDTO;
import com.appoie.services.UsuarioService;
import com.appoie.utils.Sessao;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.POST)
	public void cadastrar(@RequestBody CadastrarCommand command) throws Exception {
		usuarioService.cadastrar(command);
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public void realizarLogin(@RequestBody AutenticarCommand loginCommand, HttpSession session) throws Exception{
		usuarioService.autenticar(loginCommand, session);
	}
	
	@RequestMapping(value="/perfil", method=RequestMethod.GET)
	public @ResponseBody PerfilDTO buscarPerfil(HttpSession session) throws Exception{
		UsuarioId id = new Sessao(session).getUsuarioId();
		return usuarioService.getPerfil(id);
	}
	
	@RequestMapping(value="/perfil", method=RequestMethod.PUT)
	public void alterarPerfil(@RequestBody PerfilDTO perfilCommand, HttpSession session) throws Exception{
		UsuarioId id = new Sessao(session).getUsuarioId();
		usuarioService.alterarPerfil(perfilCommand, id);
	}

	@RequestMapping(value="/email", method=RequestMethod.PUT)
	public void alterarEmail(@RequestBody AlterarEmailCommand emailCommand, HttpSession session) throws Exception{
		UsuarioId id = new Sessao(session).getUsuarioId();
		usuarioService.alterarEmail(emailCommand, id);
	}
	
	@RequestMapping(value="/senha", method=RequestMethod.PUT)
	public void alterarSenha(@RequestBody AlterarSenhaCommand senhaCommand, HttpSession session) throws Exception{
		UsuarioId id = new Sessao(session).getUsuarioId();
		usuarioService.alterarSenha(senhaCommand, id);	
	}

	@RequestMapping(method = RequestMethod.POST, value = "/recuperarSenha")
	public void recuperarSenha(@RequestBody RecuperarSenhaCommand command) throws Exception {
		usuarioService.recuperarSenha(command);
	}
}