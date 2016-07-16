package com.appoie.querys;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.ids.PublicacaoId;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;

@Component
public class PublicacaoQuery extends BasicQuery {
	public Boolean existe(PublicacaoId id) {
		Query query = em.createNativeQuery("select count(1) from publicacao where id = :id");
		query.setParameter("id", id.getId());
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.longValue() == 1L;
	}

	public List<FotoPublicacao> recuperarFotosPublicacao(PublicacaoId id) {
		Query query = em.createNativeQuery("select * from foto_publicacao where publicacao_id = :id",
				FotoPublicacao.class);
		query.setParameter("id", id);
		List<FotoPublicacao> fotosPublicacao = (List<FotoPublicacao>) query.getResultList();
		return fotosPublicacao;
	}
	

	public Publicacao recuperarPublicacao(PublicacaoId id) {
		Query query = em.createNativeQuery("select * from publicacao where id = :id", Publicacao.class);
		query.setParameter("id", id);
		return (Publicacao) query.getSingleResult();

	}
	
	public String recuperarIdUsuarioPublicacao(PublicacaoId id) {
		Query query = em.createNativeQuery("select usuario_id from publicacao where id = :id");
		query.setParameter("id", id);
		return query.getSingleResult().toString();

	}


}
