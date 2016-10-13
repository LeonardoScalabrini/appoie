package com.appoie.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.commands.PublicacaoRecuperarCommand;
import com.appoie.exceptions.NumeroFotosPublicacaoInvalido;
import com.appoie.exceptions.PublicacaoNaoEncontradaException;
=======
import com.appoie.commands.SalvarPublicacaoCommand;
import com.appoie.commands.EditarPublicacaoCommand;
import com.appoie.dto.IconesDTO;
import com.appoie.dto.PublicacaoDetalhadaDTO;
import com.appoie.dto.PublicacaoMarcacaoDTO;
import com.appoie.dto.PublicacaoPreviaDTO;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f
import com.appoie.ids.CidadeId;
import com.appoie.ids.PublicacaoId;
import com.appoie.models.Categoria;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;
import com.appoie.querys.PublicacaoQuery;
import com.appoie.repositorys.FotoPublicacaoRepository;
import com.appoie.repositorys.PublicacaoRepository;
<<<<<<< HEAD
import com.appoie.utils.UsuarioLogado;
=======
import com.appoie.utils.Sessao;
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f

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

<<<<<<< HEAD
	public void salvar(PublicacaoCommand command, HttpSession session) throws NumeroFotosPublicacaoInvalido {

		UsuarioLogado usuarioLogado = new UsuarioLogado(session);
		CidadeId cidadeId = usuarioLogado.getCidadeId();
		List<FotoPublicacao> fotos = new ArrayList<>();

	
		for (String fotoPublicacao : command.fotos) {
			if(!fotoPublicacao.isEmpty()) 
				fotos.add(new FotoPublicacao(fotoPublicacao));

		}
		Publicacao publicacao = new Publicacao(command, usuarioLogado.getId(), fotos, cidadeId);
		for (FotoPublicacao fotoPublicacao : fotos) {
			fotoPublicacao.setPublicacaoId(publicacao.getId());

		}
		publicacaoRepo.save(publicacao);
		fotoPublicacaoRepo.save(fotos);
=======
	public void salvar(SalvarPublicacaoCommand command, Sessao sessao) throws QuantidadeFotosPublicacaoException {
		CidadeId cidadeId = cidadeService.getCidade(command.cidade, command.estado).getId();
		List<FotoPublicacao> fotosPublicacao = fotoPublicacaoService.salvar(command.fotos);
		Publicacao publicacao = new Publicacao(command, sessao.getUsuarioId(), cidadeId, fotoPublicacaoService.getFotosPublicacaoId(fotosPublicacao));
		publicacaoRepository.save(publicacao);
		fotosPublicacaoRepository.save(fotosPublicacao);
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f
	}

	public void excluir(PublicacaoId id) {
		publicacaoRepository.delete(id);
	}

<<<<<<< HEAD
	public List<PublicacaoRecuperarCommand> recuperar() {
		
		List<Publicacao> publicacoes = publicacaoRepo.findAll();
		List<PublicacaoRecuperarCommand> recuperarCommands = new ArrayList<>();
		for (Publicacao publicacao : publicacoes) {
			List<FotoPublicacao> fotos = new ArrayList<>();
			List<String> base64Fotos = new ArrayList<>();
		    fotos = publicacaoQuery.recuperarFotosPublicacao(publicacao.getId());
			for (FotoPublicacao fotoPublicacao : fotos) {
				base64Fotos.add(fotoPublicacao.getFoto());
=======
	public void editar(EditarPublicacaoCommand command) {
		Publicacao publicacao = publicacaoRepository.findOne(new PublicacaoId(command.id));
		publicacao.editar(command);
		publicacaoRepository.save(publicacao);
	}
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f

	public List<PublicacaoMarcacaoDTO> getMarcadores(CidadeId cidadeId) {
		return publicacaoQuery.getMarcadores(cidadeId);
	}

	public PublicacaoPreviaDTO getPreviaPublicacao(PublicacaoId id) {
		return publicacaoQuery.getPreviaPublicacao(id);
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
}
