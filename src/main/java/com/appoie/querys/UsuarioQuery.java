package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.ids.UsuarioId;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;

@Component
public class UsuarioQuery extends BasicQuery {

	public UsuarioId buscar(Email email, Senha senha){
		Query query = em.createNativeQuery("select id from usuario where email = :email and senha = :senha", UsuarioId.class);
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
		return (UsuarioId) query.getSingleResult();
	}
	
	public Usuario buscar(Email email){
		Query query =  em.createNativeQuery("select * from usuario where email = :email", Usuario.class);
		query.setParameter("email", email.getValue());
		return (Usuario) query.getSingleResult();
	}
	
	public Boolean existeEmail(Email email){
		Query query = em.createNativeQuery("select count(1) from usuario where email = :email");
		query.setParameter("email", email.getValue());
		BigInteger quantidade =(BigInteger)query.getSingleResult();
		return quantidade.longValue() > 0L;
	}
	
	public Boolean exite(Email email, Senha senha) {
		Query query = em.createNativeQuery("select count(1) from usuario where email = :email and senha = :senha");
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.longValue() == 1L;
	}

	public String selectSenhaUsuarioByEmail(Email email) {
		Query query = em.createNativeQuery("select senha from usuario where email = :email");
		query.setParameter("email", email.getValue());
		return query.getSingleResult().toString();

	}

}
