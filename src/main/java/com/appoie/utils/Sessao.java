package com.appoie.utils;

import javax.servlet.http.HttpSession;

import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;

public class Sessao {

<<<<<<< HEAD:src/main/java/com/appoie/utils/UsuarioLogado.java
	public final String USUARIO_LOGADO = "usuarioLogado";
	public final String CIDADE_ATUAL = "cidadeAtual";
=======
	private final String USUARIO_LOGADO = "usuarioLogado";
	private final String CIDADE_ATUAL = "cidadeAtual";
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f:src/main/java/com/appoie/utils/Sessao.java

	private HttpSession session;

	public Sessao(HttpSession session) {
		this.session = session;
	}

	public UsuarioId getUsuarioId() {
		return (UsuarioId) session.getAttribute(USUARIO_LOGADO);
	}

<<<<<<< HEAD:src/main/java/com/appoie/utils/UsuarioLogado.java
	public void setId(UsuarioId id) {
=======
	public void setUsuarioId(UsuarioId id) {
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f:src/main/java/com/appoie/utils/Sessao.java
		session.setAttribute(USUARIO_LOGADO, id);
	}

	public CidadeId getCidadeId() {
		return (CidadeId) session.getAttribute(CIDADE_ATUAL);
	}

	public void setCidadeId(CidadeId id) {
		session.setAttribute(CIDADE_ATUAL, id);
	}
}
