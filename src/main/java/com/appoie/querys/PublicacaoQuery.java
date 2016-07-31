package com.appoie.querys;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.ids.CidadeId;
import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;

@Component
public class PublicacaoQuery extends BasicQuery {
	public Boolean existe(PublicacaoId id) {
		Query query = em.createNativeQuery("select count(1) from publicacao where id = :id");
		query.setParameter("id", id.getValue());
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.longValue() == 1L;
	}

	public List<FotoPublicacao> recuperarFotosPublicacao(PublicacaoId publicacaoId) {
		String id = publicacaoId.getValue();
		Query query = em.createNativeQuery("select * from foto_publicacao where publicacao_id = :id",
				FotoPublicacao.class);
		query.setParameter("id", id);
		/* List<FotoPublicacao> fotosPublicacao = (List<FotoPublicacao>) */
		return (List<FotoPublicacao>) query.getResultList();
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

	public List<Publicacao> recuperarPublicacoesPaginadas(int publicacoesPaginadas) {
		
		String sql = "select p.id, p.categoria, p.cidade_id, p.data_publicacao,"
				+ "		p.descricao, p.localizacao, p.titulo, p.usuario_id "
				+ "	 		from publicacao p order by data_publicacao"
				+ " limit 1 offset " + Integer.toString(publicacoesPaginadas);
		Query query = em.createNativeQuery(sql, Publicacao.class);
		//where p.cidade_id = :cidade_id
		//query.setParameter("numero_publicacoes_ja_recuperadas", publicacoesPaginadas);
		
		return (List<Publicacao>) query.getResultList();
		
	}

}
