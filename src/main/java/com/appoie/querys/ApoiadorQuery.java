package com.appoie.querys;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Apoiador;
import com.appoie.models.Usuario;
import com.appoie.repositorys.ApoiadorRepository;
import com.appoie.utils.Sessao;

@Component
public class ApoiadorQuery extends BasicQuery {
	@Autowired
	ApoiadorRepository apoiadorRepository;
	
	public Apoiador getApoiador(PublicacaoId publicacaoId) {
		Query query =  em.createNativeQuery("select * from apoiador where usuario_id = :usuarioId and publicacao_id = :publicacaoId", Apoiador.class);
		query.setParameter("usuarioId", Sessao.getUsuarioId().getValue());
		query.setParameter("publicacaoId", publicacaoId.getValue());
		return (Apoiador) query.getSingleResult();
	}

}
