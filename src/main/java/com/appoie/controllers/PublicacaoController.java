package com.appoie.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.IconeCommand;
import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoDetalhadaCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.commands.PublicacaoMarcacaoCommand;
import com.appoie.commands.PublicacaoPreviaCommand;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.PublicacaoId;
import com.appoie.services.PublicacaoService;
import com.appoie.utils.Sessao;

@CrossOrigin
@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	private PublicacaoService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/icone")
	public List<IconeCommand> icone(){
		return service.getIconesCommand();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/editar")
	public void editar(@RequestBody PublicacaoEditarCommand command) {
		service.editar(command);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/marcadores")
	public List<PublicacaoMarcacaoCommand> recuperarMarcadores(HttpSession session) {
		Sessao sessao = new Sessao(session);
		return service.getMarcadoresCidadeId(sessao.getCidadeId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "detalhada/{id}")
	public PublicacaoDetalhadaCommand recuperarDetalhada(@PathVariable PublicacaoId id) {
			return service.getPublicacaoDetalhadaCommand(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "previa/{id}")
	public PublicacaoPreviaCommand recuperarPrevia(@PathVariable PublicacaoId id) {
			return service.getPublicacaoPreviaCommand(id);
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
