package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.commands.AlterarPerfilCommand;
import com.appoie.dto.InformacoesUsuarioDTO;
import com.appoie.exceptions.SenhaTamanhoMinimoException;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Email;
import com.appoie.models.Senha;
import com.appoie.models.Usuario;
import com.appoie.utils.FotoRepository;
import com.appoie.utils.SimpleCalendarFormat;
import com.appoie.utils.Value;

@Component
public class UsuarioQuery extends BasicQuery {

	@Autowired
	private FotoRepository fotoRepository;

	public UsuarioId buscar(Email email, Senha senha) {
		Query query = em
				.createNativeQuery("select id " + " from usuario where " + "email = :email and " + "senha = :senha");
		query.setParameter("email", email.getValue());
		query.setParameter("senha", senha.getValue());
		return new UsuarioId(query.getSingleResult().toString());
	}

	public Usuario buscar(Email email) {
		Query query = em.createNativeQuery("select * from usuario where email = :email", Usuario.class);
		query.setParameter("email", email.getValue());
		return (Usuario) query.getSingleResult();
	}

	public Boolean existeEmail(Email email) {
		Query query = em.createNativeQuery("select count(1) from usuario where email = :email");
		query.setParameter("email", email.getValue());
		BigInteger quantidade = (BigInteger) query.getSingleResult();
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

	public AlterarPerfilCommand getPerfil(UsuarioId id) {
		Query query = em.createNativeQuery("select u.id, u.nome, u.sobrenome, u.sexo, u.cep, "
				+ "u.cidade, u.estado, u.dataNascimento, f.endereco from usuario u fotoPerfil f where u.id = f.id and u.id = :id");
		query.setParameter("id", id);
		Object[] perfil = (Object[]) query.getSingleResult();

		return new AlterarPerfilCommand(perfil[0].toString(), perfil[1].toString(), perfil[2].toString(), perfil[3].toString(),
				perfil[4].toString(), perfil[5].toString(), perfil[6].toString(),
				SimpleCalendarFormat.parse(perfil[7].toString()), fotoRepository.getBase64(perfil[8].toString()));
	}

	public void setPassword(String email, String password) {
		Query query = em.createNativeQuery("update usuario u set u.senha = :password where u.email = :email");
		query.setParameter("password", password);
		query.setParameter("email", email);
		query.executeUpdate();

	}

	public InformacoesUsuarioDTO buscarInformacoesDetalhadas(String email, boolean acesso) {
		Query query = em.createNativeQuery("select u.nome, u.sobrenome, u.email, c.nome as cidade, e.nome as estado from usuario u "
				+ "left join cidade c on (u.cidade_id = c.id) left join estado e on (c.estado_id = e.id ) "
				+ "where u.email = :email");
		query.setParameter("email", email);
		Object[] informacoesUsuario = (Object[]) query.getSingleResult();
		
		return new InformacoesUsuarioDTO(Value.StringOf(informacoesUsuario[0]), Value.StringOf(informacoesUsuario[1]),
				Value.StringOf(informacoesUsuario[2]), Value.StringOf(informacoesUsuario[3]), Value.StringOf(informacoesUsuario[4]),
				acesso);

	}
}
