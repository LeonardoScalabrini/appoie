package com.appoie.querys;

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

@Component
public class PublicacaoQuery extends BasicQuery {
	
	@Autowired
	private FotoPublicacaoQuery fotoPublicacaoQuery;
	
	@SuppressWarnings("unchecked")
	public List<PublicacaoMarcacaoCommand> buscarMarcadores(CidadeId cidadeId) {
		
		Query query = em.createNativeQuery("select p.id, p.latitude, p.longitude, p.categoria, p.qtd_Curtidas"
          + " from publicacao p where p.cidade_Id = :cidadeId");
		query.setParameter("cidadeId", cidadeId.getValue());
		List<Object[]> marcadores = query.getResultList();
		
		List<PublicacaoMarcacaoCommand> commands = new ArrayList<>();
		for (Object[] marcador : marcadores) {
			Categoria categoria = Categoria.valueOf(marcador[3].toString().toUpperCase());
			commands.add(new PublicacaoMarcacaoCommand(marcador[0].toString(), 
										   	   (Double)marcador[1],
										   	   (Double)marcador[2],
										   	   categoria,
										   	   categoria.getMarcador()));
		}
		
		return commands; 
	}

	public PublicacaoPreviaCommand buscarPrevia(PublicacaoId id) {
		Query query = em.createNativeQuery("select id, titulo, qtdeApoiares, status, foto"
		          + " from publicacao where id = :id");
		query.setParameter("id", id.getValue());
		
		Object[] objeto = (Object[]) query.getSingleResult();
		
		return new PublicacaoPreviaCommand(objeto[0].toString(),
										   objeto[1].toString(), 
										   Integer.parseInt(objeto[2].toString()), 
										   objeto[3].toString(), 
										   objeto[4].toString());
														
	}

	public PublicacaoDetalhadaCommand buscarDetalhada(PublicacaoId id) {
		Query query = em.createNativeQuery("select id, titulo, descricao, categoria, "
				+ "localizacao, dataPublicacao, qtdeApoiares, status"
		          + " from publicacao where id = :id");
		query.setParameter("id", id.getValue());
		
		Object[] publicacao = (Object[]) query.getSingleResult();
		
		return new PublicacaoDetalhadaCommand(publicacao[0].toString(),
										   	  publicacao[1].toString(), 
										   	  publicacao[2].toString(), 
										   	  publicacao[3].toString(), 
										   	  publicacao[4].toString(),
										   	  publicacao[5].toString(),
										   	  Integer.parseInt(publicacao[6].toString()),
										   	  publicacao[7].toString(),
										   	  fotoPublicacaoQuery.recuperarFotos(id));
	}
	
}
