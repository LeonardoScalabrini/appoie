package com.appoie.services;

import org.springframework.stereotype.Service;

import com.appoie.ids.FotoPublicacaoId;
import com.appoie.models.FotoPublicacao;
import com.appoie.utils.FotoRepository;

@Service
public class FotoPublicacaoService {

	private FotoRepository repository = new FotoRepository();
	
	public FotoPublicacao salvar(String foto) {
		FotoPublicacaoId id = new FotoPublicacaoId();
		FotoPublicacao fotoPublicacao = new FotoPublicacao(id, repository.save(foto, id.getValue()));
		
		return fotoPublicacao;
	}
}
