package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.models.BasicEntity;

public abstract interface BasicRepository <Entity extends BasicEntity> extends JpaRepository<Entity, String>{

}
