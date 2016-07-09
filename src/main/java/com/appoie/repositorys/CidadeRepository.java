package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.ids.CidadeId;
import com.appoie.models.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, CidadeId> {

}
