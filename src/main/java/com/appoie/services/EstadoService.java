package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.models.Estado;
import com.appoie.querys.EstadoQuery;
import com.appoie.repositorys.EstadoRepository;

@Component
public class EstadoService {

	@Autowired
	private EstadoQuery query;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado getEstado(String nomeEstado) {
		
		if(query.existe(nomeEstado))
			return query.getEstado(nomeEstado);
		
		Estado estado = new Estado(nomeEstado);
		estadoRepository.save(estado);
		return estado;
	}
}
