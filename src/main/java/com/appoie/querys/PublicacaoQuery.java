package com.appoie.querys;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appoie.commands.FiltroCommand;
import com.appoie.commands.VerificaFechamentoPublicacaoCommand;
import com.appoie.dto.NotificacaoPublicacaoDTO;
import com.appoie.dto.PublicacaoDetalhadaDTO;
import com.appoie.dto.PublicacaoMarcacaoDTO;
import com.appoie.dto.PublicacaoPreviaDTO;
import com.appoie.exceptions.FiltroCategoriaPublicacaoException;
import com.appoie.exceptions.FiltroStatusException;
import com.appoie.exceptions.FiltroTipoPublicacaoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.NotificacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Categoria;
import com.appoie.models.CriticidadeProblema;
import com.appoie.models.Notificacao;
import com.appoie.models.Status;
import com.appoie.models.TipoImagem;
import com.appoie.repositorys.NotificacaoRepository;
import com.appoie.utils.FotoRepository;
import com.appoie.utils.Sessao;
import com.appoie.utils.SimpleCalendarFormat;

@Component
public class PublicacaoQuery extends BasicQuery {

	@Autowired
	private FotoPublicacaoQuery fotoPublicacaoQuery;

	@Autowired
	private NotificacaoRepository notificacaoRepo;

	private FotoRepository fotoRepository = new FotoRepository(TipoImagem.JPG);

	@SuppressWarnings("unchecked")
	public List<PublicacaoMarcacaoDTO> getMarcadores(CidadeId cidadeId) {

		Query query = em.createNativeQuery("select p.id, p.latitude, p.longitude, p.categoria, "
				+ "p.qtd_apoiadores from publicacao p where " + "p.cidade_Id = :cidadeId");

		query.setParameter("cidadeId", cidadeId.getValue());
		List<Object[]> publicacoes = query.getResultList();

		List<PublicacaoMarcacaoDTO> commands = new ArrayList<>();
		for (Object[] publicacao : publicacoes) {

			Categoria categoria = Categoria.valueOf(publicacao[3].toString().toUpperCase());
			BigInteger qtdCurtidas = (BigInteger) publicacao[4];

			commands.add(new PublicacaoMarcacaoDTO(publicacao[0].toString(), (Double) publicacao[1],
					(Double) publicacao[2], categoria, qtdCurtidas.longValue()));
		}
		return commands;
	}

	public PublicacaoPreviaDTO getPreviaPublicacao(PublicacaoId id, UsuarioId usuarioId) {
		Query query = em.createNativeQuery(
				"select  p.id, p.titulo, p.qtd_apoiadores, p.status, f.endereco, CASE WHEN a.usuario_id= :idUsuario THEN 'S' ELSE 'N' END, a.id as idApoiador, p.criticidade "
						+ "from publicacao p left join apoiador a on p.id = a.publicacao_id inner join foto_publicacao f on (p.foto_publicacao_id = f.id)"
						+ " where p.id = :id and p.foto_publicacao_id = f.id limit 1");
		query.setParameter("id", id.getValue());
		query.setParameter("idUsuario", usuarioId.getValue());

		Object[] publicacao = (Object[]) query.getSingleResult();

		BigInteger qtdApoiadores = (BigInteger) publicacao[2];

		return new PublicacaoPreviaDTO(publicacao[0].toString(), publicacao[1].toString(), qtdApoiadores.longValue(),
				Status.valueOf(publicacao[3].toString()), fotoRepository.getBase64(publicacao[4].toString()),
				publicacao[5].toString(), publicacao[6], CriticidadeProblema.valueOf(publicacao[7].toString()));

	}

