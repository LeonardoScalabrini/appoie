package com.appoie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.commands.PublicacaoRecuperarCommand;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.PublicacaoId;
import com.appoie.models.Publicacao;
import com.appoie.repositorys.PublicacaoRepository;
import com.appoie.utils.Sessao;

@Service
public class PublicacaoService {
	
	@Autowired
	private PublicacaoRepository publicacaoRepository;

	public void salvar(PublicacaoCommand command, Sessao sessao) throws QuantidadeFotosPublicacaoException {
		//Publicacao publicacao = new Publicacao(command, sessao.getUsuarioId() , sessao.getCidadeId());
		//publicacaoRepository.save(publicacao);
	}

	public void excluir(PublicacaoId id) {
		publicacaoRepository.delete(id);
	}

	public List<PublicacaoRecuperarCommand> recuperar(Sessao sessao) {
		return null;
	}

	public void editar(PublicacaoEditarCommand command) {
			Publicacao publicacao = publicacaoRepository.findOne(command.id);
			publicacao.editar(command);
			publicacaoRepository.save(publicacao);
	}
}
