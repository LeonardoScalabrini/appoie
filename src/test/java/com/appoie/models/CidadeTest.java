package com.appoie.models;
import static org.junit.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public class CidadeTest {
	
	private Cidade cidade;	
	private Estado estado;
	

	@Before
	public void setUp() throws Exception {
		
		
		
	}

	@Test
	public void cidadeDeveReceberIdDeEstado() {
		Cidade cidade =new Cidade();
		Estado estado =new Estado();
	   assertEquals(cidade.getEstadoId().getId(), estado.getId().getId());
		
	}

}
