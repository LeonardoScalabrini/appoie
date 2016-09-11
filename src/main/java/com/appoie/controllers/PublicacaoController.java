package com.appoie.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.commands.PublicacaoRecuperarCommand;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.PublicacaoId;
import com.appoie.services.PublicacaoService;
import com.appoie.utils.Sessao;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	PublicacaoService service;

	@RequestMapping(method = RequestMethod.PUT, value = "/editar")
	public void editar(@RequestBody PublicacaoEditarCommand command) {
		service.editar(command);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/recuperar")
	public List<PublicacaoRecuperarCommand> recuperar(HttpSession session) {
		return service.recuperar(new Sessao(session));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public void salvar(@RequestBody PublicacaoCommand command, HttpSession session) throws QuantidadeFotosPublicacaoException {
			service.salvar(command, new Sessao(session));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deletar(@PathVariable PublicacaoId id) {
			service.excluir(id);
	}
	
}
