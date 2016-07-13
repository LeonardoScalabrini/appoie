package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.ids.PublicacaoId;

@Component
public class PublicacaoQuery extends BasicQuery {
	public Boolean existe(PublicacaoId id) {
		Query query = em.createNativeQuery("select count(1) from publicacao where usuario_id = :id");
		query.setParameter("id", id.getId());
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.longValue() == 1L;
	}

}
