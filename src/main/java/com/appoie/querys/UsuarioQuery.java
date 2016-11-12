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
	
	public PerfilDTO buscar(Email email, Senha senha){
		Query query = em.createNativeQuery("select id, "
										  + " nome, sobrenome, sexo, data_de_nascimento"
				                          + " from usuario where "
				                                       + "email = :email and "
				                                       + "senha = :senha");
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
		Object[] perfil = (Object[]) query.getSingleResult();
		return new PerfilDTO(perfil[0].toString(),
				 perfil[1].toString(),
				 perfil[2].toString(), 
				 perfil[3].toString(), 
				 perfil[4].toString());
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
		Query query = em.createNativeQuery("select u.id, u.nome, u.sobrenome, u.sexo, u.data_de_Nascimento, "
				+ "f.endereco from usuario u LEFT JOIN foto_perfil f ON f.id = u.id WHERE u.id = :id");
		query.setParameter("id", id.getValue());
		Object[] perfil = (Object[]) query.getSingleResult();
		String enderecoFoto = "";
		if(perfil[5] != null){
			enderecoFoto = perfil[5].toString();
		}
		return new PerfilDTO(perfil[0].toString(),
								 perfil[1].toString(),
								 perfil[2].toString(), 
								 perfil[3].toString(), 
								 perfil[4].toString());
	}

	public void setPassword(String email, String password) {
		Query query = em.createNativeQuery("update usuario u set u.senha = :password where u.email = :email");
		query.setParameter("password", password);
		query.setParameter("email", email);
		query.executeUpdate();
				
	}
}
