package com.appoie.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appoie.ids.FotoPublicacaoId;
import com.appoie.models.FotoPublicacao;

public interface FotoPublicacaoRepository extends JpaRepository<FotoPublicacao, FotoPublicacaoId> {

}
