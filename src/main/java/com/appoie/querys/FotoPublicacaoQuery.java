package com.appoie.querys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.commands.SalvarFotoPublicacaoCommand;
import com.appoie.ids.PublicacaoId;
import com.appoie.utils.FotoRepository;

@Component
public class FotoPublicacaoQuery extends BasicQuery{
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@SuppressWarnings("unchecked")
	public List<SalvarFotoPublicacaoCommand> getFotosPublicacaoCommand(PublicacaoId id) {
		
		Query query = em.createNativeQuery("select f.id, f.endereco from foto_Publicacao f inner join publicacao p on p.foto_publicacao_id = f.id"
										 + " where p.id = :id");
		
		query.setParameter("id", id.getValue());
		
		List<Object[]> fotosPublicacao = query.getResultList();
		
		List<SalvarFotoPublicacaoCommand> fotosPublicacaoCommand = new ArrayList<>();
		
		for (Object[] fotoPublicacao : fotosPublicacao) {
			String endereco = fotoRepository.getBase64(fotoPublicacao[1].toString());
			fotosPublicacaoCommand.add(new SalvarFotoPublicacaoCommand(fotoPublicacao[0].toString(), endereco));
		}
		
		return fotosPublicacaoCommand; 
	}

}
