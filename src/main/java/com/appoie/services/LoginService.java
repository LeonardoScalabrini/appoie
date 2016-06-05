package com.appoie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appoie.commands.LoginCommand;
import com.appoie.exceptions.EmailSenhaInvalidoException;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.querys.UsuarioQuery;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioQuery usuarioQuery;
	
	public void realizarLogin(LoginCommand command) throws EmailSenhaInvalidoException{
		Email email;
		Senha senha;
		try {
			email = new Email(command.email);
			senha = new Senha(command.senha);
		} catch (Exception e) {
			throw new EmailSenhaInvalidoException();
		} 
		if (!usuarioQuery.exite(email, senha)){
			throw new EmailSenhaInvalidoException();
		}
	}

}
