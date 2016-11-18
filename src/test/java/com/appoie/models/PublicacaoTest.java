package com.appoie.models;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.builders.PublicacaoBuilder;
import com.appoie.commands.EditarPublicacaoCommand;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.UsuarioId;

public class PublicacaoTest {

	@Test
	public void deveCriar() throws QuantidadeFotosPublicacaoException{
		
		UsuarioId usuarioId = new UsuarioId();
		CidadeId cidadeId = new CidadeId();
		FotoPublicacaoId fotoId = new FotoPublicacaoId();
		Double latitude = 2.5;
		Double longitude = 2.5;
		CriticidadeProblema criticidadeProblema = CriticidadeProblema.ALTA;
		Publicacao publicacao = new PublicacaoBuilder()
									.usuarioId(usuarioId)
									.cidadeId(cidadeId)
									.latitude(latitude)
									.longitude(longitude)
									.latitude(latitude)
									.fotoPublicacaoId(fotoId)
									.criticiade(criticidadeProblema)
									.criar();
		Calendar hoje = Calendar.getInstance();
		Assert.assertTrue(publicacao.getTitulo().equals("titulo"));
		Assert.assertTrue(publicacao.getDescricao().equals("descrição"));
		Assert.assertTrue(publicacao.getCategoria().equals(Categoria.ARBORIZACAO));
		Assert.assertTrue(publicacao.getUsuarioId().equals(usuarioId));
		Assert.assertTrue(publicacao.getCidadeId().equals(cidadeId));
		Assert.assertTrue(publicacao.getLatitude().equals(latitude));
		Assert.assertTrue(publicacao.getLongitude().equals(longitude));
		Assert.assertTrue(publicacao.getFotosId().get(0).equals(fotoId));
		Assert.assertTrue(publicacao.getCriticidade().equals(criticidadeProblema));
		Assert.assertTrue(publicacao.getDataPublicacao().equals(hoje));
		Assert.assertTrue(publicacao.getStatus().equals(Status.ABERTO));
		Assert.assertNull(publicacao.getDataFechamento());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComTituloVazio() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().titulo("").criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComTituloNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().titulo(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComDescricaoVazia() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().descricao("").criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComDescricaoNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().descricao(null).criar();
	}
	
	@Test(expected=NullPointerException.class)
	public void naodeveCriarSemCategoria() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().categoria(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComCidadeIdNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().cidadeId(null).criar();
	}
	
	@Test(expected=QuantidadeFotosPublicacaoException.class)
	public void naodeveCriarComFotoIdNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().fotoPublicacaoId(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComLatitudeNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().latitude(null).criar();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naodeveCriarComLongitudeNull() throws QuantidadeFotosPublicacaoException{
		new PublicacaoBuilder().longitude(null).criar();
	}
	
	@Test
	public void deveApoiar() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		publicacao.apoiar();
		Assert.assertTrue(publicacao.getQtdApoiadores().equals(1L));
	}
	
	@Test
	public void deveDesapoiar() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		publicacao.apoiar();
		Assert.assertTrue(publicacao.getQtdApoiadores().equals(1L));
		publicacao.desapoiar();
		Assert.assertTrue(publicacao.getQtdApoiadores().equals(0L));
	}
	
	@Test
	public void naoDeveDesapoiarSemEstarApoiado() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		Assert.assertTrue(publicacao.getQtdApoiadores().equals(0L));
		publicacao.desapoiar();
		Assert.assertTrue(publicacao.getQtdApoiadores().equals(0L));
	}
	
	@Test
	public void deveFecharPublicacao() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		Assert.assertNull(publicacao.getDataFechamento());
		publicacao.fechar();
		Calendar agora = Calendar.getInstance();
		Assert.assertTrue(publicacao.getStatus().equals(Status.FECHADO));
		Assert.assertTrue(publicacao.getDataFechamento().equals(agora));
	}
	
	@Test
	public void deveEditar() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		EditarPublicacaoCommand editarPublicacaoCommand = new EditarPublicacaoCommand("PUBLICACAO_ID", "teste", "teste", "ARBORIZACAO");
		publicacao.editar(editarPublicacaoCommand);
		Assert.assertTrue(publicacao.getTitulo().equals("teste"));
		Assert.assertTrue(publicacao.getDescricao().equals("teste"));
		Assert.assertTrue(publicacao.getCategoria().equals(Categoria.ARBORIZACAO));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveEditarComTituloVazio() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		EditarPublicacaoCommand editarPublicacaoCommand = new EditarPublicacaoCommand("PUBLICACAO_ID", "", "teste", "ARBORIZACAO");
		publicacao.editar(editarPublicacaoCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveEditarComTituloNulo() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		EditarPublicacaoCommand editarPublicacaoCommand = new EditarPublicacaoCommand("PUBLICACAO_ID", null, "teste", "ARBORIZACAO");
		publicacao.editar(editarPublicacaoCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveEditarComDescricaoVazia() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		EditarPublicacaoCommand editarPublicacaoCommand = new EditarPublicacaoCommand("PUBLICACAO_ID", "teste", "", "ARBORIZACAO");
		publicacao.editar(editarPublicacaoCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveEditarComDescricaoNula() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		EditarPublicacaoCommand editarPublicacaoCommand = new EditarPublicacaoCommand("PUBLICACAO_ID", "teste", null, "ARBORIZACAO");
		publicacao.editar(editarPublicacaoCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveEditarComCategoriaVazia() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		EditarPublicacaoCommand editarPublicacaoCommand = new EditarPublicacaoCommand("PUBLICACAO_ID", "teste", "teste", "");
		publicacao.editar(editarPublicacaoCommand);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveEditarComCategoriaNula() throws QuantidadeFotosPublicacaoException{
		Publicacao publicacao = new PublicacaoBuilder().criar();
		EditarPublicacaoCommand editarPublicacaoCommand = new EditarPublicacaoCommand("PUBLICACAO_ID", "teste", "teste", null);
		publicacao.editar(editarPublicacaoCommand);
	}
}
