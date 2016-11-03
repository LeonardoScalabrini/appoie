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
		usuarioService.autenticar(loginCommand);
	}
	
	@RequestMapping(value="/perfil", method=RequestMethod.GET)
	public @ResponseBody PerfilDTO buscarPerfil() throws Exception{
		return usuarioService.getPerfil();
	}
	
	@RequestMapping(value="/perfil", method=RequestMethod.PUT)
	public void alterarPerfil(@RequestBody PerfilDTO perfilCommand) throws Exception{
		usuarioService.alterarPerfil(perfilCommand);
	}

	@RequestMapping(value="/email", method=RequestMethod.PUT)
	public void alterarEmail(@RequestBody AlterarEmailCommand emailCommand) throws Exception{
		usuarioService.alterarEmail(emailCommand, Sessao.getUsuarioId());
	}
	
	@RequestMapping(value="/senha", method=RequestMethod.PUT)
	public void alterarSenha(@RequestBody AlterarSenhaCommand senhaCommand) throws Exception{
		usuarioService.alterarSenha(senhaCommand, Sessao.getUsuarioId());	
	}

	@RequestMapping(method = RequestMethod.POST, value = "/recuperarSenha")
	public void recuperarSenha(@RequestBody RecuperarSenhaCommand command) throws Exception {
		usuarioService.recuperarSenha(command);
	}
}