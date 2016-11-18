package com.appoie.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.appoie.services.UsuarioService;

public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	UsuarioService usuarioService;
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		auth.setAuthenticated(false);
		return auth; 
	}

}
