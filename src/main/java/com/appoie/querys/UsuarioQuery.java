package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.models.Email;
import com.appoie.models.Senha;

@Component
public class UsuarioQuery extends BasicQuery{

	public Boolean exiteUsuario(Email email, Senha senha){
		Query query =  em.createNativeQuery("select count(1) from usuario where email = :email and senha = :senha");
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.intValue() == 1;
	}
	
}
