package com.appoie.querys;

import java.math.BigInteger;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.appoie.models.Estado;

@Component
public class EstadoQuery extends BasicQuery{

	public boolean existe(String nomeEstado){
		Query query = em.createNativeQuery("select count(1) "
									   	   + "from estado e where "
									   	   + "e.nome = :nomeEstado");
		
		query.setParameter("nomeEstado", nomeEstado);
		BigInteger quantidade = (BigInteger) query.getResultList().get(0);
		return quantidade.intValue() > 0 ; 
	}
	
	public Estado getEstado(String nomeEstado){
		Query query = em.createNativeQuery("select e.id, e.nome "			
									   	   + "from estado e where "
									   	   + "e.nome = :nomeEstado", Estado.class);
		
		query.setParameter("nomeEstado", nomeEstado);
		
		return (Estado) query.getSingleResult(); 
	}

}
