package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.ids.PublicacaoId;
import com.appoie.models.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, PublicacaoId> {

}
