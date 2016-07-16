package com.appoie.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appoie.ids.UsuarioId;
import com.appoie.models.FotoPerfil;
import com.appoie.models.FotoPublicacao;
import com.appoie.repositorys.FotoPerfilRepository;
import com.appoie.repositorys.FotoPublicacaoRepository;
import com.appoie.utils.UsuarioLogado;



@RestController
@RequestMapping("/visualizarTimeline")
public class VisualizarTimelineController {

	@Autowired
	private FotoPerfilRepository fotoPerfilrepo;
	@Autowired
	private FotoPublicacaoRepository fotoPublicacaorepo;
	
	
	@RequestMapping(value="/foto_perfil", method=RequestMethod.GET)
	public byte[] buscarFotoPerfil(@RequestBody HttpSession session) throws Exception{
		UsuarioId id = new UsuarioLogado(session).getId();
		FotoPerfil foto=fotoPerfilrepo.findOne(id);
		return foto.foto;
	}
	
	@RequestMapping(value="/foto_publicacao", method=RequestMethod.GET)
	private byte[] buscarFotoPublicacao(@RequestBody HttpSession session)throws Exception{
		UsuarioId id =new UsuarioLogado(session).getId();
		FotoPublicacao foto = fotoPublicacaorepo.findOne(id.getId());
		return foto.foto;
	}
}
