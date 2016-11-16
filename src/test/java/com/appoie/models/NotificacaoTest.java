package com.appoie.models;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import com.appoie.ids.NotificacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;

public class NotificacaoTest {

	@Test
	public void deveCriar(){
		Calendar data = Calendar.getInstance();
		PublicacaoId idPublicacao = new PublicacaoId();
		UsuarioId usuarioId = new UsuarioId();
		Notificacao notificacao = new Notificacao(data, idPublicacao, usuarioId);

		Assert.assertTrue(notificacao.getDataProximaNotificacao().equals(data));
		Assert.assertTrue(notificacao.getIdPublicacao().equals(idPublicacao));
		Assert.assertTrue(notificacao.getIdUsuario().equals(usuarioId));
	}
	
	@Test
	public void deveCriar2(){
		Calendar data = Calendar.getInstance();
		PublicacaoId idPublicacao = new PublicacaoId();
		UsuarioId usuarioId = new UsuarioId();
		NotificacaoId notificacaoId = new NotificacaoId();
		Notificacao notificacao = new Notificacao(notificacaoId, data, idPublicacao, usuarioId);

		Assert.assertTrue(notificacao.getDataProximaNotificacao().equals(data));
		Assert.assertTrue(notificacao.getIdPublicacao().equals(idPublicacao));
		Assert.assertTrue(notificacao.getIdUsuario().equals(usuarioId));
		Assert.assertTrue(notificacao.getId().equals(notificacaoId));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComIdNullo(){
		new Notificacao(Calendar.getInstance(), null, new UsuarioId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComDataNullo(){
		new Notificacao(null, new PublicacaoId(), new UsuarioId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriarComUsuarioId(){
		new Notificacao(Calendar.getInstance(), new PublicacaoId(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriar2ComIdPublicacaoNullo(){
		new Notificacao(new NotificacaoId(), Calendar.getInstance(), null, new UsuarioId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriar2ComDataNullo(){
		new Notificacao(new NotificacaoId(), null, new PublicacaoId(), new UsuarioId());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriar2ComUsuarioId(){
		new Notificacao(new NotificacaoId(), Calendar.getInstance(), new PublicacaoId(), null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveCriar2ComIdNotificacaoNullo(){
		new Notificacao(null, Calendar.getInstance(), new PublicacaoId(), new UsuarioId());
	}
}
