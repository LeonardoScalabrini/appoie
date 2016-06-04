package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.UsuarioCommand;
import com.appoie.exceptions.EmailJaCadastrado;
import com.appoie.models.Usuario;
import com.appoie.querys.UsuarioQuery;
import com.appoie.repositorys.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioQuery usuarioQuery;

	public void cadastrar(UsuarioCommand command) throws Exception {
		Usuario usuario=command.criarUsuario();
		if(usuarioQuery.existeEmail(usuario.getEmail())){
			throw new EmailJaCadastrado();
		}
		usuarioRepository.save(usuario);
	}

	
	
	

}
