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

import com.appoie.commands.SalvarPublicacaoCommand;
import com.appoie.commands.EditarPublicacaoCommand;
import com.appoie.dto.IconesDTO;
import com.appoie.dto.PublicacaoDetalhadaDTO;
import com.appoie.dto.PublicacaoMarcacaoDTO;
import com.appoie.dto.PublicacaoPreviaDTO;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.PublicacaoId;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;
import com.appoie.services.PublicacaoService;
import com.appoie.utils.Sessao;

@CrossOrigin
@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
	private PublicacaoService service;
	
	@RequestMapping(method = RequestMethod.GET, value = "/icones")
	public List<IconesDTO> icone(){
		return service.getIconesDTO();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/editar")
<<<<<<< HEAD
	public void editar(@RequestBody PublicacaoEditarCommand command, HttpSession session) {
		try {
			service.editar(command, session);
		} catch (PublicacaoNaoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

=======
	public void editar(@RequestBody EditarPublicacaoCommand command) {
		service.editar(command);
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f
	}

	@RequestMapping(method = RequestMethod.GET, value = "/marcadores")
	public List<PublicacaoMarcacaoDTO> recuperarMarcadores(HttpSession session) {
		Sessao sessao = new Sessao(session);
		return service.getMarcadores(sessao.getCidadeId());
	}

<<<<<<< HEAD
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
=======
	@RequestMapping(method = RequestMethod.GET, value = "detalhada/{id}")
	public PublicacaoDetalhadaDTO recuperarDetalhada(@PathVariable PublicacaoId id) {
		return service.getDetalhesPublicacao(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "previa/{id}")
	public PublicacaoPreviaDTO recuperarPrevia(@PathVariable PublicacaoId id) {
		return service.getPreviaPublicacao(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvar")
	public void salvar(@RequestBody SalvarPublicacaoCommand command, HttpSession session) throws QuantidadeFotosPublicacaoException {
		service.salvar(command, new Sessao(session));
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/excluir/{id}")
	public void deletar(@PathVariable PublicacaoId id) {
		service.excluir(id);
	}
	
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f
}
