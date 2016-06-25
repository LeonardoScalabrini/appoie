package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.models.Publicacao;
import com.appoie.repositorys.PublicacaoRepository;

@Service
public class PublicacaoService {
	@Autowired
	private PublicacaoRepository repo;
	
	public void salvar(PublicacaoCommand command) {
		repo.save(new Publicacao(command));
	}
	

}
