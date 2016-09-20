package com.appoie.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.IconeCommand;
import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoDetalhadaCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.commands.PublicacaoMarcacaoCommand;
import com.appoie.commands.PublicacaoPreviaCommand;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.PublicacaoId;
import com.appoie.models.Categoria;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;
import com.appoie.querys.PublicacaoQuery;
import com.appoie.repositorys.FotoPublicacaoRepository;
import com.appoie.repositorys.PublicacaoRepository;
import com.appoie.utils.Sessao;

@Service
public class PublicacaoService {
	
	@Autowired
	private PublicacaoRepository publicacaoRepository;
	
	@Autowired
	private FotoPublicacaoRepository fotosPublicacaoRepository;
	
	@Autowired
	private FotoPublicacaoService fotoPublicacaoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private PublicacaoQuery publicacaoQuery;

	public void salvar(PublicacaoCommand command, Sessao sessao) throws QuantidadeFotosPublicacaoException {
		CidadeId cidadeId = cidadeService.getCidade(command.cidade, command.estado).getId();
		List<FotoPublicacao> fotosPublicacao = fotoPublicacaoService.salvar(command.fotos);
		Publicacao publicacao = new Publicacao(command, sessao.getUsuarioId(), cidadeId, fotoPublicacaoService.getFotosPublicacaoId(fotosPublicacao));
		publicacaoRepository.save(publicacao);
		fotosPublicacaoRepository.save(fotosPublicacao);
	}

	public void excluir(PublicacaoId id) {
		publicacaoRepository.delete(id);
	}

	public void editar(PublicacaoEditarCommand command) {
			Publicacao publicacao = publicacaoRepository.findOne(new PublicacaoId(command.id));
			publicacao.editar(command);
			publicacaoRepository.save(publicacao);
	}

	public List<PublicacaoMarcacaoCommand> getMarcadoresCidadeId(CidadeId cidadeId) {
		return publicacaoQuery.getMarcadoresCidadeId(cidadeId);
	}

	public PublicacaoPreviaCommand getPublicacaoPreviaCommand(PublicacaoId id) {
		return publicacaoQuery.getPublicacaoPreviaCommand(id);
	}

	public PublicacaoDetalhadaCommand getPublicacaoDetalhadaCommand(PublicacaoId id) {
		return publicacaoQuery.getPublicacaoDetalhadaCommand(id);
	}

	public List<IconeCommand> getIconesCommand() {
		Categoria[] categorias = Categoria.values();
		List<IconeCommand> commands = new ArrayList<>();
		for (Categoria categoria : categorias) {
			commands.add(new IconeCommand(categoria));
		}
		return commands;
	}
}
