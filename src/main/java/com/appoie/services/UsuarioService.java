package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.CadastrarCommand;
import com.appoie.exceptions.CamposCadastrarException;
import com.appoie.exceptions.EmailCadastradoException;
import com.appoie.maps.UsuarioMap;
import com.appoie.models.Usuario;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioQuery usuarioQuery;

	public void cadastrar(CadastrarCommand command) throws CamposCadastrarException, EmailCadastradoException {
		
		Usuario usuario;
		try {
			UsuarioMap usuarioMap = new UsuarioMap();
			usuario = usuarioMap.map(command);
		} catch (Exception e) {
			// TODO: handle exception
			throw new CamposCadastrarException();
		}
		
		if(!usuario.getEmail().getValue().equals(command.confirmarEmail))
			throw new CamposCadastrarException();
			
		if(usuarioQuery.existeEmail(usuario.getEmail()))
			throw new EmailCadastradoException();
		
		usuarioRepository.save(usuario);
	}
	

}
