package com.appoie.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.exceptions.NumeroFotosPublicacaoInvalido;
import com.appoie.exceptions.PublicacaoNaoEncontradaException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;
import com.appoie.querys.PublicacaoQuery;
import com.appoie.repositorys.FotoPublicacaoRepository;
import com.appoie.repositorys.PublicacaoRepository;
import com.appoie.utils.UsuarioLogado;

@Service
public class PublicacaoService {
	@Autowired
	private PublicacaoRepository publicacaoRepo;

	@Autowired
	private FotoPublicacaoRepository fotoPublicacaoRepo;

	@Autowired
	private PublicacaoQuery publicacaoQuery;

	public void salvar(PublicacaoCommand command, HttpSession session) throws NumeroFotosPublicacaoInvalido {

		UsuarioLogado usuarioLogado = new UsuarioLogado(session);
		CidadeId cidadeId = usuarioLogado.getCidadeId();
		List<FotoPublicacao> fotos = new ArrayList<>();

		for (String fotoPublicacao : command.fotos) {
			fotos.add(new FotoPublicacao(fotoPublicacao));

		}
		Publicacao publicacao = new Publicacao(command, usuarioLogado.getId(), fotos, cidadeId);
		for (FotoPublicacao fotoPublicacao : fotos) {
			fotoPublicacao.setPublicacaoId(publicacao.getId());

		}
		publicacaoRepo.save(publicacao);
		fotoPublicacaoRepo.save(fotos);
	}

	public void excluir(PublicacaoId id, HttpSession session) throws PublicacaoNaoEncontradaException {
		if (!publicacaoQuery.existe(id)) {
			throw new PublicacaoNaoEncontradaException();

		} else {
			UsuarioLogado usuarioLogado = new UsuarioLogado(session);
			Publicacao publicacao = publicacaoRepo.findOne(id.toString());
			if (publicacao.getUsuarioId() != usuarioLogado.getId()) {
				return;
			} else {
				publicacaoRepo.delete(publicacao.getId().toString());
			}
		}

	}

	public List<Publicacao> recuperar() {
		return publicacaoRepo.findAll();

	}

	public List<FotoPublicacao> recuperarFotos(PublicacaoId idPublicacao) {
		return publicacaoQuery.recuperarFotosPublicacao(idPublicacao);
		 
	}

	public void editar(PublicacaoEditarCommand command, HttpSession session) throws PublicacaoNaoEncontradaException {
		if (!publicacaoQuery.existe(command.id)) {
			throw new PublicacaoNaoEncontradaException();

		} else {
			UsuarioLogado usuarioLogado = new UsuarioLogado(session);
			UsuarioId usuarioId = new UsuarioId(publicacaoQuery.recuperarIdUsuarioPublicacao(command.id));
			Publicacao publicacao = publicacaoQuery.recuperarPublicacao(command.id);		
			if (usuarioId != usuarioLogado.getId()) {
				return;
			} else {
				publicacaoRepo.save(new Publicacao(command, publicacao.getCidadeId(), publicacao.getDataPublicacao(), publicacao.getUsuarioId(), publicacao.getFotosId()));
			}
		}
		
	}

}
