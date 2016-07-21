package com.appoie.models;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.appoie.builders.FotoPulbicacaoBuilder;
import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;
import com.appoie.repositorys.FotoPublicacaoRepository;


public class PublicacaoTest {
	@Autowired
	private CidadeId idCidade;
	@Autowired
	private UsuarioId usuarioId;
	@Autowired
	private FotoPublicacaoRepository fotoPublicacao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveAceitarTitulo() {
		Publicacao publicacaoMock =mock(Publicacao.class);
		when(publicacaoMock.getTitulo()).thenReturn("Buraco na Rua");
		assertEquals("Buraco na Rua",publicacaoMock.getTitulo());		
	}
	@Test
	public void deveAceitarDescricao(){
		Publicacao publicacaoMock =mock(Publicacao.class);
		when(publicacaoMock.getDescrição()).thenReturn("Aqui na av Mário Marangoni tema a ocorrencia de Um buraco");	
		assertEquals("Aqui na av Mário Marangoni tema a ocorrencia de Um buraco", publicacaoMock.getDescrição());
				
	}
	@Test(expected=RuntimeException.class)
	public void naoAceitaIdUsuarioNulonapublicacao() throws Exception {
		//Publicacao publicacao =new Publicacao();
		//assertTrue(publicacao.getUsuarioId().equals(null));
	}
	
	@Test(expected=RuntimeException.class)
	public void naoaceitaIdCidadeNulonaPublicacao() throws Exception {
		//Publicacao publicacao =new Publicacao();
		//assertTrue(publicacao.getCidadeId().equals(null));
	}
	@Test
	public void acrescentarPublicacao() throws Exception {
		FotoPublicacao foto =new FotoPulbicacaoBuilder().criar();
		
	}
	
	

}