	public PublicacaoDetalhadaDTO getDetalhesPublicacao(PublicacaoId id) {
		Query query = em.createNativeQuery(
				"select p.id, "
				+ "     p.titulo, "
				+ "     p.descricao, "
				+ "     p.categoria, "
				+ "     p.data_Publicacao, "
				+ "     p.qtd_apoiadores, "
				+ "     p.status, "
				+ "     p.criticidade, "
				+ "     CASE WHEN a.usuario_id= :idUsuario THEN 'S' ELSE 'N' END, "
				+ "     a.id as idApoiador,"
			    + "     c.nome as nomeCidade, "
                + "     e.nome as nomeEstado, "
            	+ "     u.nome as nomeUsuario, "
            	+ "     u.sobrenome as sobrenomeUsuario "
				+ " from publicacao p "
				+ " left join apoiador a on p.id = a.publicacao_id and a.usuario_id = :idUsuario"
				+"  inner join usuario u on u.id = p.usuario_id "
				+"  inner join cidade c on c.id = p.cidade_id "
				+"  inner join estado e on e.id = c.estado_id "
				+ "where p.id = :id");

		query.setParameter("id", id.getValue());
		query.setParameter("idUsuario", Sessao.getUsuarioId().getValue());

		Object[] publicacao = (Object[]) query.getSingleResult();
		
		System.out.println(publicacao);

		return new PublicacaoDetalhadaDTO(publicacao[0].toString(), publicacao[1].toString(), publicacao[2].toString(),
				publicacao[3].toString(), publicacao[4].toString(), Integer.parseInt(publicacao[5].toString()),
				Status.valueOf(publicacao[6].toString()), fotoPublicacaoQuery.getFotosPublicacaoCommand(id),
				CriticidadeProblema.valueOf(publicacao[7].toString()), publicacao[8].toString(), publicacao[9],publicacao[10].toString(), 
				publicacao[11].toString(), publicacao[12].toString(), publicacao[13].toString());
	}

