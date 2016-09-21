package com.appoie.querys;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.commands.PublicacaoDetalhadaCommand;
import com.appoie.commands.PublicacaoMarcacaoCommand;
import com.appoie.commands.PublicacaoPreviaCommand;
import com.appoie.ids.CidadeId;
import com.appoie.ids.PublicacaoId;
import com.appoie.models.Categoria;
import com.appoie.models.Status;
import com.appoie.utils.FotoRepository;

@Component
public class PublicacaoQuery extends BasicQuery {
	
	@Autowired
	private FotoPublicacaoQuery fotoPublicacaoQuery;
	
	@Autowired 
	private FotoRepository fotoRepository;
	
	
	@SuppressWarnings("unchecked")
	public List<PublicacaoMarcacaoCommand> getMarcadoresCidadeId(CidadeId cidadeId) {
		
		Query query = em.createNativeQuery("select p.id, p.latitude, p.longitude, p.categoria, "
										   + "p.qtd_apoiadores from publicacao p where "
										   + "p.cidade_Id = :cidadeId");
		
		query.setParameter("cidadeId", cidadeId.getValue());
		List<Object[]> publicacoes = query.getResultList();
		
		List<PublicacaoMarcacaoCommand> commands = new ArrayList<>();
		for (Object[] publicacao : publicacoes) {
			
			Categoria categoria = Categoria.valueOf(publicacao[3].toString().toUpperCase());
			BigInteger qtdCurtidas = (BigInteger)publicacao[4];
			
		    commands.add(new PublicacaoMarcacaoCommand(publicacao[0].toString(), 
										   	   (Double)publicacao[1],
										   	   (Double)publicacao[2],
										   	   categoria,
										   	   qtdCurtidas.longValue()));
		}
		return commands; 
	}

	public PublicacaoPreviaCommand getPublicacaoPreviaCommand(PublicacaoId id) {
		Query query = em.createNativeQuery("(select p.id, p.titulo, p.qtd_apoiadores, p.status, f.endereco"
		          						   + " from publicacao p, foto_publicacao f "
		          						   + "where p.id = :id and p.id = f.publicacao_id) limit 1");
		query.setParameter("id", id.getValue());
		
		Object[] publicacao = (Object[]) query.getSingleResult();
		
		BigInteger qtdApoiadores = (BigInteger)publicacao[2];
		
		return new PublicacaoPreviaCommand(publicacao[0].toString(),
										   publicacao[1].toString(), 
										   qtdApoiadores.longValue(), 
										   Status.valueOf(publicacao[3].toString()), 
										   fotoRepository.getBase64(publicacao[4].toString()));
														
	}

	public PublicacaoDetalhadaCommand getPublicacaoDetalhadaCommand(PublicacaoId id) {
		Query query = em.createNativeQuery("select id, titulo, descricao, categoria, data_Publicacao, qtd_apoiadores, status"
										   + " from publicacao where id = :id");
		
		query.setParameter("id", id.getValue());
		
		Object[] publicacao = (Object[]) query.getSingleResult();
		
		return new PublicacaoDetalhadaCommand(publicacao[0].toString(),
										   	  publicacao[1].toString(), 
										   	  publicacao[2].toString(), 
										   	  publicacao[3].toString(), 
										   	  publicacao[4].toString(),
										   	  Integer.parseInt(publicacao[5].toString()),
										   	  Status.valueOf(publicacao[6].toString()),
										   	  fotoPublicacaoQuery.getFotosPublicacaoCommand(id));
	}
	
}
