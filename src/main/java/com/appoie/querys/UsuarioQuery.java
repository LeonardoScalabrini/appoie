package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.dto.PerfilDTO;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.utils.FotoRepository;
import com.appoie.utils.SimpleCalendarFormat;

@Component
public class UsuarioQuery extends BasicQuery {
	
	@Autowired
	private FotoRepository fotoRepository;
	
	public UsuarioId buscar(Email email, Senha senha){
		Query query = em.createNativeQuery("select id "
				                          + " from usuario where "
				                                       + "email = :email and "
				                                       + "senha = :senha");
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
<<<<<<< HEAD
<<<<<<< HEAD
		String id = (String) query.getSingleResult();
		return new UsuarioId(id);
=======
		return new UsuarioId(query.getSingleResult().toString()); 
>>>>>>> c689ad998e6d1dda6c9864ec9d71c77ecc5d9757
=======
		return new UsuarioId(query.getSingleResult().toString()); 
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f
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

	public PerfilDTO getPerfil(UsuarioId id) {
		Query query = em.createNativeQuery("select u.id, u.nome, u.sobrenome, u.sexo, u.cep, "
				+ "u.cidade, u.estado, u.dataNascimento, f.endereco from usuario u fotoPerfil f where u.id = f.id and u.id = :id");
		query.setParameter("id", id);
		Object[] perfil = (Object[]) query.getSingleResult(); 
		
		return new PerfilDTO(perfil[0].toString(),
								 perfil[1].toString(),
								 perfil[2].toString(), 
								 perfil[3].toString(), 
								 perfil[4].toString(), 
								 perfil[5].toString(), 
								 perfil[6].toString(), 
								 SimpleCalendarFormat.parse(perfil[7].toString()),
								 fotoRepository.getBase64(perfil[8].toString()));
	}
}