	public boolean verificaListaSituacoes(List<String> lista) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).equalsIgnoreCase("aberto") || lista.get(i).equalsIgnoreCase("fechado"))
				return true;
		}
		return false;

	}

	@SuppressWarnings("unchecked")
	public List<PublicacaoMarcacaoDTO> getMarcadoresFiltrados(CidadeId cidadeId, UsuarioId usuarioId,
			FiltroCommand command)
					throws FiltroCategoriaPublicacaoException, FiltroTipoPublicacaoException, FiltroStatusException {

		Query query = null;
		boolean filtroPorData = true;
		boolean minhasPublicacoes = true;
		if (command.dataInicio == null || command.dataFim == null) {
			filtroPorData = false;
		}
		if (!command.filtrarMinhasPublicacoes) {
			minhasPublicacoes = false;
		}
		if (!verificaListaSituacoes(command.situacoes)) {
			throw new FiltroStatusException();
		}

		if (command.categorias.size() == 0) {
			throw new FiltroCategoriaPublicacaoException();
		} else if (command.situacoes.size() == 0) {
			throw new FiltroTipoPublicacaoException();
		}
		if (minhasPublicacoes && filtroPorData) {
			query = em.createNativeQuery("select p.id, p.latitude, p.longitude, p.categoria, "
					+ "p.qtd_apoiadores from publicacao p where " + "(" + "(p.cidade_Id = :cidadeId)"
					+ " and (p.data_publicacao between :dataInicio and :dataFim)" + " and (p.status in (:status))"
					+ " and (p.usuario_id = :idUsuario)" + " and (p.categoria in (:valoresCategorias))" + ")");

			query.setParameter("cidadeId", cidadeId.getValue());
			query.setParameter("dataInicio", command.dataInicio);
			query.setParameter("dataFim", command.dataFim);
			query.setParameter("status", command.situacoes);
			query.setParameter("idUsuario", usuarioId.getValue());
			query.setParameter("valoresCategorias", command.categorias);
		} else if (minhasPublicacoes && !filtroPorData) {
			query = em.createNativeQuery(
					"select p.id, p.latitude, p.longitude, p.categoria, " + "p.qtd_apoiadores from publicacao p where "
							+ "(" + "(p.cidade_Id = :cidadeId)" + " and (p.status in (:status)) "
							+ "and (p.usuario_id = :idUsuario) " + "and (p.categoria in (:valoresCategorias))" + ")");

			query.setParameter("cidadeId", cidadeId.getValue());
			query.setParameter("status", command.situacoes);
			query.setParameter("idUsuario", usuarioId.getValue());
			query.setParameter("valoresCategorias", command.categorias);
		}

		else {
			if(filtroPorData) {
				query = em.createNativeQuery("select p.id, p.latitude, p.longitude, p.categoria, "
						+ "p.qtd_apoiadores from publicacao p where " + "(" + "(p.cidade_Id = :cidadeId)"
						+ "and (p.status in (:status)) " + "and (p.categoria in (:valoresCategorias)) and (p.data_publicacao between :dataInicio and :dataFim))");

				query.setParameter("cidadeId", cidadeId.getValue());
				query.setParameter("status", command.situacoes);
				query.setParameter("valoresCategorias", command.categorias);
				query.setParameter("dataInicio", command.dataInicio);
				query.setParameter("dataFim", command.dataFim);
				
			}
			else {
				query = em.createNativeQuery("select p.id, p.latitude, p.longitude, p.categoria, "
						+ "p.qtd_apoiadores from publicacao p where " + "(" + "(p.cidade_Id = :cidadeId)"
						+ "and (p.status in (:status)) " + "and (p.categoria in (:valoresCategorias))" + ")");

				query.setParameter("cidadeId", cidadeId.getValue());
				query.setParameter("status", command.situacoes);
				query.setParameter("valoresCategorias", command.categorias);
				
			}
			
			
		}
		List<Object[]> publicacoes = query.getResultList();

		List<PublicacaoMarcacaoDTO> commands = new ArrayList<>();
		for (Object[] publicacao : publicacoes) {

			Categoria categoria = Categoria.valueOf(publicacao[3].toString().toUpperCase());
			BigInteger qtdCurtidas = (BigInteger) publicacao[4];

			commands.add(new PublicacaoMarcacaoDTO(publicacao[0].toString(), (Double) publicacao[1],
					(Double) publicacao[2], categoria, qtdCurtidas.longValue()));
		}
		return commands;
	}
	
	
	private void atualizaDataNotificacao(UsuarioId idUsuario, List<PublicacaoId> idsPublicacoes) {

		for (PublicacaoId idPublicacao : idsPublicacoes) {
			Query query = em.createNativeQuery(
					"select  n.id, n.data_proxima_notificacao, n.publicacao_id, n.usuario_id from notificacao n inner join publicacao p on (n.usuario_id = p.usuario_id and n.publicacao_id = p.id) "
							+ "where n.usuario_id = :idUsuario and n.publicacao_id = :idPublicacao LIMIT 1");
			query.setParameter("idUsuario", idUsuario.getValue());
			query.setParameter("idPublicacao", idPublicacao.getValue());

			Object[] notificacao = (Object[]) query.getSingleResult();

			Notificacao objNotificacao = new Notificacao(new NotificacaoId(notificacao[0].toString()),
					new SimpleCalendarFormat().parse(notificacao[1].toString()), new PublicacaoId(notificacao[2].toString()),
					new UsuarioId(notificacao[3].toString()));
						
			objNotificacao.getDataProximaNotificacao().add(Calendar.DAY_OF_MONTH, +7);
			notificacaoRepo.save(objNotificacao);
		}

	}

	public List<NotificacaoPublicacaoDTO> verificarFechamentoPublicacao(VerificaFechamentoPublicacaoCommand command) {
		Query query = em.createNativeQuery(
				"select  p.id, p.titulo from publicacao p inner join notificacao n on (p.usuario_id = n.usuario_id and p.id = n.publicacao_id) "
						+ "where p.usuario_id = :idUsuario and n.data_proxima_notificacao <= now()");
		query.setParameter("idUsuario", Sessao.getUsuarioId().getValue());

		@SuppressWarnings("unchecked")
		List<Object[]> publicacoes = query.getResultList();

		List<NotificacaoPublicacaoDTO> commands = new ArrayList<>();

		for (Object[] publicacao : publicacoes) {
			commands.add(new NotificacaoPublicacaoDTO(publicacao[0].toString(), publicacao[1].toString()));

		}
		
		
		List<PublicacaoId> idsPublicacoesNotificadas = new ArrayList<>();
		for (Object[] obj : publicacoes) {
			idsPublicacoesNotificadas.add(new PublicacaoId(obj[0].toString()));
			
		}
		
		//ao refatorar, colocar este método dentro do callback.success do serviço de verificação do status de fechamento da publicação
		atualizaDataNotificacao(Sessao.getUsuarioId(), idsPublicacoesNotificadas);

		return commands;

	}
	@Transactional
	public void destruirNotificacao(PublicacaoId id) {
		Query query = em.createNativeQuery("delete from notificacao where publicacao_id = :id_publicacao");
		query.setParameter("id_publicacao", id.getValue());		
		query.executeUpdate();	
		
	}
	

}
