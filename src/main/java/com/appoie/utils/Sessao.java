package com.appoie.utils;

import javax.servlet.http.HttpSession;

import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;

public class Sessao {


	public final String USUARIO_LOGADO = "usuarioLogado";
	public final String CIDADE_ATUAL = "cidadeAtual";

	


	private HttpSession session;

	public Sessao(HttpSession session) {
		this.session = session;
	}

	public UsuarioId getUsuarioId() {
		return (UsuarioId) session.getAttribute(USUARIO_LOGADO);
	}


	public void setId(UsuarioId id) {
		
	}

	public void setUsuarioId(UsuarioId id) {

		session.setAttribute(USUARIO_LOGADO, id);
	}

	public CidadeId getCidadeId() {
		return (CidadeId) session.getAttribute(CIDADE_ATUAL);
	}

	public void setCidadeId(CidadeId id) {
		session.setAttribute(CIDADE_ATUAL, id);
	}

	
}
