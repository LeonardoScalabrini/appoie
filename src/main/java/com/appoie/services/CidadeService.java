package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Cidade;
import com.appoie.models.Estado;
import com.appoie.querys.CidadeQuery;
import com.appoie.repositorys.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeQuery cidadeQuery;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade getCidade(String nomeCidade, String nomeEstado){
		
		if (cidadeQuery.existe(nomeCidade, nomeEstado))
			return cidadeQuery.getCidade(nomeCidade, nomeEstado);
		
		Estado estado = estadoService.getEstado(nomeEstado);
		Cidade cidade = new Cidade(estado.getId(), nomeCidade);
		cidadeRepository.save(cidade);
		return cidade; 
	}

	public CidadeId getCidadeIdUsuario(UsuarioId id) {
		return cidadeQuery.getCidadeIdUsuario(id);
	}
}
