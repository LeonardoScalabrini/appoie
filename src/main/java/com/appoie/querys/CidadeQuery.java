package com.appoie.querys;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.ids.CidadeId;

@Component
public class CidadeQuery extends BasicQuery {

	public CidadeId getCidadeId(String cidade) {
		Query query = em.createNativeQuery("select id from cidade where nome = :cidade");
		query.setParameter("cidade", cidade);
		return new CidadeId(query.getSingleResult().toString());

	}

}
