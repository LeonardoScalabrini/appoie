package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.models.Cidade;

@Component
public class CidadeQuery extends BasicQuery {

	public boolean existe(String nomeCidade, String nomeEstado){
		Query query = em.createNativeQuery("select count(1) "
									   	   + "from cidade c, "
									   	        + "estado e where "
									   	                 + "c.estado_id = e.id and "
									   	                 + "c.nome = :nome and "
									   	                 + "e.nome = :nomeEstado", BigInteger.class);
		query.setParameter("nome", nomeCidade);
		query.setParameter("nomeEstado", nomeEstado);
		BigInteger quantidade = (BigInteger) query.getSingleResult();
		return quantidade.intValue() > 0 ; 
	}
	
	public Cidade buscar(String nome, String nomeEstado){
		Query query = em.createNativeQuery("select c.id, c.estado_id, c.nome"			
									   	   + "from cidade c, "
									   	        + "estado e where "
									   	                 + "c.estado_id = e.id and "
									   	                 + "c.nome = :nome and "
									   	                 + "e.nome = :nomeEstado", Cidade.class);
		query.setParameter("nome", nome);
		query.setParameter("nomeEstado", nomeEstado);
		return (Cidade) query.getSingleResult(); 
	}
	
	
}
