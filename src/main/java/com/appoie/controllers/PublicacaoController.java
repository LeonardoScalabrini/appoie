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
import com.appoie.exceptions.NumeroFotosPublicacaoInvalido;
import com.appoie.exceptions.PublicacaoNaoEncontradaException;
import com.appoie.ids.PublicacaoId;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;
import com.appoie.services.PublicacaoService;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	PublicacaoService service;

	@RequestMapping(method = RequestMethod.PUT, value = "/editar")
	public void editar(@RequestBody PublicacaoEditarCommand command, HttpSession session) {
		try {
			service.editar(command, session);
		} catch (PublicacaoNaoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/recuperar")
	public List<PublicacaoRecuperarCommand> recuperar() {
		return service.recuperar();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public void salvar(@RequestBody PublicacaoCommand command, HttpSession session) {
		try {
			service.salvar(command, session);
		} catch (NumeroFotosPublicacaoInvalido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/excluir")
	public void deletar(@PathVariable PublicacaoId id, HttpSession session) {
		try {
			service.excluir(id, session);
		} catch (PublicacaoNaoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
