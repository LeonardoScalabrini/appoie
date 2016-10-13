package com.appoie.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.appoie.ids.FotoPublicacaoId;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.TipoImagem;
import com.appoie.utils.FotoRepository;

@Service
public class FotoPublicacaoService {

	private FotoRepository repository = new FotoRepository(TipoImagem.PNG);
	
	public List<FotoPublicacao> salvar(List<String> fotos) {
		
		List<FotoPublicacao> fotosPublicacao = new ArrayList<>();
		for (String foto : fotos) {
			FotoPublicacaoId id = new FotoPublicacaoId();
			FotoPublicacao fotoPublicacao = new FotoPublicacao(id, repository.save(foto, id.getValue()));
			fotosPublicacao.add(fotoPublicacao);
		}
		
		return fotosPublicacao;
	}

	public List<FotoPublicacaoId> getFotosPublicacaoId(List<FotoPublicacao> fotosPublicacao) {
		
		List<FotoPublicacaoId> fotosPublicacaoId = new ArrayList<>();
		
		for (FotoPublicacao fotoPublicacao : fotosPublicacao) {
			fotosPublicacaoId.add(fotoPublicacao.getId());
		}
		return fotosPublicacaoId;
	}

}
