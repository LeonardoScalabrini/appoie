package com.appoie.querys;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BasicQuery {

	@Autowired
	protected EntityManager em;
	
}
