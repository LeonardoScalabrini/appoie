package com.appoie.querys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.commands.FotoPublicacaoCommand;
import com.appoie.ids.PublicacaoId;
import com.appoie.utils.FotoRepository;

@Component
public class FotoPublicacaoQuery extends BasicQuery{

	@Autowired
	private FotoRepository fotoRepository;
	
	@SuppressWarnings("unchecked")
	public List<FotoPublicacaoCommand> getFotosPublicacaoCommand(PublicacaoId id) {
		
		Query query = em.createNativeQuery("select id, endereco from foto_Publicacao f "
										 + "where f.Publicacao_Id = :id");
		
		query.setParameter("id", id.getValue());
		List<Object[]> fotosPublicacao = query.getResultList();
		
		List<FotoPublicacaoCommand> fotosPublicacaoCommand = new ArrayList<>();
		
		for (Object[] fotoPublicacao : fotosPublicacao) {
			String foto = fotoRepository.getBase64(fotoPublicacao[1].toString());
			fotosPublicacaoCommand.add(new FotoPublicacaoCommand(fotoPublicacao[0].toString(), foto));
		}
		
		return fotosPublicacaoCommand; 
	}

}
