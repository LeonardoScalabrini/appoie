package com.appoie.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.exceptions.NumeroFotosPublicacaoInvalido;
import com.appoie.exceptions.PublicacaoNaoEncontradaException;
import com.appoie.ids.PublicacaoId;
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
		List<FotoPublicacao> fotos = new ArrayList<>();

		for (byte[] fotoPublicacao : command.fotos) {
			fotos.add(new FotoPublicacao(fotoPublicacao));

		}
		Publicacao publicacao = new Publicacao(command, usuarioLogado.getId(), fotos);
		for (FotoPublicacao fotoPublicacao : fotos) {
			fotoPublicacao.setPublicacaoId(publicacao.getId());

		}
		publicacaoRepo.save(publicacao);
		fotoPublicacaoRepo.save(fotos);
	}
	
	public void excluir(PublicacaoId id, HttpSession session) throws PublicacaoNaoEncontradaException {
		if(!publicacaoQuery.existe(id)) {
			throw new PublicacaoNaoEncontradaException();
			
		}
		else {
			UsuarioLogado usuarioLogado = new UsuarioLogado(session);
			Publicacao publicacao = publicacaoRepo.findOne(id.toString());
			if(publicacao.getUsuarioId() != usuarioLogado.getId()) {
				return;
			}
			else {
				publicacaoRepo.delete(publicacao.getId().toString());
			}
		}
		
		
		
		
		
	}

}
