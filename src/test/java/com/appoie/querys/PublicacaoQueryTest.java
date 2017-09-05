package com.appoie.querys;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appoie.AppoieApplication;
import com.appoie.builders.FotoBuilder;
import com.appoie.builders.PublicacaoBuilder;
import com.appoie.builders.UsuarioBuilder;
import com.appoie.commands.CadastrarCommand;
import com.appoie.dto.PublicacaoDetalhadaDTO;
import com.appoie.dto.PublicacaoMarcacaoDTO;
import com.appoie.dto.PublicacaoPreviaDTO;
import com.appoie.exceptions.SemResultadoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
import com.appoie.models.Cidade;
import com.appoie.models.Estado;
import com.appoie.models.FotoPublicacao;
import com.appoie.models.Publicacao;
import com.appoie.models.Sexo;
import com.appoie.models.Usuario;
import com.appoie.repositorys.CidadeRepository;
import com.appoie.repositorys.EstadoRepository;
import com.appoie.repositorys.FotoPublicacaoRepository;
import com.appoie.repositorys.PublicacaoRepository;
import com.appoie.repositorys.UsuarioRepository;
import com.appoie.utils.FotoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppoieApplication.class)
public class PublicacaoQueryTest {
	
	@Autowired
	private PublicacaoQuery publicacaoQuery; 
	
	private PublicacaoBuilder publicacaoBuilder = new PublicacaoBuilder();
	
	@Autowired
	private PublicacaoRepository repository;
	
	@Autowired
	private FotoPublicacaoRepository fotoPublicacaoRepository;
	
	@Autowired
	private FotoRepository fotoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@After
	public void depois(){
		repository.deleteAll();
	}
	
	@Test
	public void deveRetornarMarcadores(){
		CidadeId cidadeId = new CidadeId();
		Publicacao publicacao = publicacaoBuilder.cidadeId(cidadeId).criar();
		repository.save(publicacao);
		List<PublicacaoMarcacaoDTO> marcadores = publicacaoQuery.getMarcadores(cidadeId);
		
		assertTrue(marcadores.size() == 1);
	}
	
	@Test
	public void deveRetornarVariosMarcadores(){
		CidadeId cidadeId = new CidadeId();
		Publicacao publicacao = publicacaoBuilder.cidadeId(cidadeId).criar();
		Publicacao publicacao2 = publicacaoBuilder.cidadeId(cidadeId).criar();
		
		repository.save(publicacao);
		repository.save(publicacao2);
		
		List<PublicacaoMarcacaoDTO> marcadores = publicacaoQuery.getMarcadores(cidadeId);
		
		assertTrue(marcadores.size() == 2);
	}
	
	@Test
	public void naoDeveRetornarMarcadores(){
		CidadeId cidadeId = new CidadeId();
		List<PublicacaoMarcacaoDTO> marcadores = publicacaoQuery.getMarcadores(cidadeId);
		assertTrue(marcadores.size() == 0);
	}
	
	@Test
	public void deveRetornarPreviaPublicacao() throws SemResultadoException{
		FotoBuilder fotoBuilder = new FotoBuilder();
		UsuarioId usuarioId = new UsuarioId();
		Publicacao publicacao = publicacaoBuilder.usuarioId(usuarioId)
				.endereco(fotoBuilder.endereco)
				.foto(fotoBuilder.base64)
				.criar();
		FotoPublicacao fotoPublicacao = publicacaoBuilder
				.endereco(fotoRepository.save(fotoBuilder.base64, publicacao.getFotoPublicacaoId().getValue()))
				.criarFoto();
		repository.save(publicacao);
		fotoPublicacaoRepository.save(fotoPublicacao);
		
		PublicacaoPreviaDTO publicacaoPreviaDTO = publicacaoQuery.getPreviaPublicacao(publicacao.getId(), usuarioId);
		
		assertFalse(publicacaoPreviaDTO.apoiado);
		assertTrue(publicacaoPreviaDTO.criticidade.equals(publicacao.getCriticidade()));
		assertTrue(publicacaoPreviaDTO.idPublicacao.equals(publicacao.getId().getValue()));
		assertTrue(publicacaoPreviaDTO.qtdApoiadores == 0);
		assertTrue(publicacaoPreviaDTO.status.equals(publicacao.getStatus()));
		assertTrue(publicacaoPreviaDTO.titulo.equals(publicacao.getTitulo()));
	}
	
