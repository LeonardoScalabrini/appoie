package com.appoie.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.SalvarPublicacaoCommand;
import com.appoie.commands.EditarPublicacaoCommand;
import com.appoie.commands.FiltroCommand;
import com.appoie.dto.IconesDTO;
import com.appoie.dto.PublicacaoDetalhadaDTO;
import com.appoie.dto.PublicacaoMarcacaoDTO;
import com.appoie.dto.PublicacaoPreviaDTO;
import com.appoie.exceptions.FiltroCategoriaPublicacaoException;
import com.appoie.exceptions.FiltroTipoPublicacaoException;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.ApoiadorId;
import com.appoie.ids.CidadeId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Apoiador;
import com.appoie.models.Categoria;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;
import com.appoie.querys.PublicacaoQuery;
import com.appoie.repositorys.ApoiadorRepository;
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
	private ApoiadorRepository apoiadorRepository;
	
	@Autowired
	private FotoPublicacaoService fotoPublicacaoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private PublicacaoQuery publicacaoQuery;

	public void salvar(SalvarPublicacaoCommand command, Sessao sessao) throws QuantidadeFotosPublicacaoException {
		CidadeId cidadeId = cidadeService.getCidade(command.cidade, command.estado).getId();
		List<FotoPublicacao> fotosPublicacao = fotoPublicacaoService.salvar(command.fotos);
		Publicacao publicacao = new Publicacao(command, sessao.getUsuarioId(), cidadeId, fotoPublicacaoService.getFotosPublicacaoId(fotosPublicacao));
		publicacaoRepository.save(publicacao);
		fotosPublicacaoRepository.save(fotosPublicacao);
	}

	public void excluir(PublicacaoId id) {
		publicacaoRepository.delete(id);
	}

	public void editar(EditarPublicacaoCommand command) {
		Publicacao publicacao = publicacaoRepository.findOne(new PublicacaoId(command.id));
		publicacao.editar(command);
		publicacaoRepository.save(publicacao);
	}

	public List<PublicacaoMarcacaoDTO> getMarcadores(CidadeId cidadeId) {
		return publicacaoQuery.getMarcadores(cidadeId);
	}

	public PublicacaoPreviaDTO getPreviaPublicacao(PublicacaoId id, HttpSession session) {
		Sessao sessao = new Sessao(session);
		return publicacaoQuery.getPreviaPublicacao(id, sessao.getUsuarioId());
	}

	public PublicacaoDetalhadaDTO getDetalhesPublicacao(PublicacaoId id) {
		return publicacaoQuery.getDetalhesPublicacao(id);
	}

	public List<IconesDTO> getIconesDTO() {
		Categoria[] categorias = Categoria.values();
		List<IconesDTO> dto = new ArrayList<>();
		for (Categoria categoria : categorias) {
			dto.add(new IconesDTO(categoria));
		}
		return dto;
	}	

	public List<PublicacaoMarcacaoDTO> getMarcadoresFiltrados(CidadeId cidadeId, UsuarioId usuarioId, FiltroCommand command) {
		if(!command.filtrarMinhasPublicacoes) {
			usuarioId = null;
		}
		try {
			return publicacaoQuery.getMarcadoresFiltrados(cidadeId, usuarioId, command);
		} catch (FiltroCategoriaPublicacaoException | FiltroTipoPublicacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ApoiadorId apoiar(PublicacaoId publicacaoId, UsuarioId usuarioId) {
		Apoiador apoiador = new Apoiador(publicacaoId, usuarioId);
		Publicacao p = publicacaoRepository.findOne(publicacaoId);
		p.apoiar();
		apoiadorRepository.save(apoiador);
		publicacaoRepository.save(p);
		return apoiador.getId();
	}

	public void desapoiar(ApoiadorId id) {
		Apoiador a = apoiadorRepository.findOne(id);
		apoiadorRepository.delete(a);
	}

}
