package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.SalvarUsuarioFacebookCommand;
import com.appoie.models.UsuarioFacebook;
import com.appoie.repositorys.UsuarioFacebookRepository;

@Service
public class UsuarioFacebookService {
	@Autowired
	private UsuarioFacebookRepository repository;
	
	public void salvar(SalvarUsuarioFacebookCommand command){
		UsuarioFacebook usuarioFacebook =new UsuarioFacebook(command);
		repository.save(usuarioFacebook);
	}
}
