package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.commands.PerfilCommand;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;

@Component
public class UsuarioQuery extends BasicQuery {

	public UsuarioId buscar(Email email, Senha senha){
		Query query = em.createNativeQuery("select id, "
				                                + "estado_id"
				                          + " from cidade where "
				                                       + "email = :email and "
				                                       + "senha = :senha");
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
		return new UsuarioId(query.getSingleResult().toString()); 
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
	
	public Boolean existe(Email email, Senha senha) {
		Query query = em.createNativeQuery("select count(1) from usuario where email = :email and senha = :senha");
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.longValue() == 1L;
	}

	public Senha buscarSenha(Email email) throws SenhaTamanhoMinimoException {
		Query query = em.createNativeQuery("select senha from usuario where email = :email");
		query.setParameter("email", email.getValue());
		String senha = query.getSingleResult().toString();
		return new Senha(senha); 
	}

	public PerfilCommand buscarPerfil(UsuarioId id) {
		return null;
	}
}
