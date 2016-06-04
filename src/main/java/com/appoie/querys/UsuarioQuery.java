package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.models.Email;
//import com.appoie.models.Usuario;

@Component
public class UsuarioQuery extends BasicQuery{

	public Boolean exiteUsuario(String email, String senha){
		Query query =  em.createNativeQuery("select count(1) from usuario where email = :email and senha = :senha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		BigInteger quantidade = (BigInteger)query.getSingleResult();
		return quantidade.longValue() == 1L;
	}
	public Boolean existeEmail(Email email){
		Query query= em.createNativeQuery("select usuario from usuario where email = :email");
		query.setParameter("email", email);
		BigInteger quantidade =(BigInteger)query.getSingleResult();
		return quantidade.longValue()==1L;
	}
	
	
}