	@Test
	public void naoDeveRetornarNPreviasPublicacao() throws SemResultadoException{
		FotoBuilder fotoBuilder = new FotoBuilder();
		UsuarioId usuarioId = new UsuarioId();
		Publicacao publicacao = publicacaoBuilder.usuarioId(usuarioId)
				.endereco(fotoBuilder.endereco)
				.foto(fotoBuilder.base64)
				.criar();
		
		Publicacao publicacao2 = publicacaoBuilder.usuarioId(usuarioId)
				.endereco(fotoBuilder.endereco)
				.foto(fotoBuilder.base64)
				.criar();
		
		FotoPublicacao fotoPublicacao = publicacaoBuilder
				.endereco(fotoRepository.save(fotoBuilder.base64, publicacao.getFotoPublicacaoId().getValue()))
				.criarFoto();
		
		FotoPublicacao fotoPublicacao2 = publicacaoBuilder
				.endereco(fotoRepository.save(fotoBuilder.base64, publicacao2.getFotoPublicacaoId().getValue()))
				.criarFoto();
		
		repository.save(publicacao);
		repository.save(publicacao2);
		fotoPublicacaoRepository.save(fotoPublicacao);
		fotoPublicacaoRepository.save(fotoPublicacao2);
		
		PublicacaoPreviaDTO publicacaoPreviaDTO = publicacaoQuery.getPreviaPublicacao(publicacao.getId(), usuarioId);
		PublicacaoPreviaDTO publicacaoPreviaDTO2 = publicacaoQuery.getPreviaPublicacao(publicacao2.getId(), usuarioId);
		
		assertFalse(publicacaoPreviaDTO.apoiado);
		assertTrue(publicacaoPreviaDTO.criticidade.equals(publicacao.getCriticidade()));
		assertTrue(publicacaoPreviaDTO.idPublicacao.equals(publicacao.getId().getValue()));
		assertTrue(publicacaoPreviaDTO.qtdApoiadores == 0);
		assertTrue(publicacaoPreviaDTO.status.equals(publicacao.getStatus()));
		assertTrue(publicacaoPreviaDTO.titulo.equals(publicacao.getTitulo()));
		
		assertFalse(publicacaoPreviaDTO2.apoiado);
		assertTrue(publicacaoPreviaDTO2.criticidade.equals(publicacao2.getCriticidade()));
		assertTrue(publicacaoPreviaDTO2.idPublicacao.equals(publicacao2.getId().getValue()));
		assertTrue(publicacaoPreviaDTO2.qtdApoiadores == 0);
		assertTrue(publicacaoPreviaDTO2.status.equals(publicacao2.getStatus()));
		assertTrue(publicacaoPreviaDTO2.titulo.equals(publicacao2.getTitulo()));
	}
	
	@Test(expected=SemResultadoException.class)
	public void naoDeveRetornarPreviasPublicacao() throws SemResultadoException{
		FotoBuilder fotoBuilder = new FotoBuilder();
		UsuarioId usuarioId = new UsuarioId();
		Publicacao publicacao = publicacaoBuilder.usuarioId(usuarioId)
				.endereco(fotoBuilder.endereco)
				.foto(fotoBuilder.base64)
				.criar();
		
		PublicacaoPreviaDTO publicacaoPreviaDTO = publicacaoQuery.getPreviaPublicacao(publicacao.getId(), usuarioId);
		
		assertTrue(publicacaoPreviaDTO == null);
	}
	
	@Test
	public void deveRetonarDetalhesPublicacao() throws Exception{
		FotoBuilder fotoBuilder = new FotoBuilder();
		Estado estado = new Estado("Teste");
		Cidade cidade = new Cidade(estado.getId(), "Teste");
		Usuario usuario = new UsuarioBuilder().criar();
		Publicacao publicacao = publicacaoBuilder.usuarioId(usuario.getId())
				.cidadeId(cidade.getId())
				.endereco(fotoBuilder.endereco)
				.foto(fotoBuilder.base64)
				.criar();
		FotoPublicacao fotoPublicacao = publicacaoBuilder
				.endereco(fotoRepository.save(fotoBuilder.base64, publicacao.getFotoPublicacaoId().getValue()))
				.criarFoto();
		usuarioRepository.save(usuario);
		estadoRepository.save(estado);
		cidadeRepository.save(cidade);
		repository.save(publicacao);
		fotoPublicacaoRepository.save(fotoPublicacao);
		
		PublicacaoDetalhadaDTO publicacaoDetalhesDTO = publicacaoQuery.getDetalhesPublicacao(publicacao.getId(), usuario.getId());
		
		assertFalse(publicacaoDetalhesDTO.apoiado);
		assertTrue(publicacaoDetalhesDTO.criticidade.equals(publicacao.getCriticidade()));
		assertTrue(publicacaoDetalhesDTO.idPublicacao.equals(publicacao.getId().getValue()));
		assertTrue(publicacaoDetalhesDTO.qtdApoiadores == 0);
		assertTrue(publicacaoDetalhesDTO.status.equals(publicacao.getStatus()));
		assertTrue(publicacaoDetalhesDTO.titulo.equals(publicacao.getTitulo()));
		
	}
	
	@Test(expected=SemResultadoException.class)
	public void deveNaoRetonarDetalhesPublicacao() throws Exception{
		FotoBuilder fotoBuilder = new FotoBuilder();
		
		Estado estado = new Estado("Teste");
		Cidade cidade = new Cidade(estado.getId(), "Teste");
		Usuario usuario = new UsuarioBuilder().criar();
		Publicacao publicacao = publicacaoBuilder.usuarioId(usuario.getId())
				.cidadeId(cidade.getId())
				.endereco(fotoBuilder.endereco)
				.foto(fotoBuilder.base64)
				.criar();
		FotoPublicacao fotoPublicacao = publicacaoBuilder
				.endereco(fotoRepository.save(fotoBuilder.base64, publicacao.getFotoPublicacaoId().getValue()))
				.criarFoto();
		usuarioRepository.save(usuario);
		estadoRepository.save(estado);
		cidadeRepository.save(cidade);
		
		PublicacaoDetalhadaDTO publicacaoDetalhesDTO = publicacaoQuery.getDetalhesPublicacao(publicacao.getId(), usuario.getId());
		
		assertFalse(publicacaoDetalhesDTO.apoiado);
		assertTrue(publicacaoDetalhesDTO.criticidade.equals(publicacao.getCriticidade()));
		assertTrue(publicacaoDetalhesDTO.idPublicacao.equals(publicacao.getId().getValue()));
		assertTrue(publicacaoDetalhesDTO.qtdApoiadores == 0);
		assertTrue(publicacaoDetalhesDTO.status.equals(publicacao.getStatus()));
		assertTrue(publicacaoDetalhesDTO.titulo.equals(publicacao.getTitulo()));
		
	}
}
