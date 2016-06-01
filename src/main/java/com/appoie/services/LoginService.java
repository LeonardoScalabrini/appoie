package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.querys.UsuarioQuery;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioQuery usuarioQuery;
	
	public void realizarLogin(Email email, Senha senha) throws Exception {
		
		if (!usuarioQuery.exiteUsuario(email, senha)){
			throw new Exception("O email e/ou senha estão incorretos!");
		}
	}

}
