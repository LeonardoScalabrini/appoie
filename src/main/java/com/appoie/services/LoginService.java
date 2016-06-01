package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.exceptions.EmailSenhaInvalidoException;
import com.appoie.querys.UsuarioQuery;
import com.appoie.utils.ValidationString;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioQuery usuarioQuery;
	
	public void realizarLogin(String email, String senha) throws Exception {
		
		if (ValidationString.isNullOrEmpty(email)||ValidationString.isNullOrEmpty(senha)){
			throw new EmailSenhaInvalidoException();
		}
		
		if (!usuarioQuery.exiteUsuario(email, senha)){
			throw new EmailSenhaInvalidoException();
		}
	}

}
