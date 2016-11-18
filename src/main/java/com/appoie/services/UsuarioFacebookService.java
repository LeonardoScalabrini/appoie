package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.dto.InformacoesUsuarioDTO;
import com.appoie.models.Email;
import com.appoie.models.Usuario;
import com.appoie.models.UsuarioFacebook;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.UsuarioFacebookRepository;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.utils.Sessao;

@Service
public class UsuarioFacebookService {	
	@Autowired
	private UsuarioQuery usuarioQuery;
	
	@Autowired
	private UsuarioFacebookRepository facebookRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public InformacoesUsuarioDTO salvar(SalvarUsuarioFacebookCommand command) throws Exception {
		
		if (usuarioQuery.existeEmail(new Email(command.email))){
			Usuario usuario = usuarioQuery.buscar(new Email(command.email));
			Sessao.setUsuarioId(usuario.getId());
			Sessao.setCidadeId(usuario.getCidadeId());
			return usuarioQuery.buscarInformacoesDetalhadas(command.email, false);
		}
		
		Usuario usuario = new Usuario(command);
		UsuarioFacebook usuarioFacebook = new UsuarioFacebook(command, usuario.getId());
		
		facebookRepository.save(usuarioFacebook);
		usuarioRepository.save(usuario);
		Sessao.setUsuarioId(usuario.getId());
		Sessao.setCidadeId(usuario.getCidadeId());
		return usuarioQuery.buscarInformacoesDetalhadas(command.email, true);
	}
}
