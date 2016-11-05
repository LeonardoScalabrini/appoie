package com.appoie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.commands.SalvarPublicacaoCommand;
import com.appoie.commands.EditarPublicacaoCommand;
import com.appoie.commands.FiltroCommand;
import com.appoie.dto.IconesDTO;
import com.appoie.dto.PublicacaoDetalhadaDTO;
import com.appoie.dto.PublicacaoMarcacaoDTO;
import com.appoie.dto.PublicacaoPreviaDTO;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.ApoiadorId;
import com.appoie.ids.PublicacaoId;
import com.appoie.services.PublicacaoService;
import com.appoie.utils.Sessao;

@CrossOrigin
@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	private PublicacaoService service;

	@RequestMapping(method = RequestMethod.GET, value = "/icones")
	public List<IconesDTO> icone() {
		return service.getIconesDTO();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/editar")
	public void editar(@RequestBody EditarPublicacaoCommand command) {
		service.editar(command);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/marcadores")
	public List<PublicacaoMarcacaoDTO> recuperarMarcadores() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null||!authentication.isAuthenticated()){
			throw new AccessDeniedException("403 returned");
		}
		return service.getMarcadores(Sessao.getCidadeId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "detalhada/{id}")
	public PublicacaoDetalhadaDTO recuperarDetalhada(@PathVariable PublicacaoId id) {
		return service.getDetalhesPublicacao(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "previa/{id}")
	public PublicacaoPreviaDTO recuperarPrevia(@PathVariable PublicacaoId id) {
		return service.getPreviaPublicacao(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public void salvar(@RequestBody SalvarPublicacaoCommand command)
			throws QuantidadeFotosPublicacaoException {
		service.salvar(command);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/excluir/{id}")
	public void deletar(@PathVariable PublicacaoId id) {
		service.excluir(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/marcadores/filtrar")
	public List<PublicacaoMarcacaoDTO> recuperarMarcadoresFiltrados(@RequestBody FiltroCommand command) {
		return service.getMarcadoresFiltrados(command);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/apoiar/{id}")
	@ResponseBody
	public ApoiadorId apoiar(@PathVariable PublicacaoId id) {
		return service.apoiar(id, Sessao.getUsuarioId());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/desapoiar/{id}")
	public void desapoiar(@PathVariable ApoiadorId id) {
		service.desapoiar(id);
	}

}
