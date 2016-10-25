package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.ids.CidadeId;
import com.appoie.models.Email;
@Component
public class UsuarioFacebookQuery extends BasicQuery {
	
	public Boolean existeUsuarioFacebook(Email email) {
		Query query = em.createNativeQuery("select count(1) from usuario_Facebook   where email = :email");
		query.setParameter("email",email.getValue());	
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.longValue() == 1L;
	}
	
	public CidadeId buscarCidadeId(String nome){		
		Query query = em.createNativeQuery("select id from Cidade  where nome = :nome");
		query.setParameter("nome", nome);
		return new CidadeId(query.getSingleResult().toString());
		
	}
	/*public UsuarioFacebookId buscarUsuarioId(Email email){
		Query query = em.createNativeQuery("select id from usuario_facebook where email = :email");
		query.setParameter("email", email.getValue());		
		return new UsuarioFacebookId(query.getSingleResult().toString()); 
	}
	
	/*public Usuario buscar(Email email){
		Query query =  em.createNativeQuery("select * from usuario_facebook where email = :email", UsuarioFacebook.class);
		query.setParameter("email", email.getValue());
		return (Usuario) query.getSingleResult();
	}*/
	
}
