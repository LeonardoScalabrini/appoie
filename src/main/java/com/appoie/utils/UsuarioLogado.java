package com.appoie.utils;

import javax.servlet.http.HttpSession;

import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;

public class UsuarioLogado {

	public final String USUARIO_LOGADO = "usuarioLogado";
	public final String CIDADE_ATUAL = "cidadeAtual";
	public final String CONT_PUBLICACOES_RECUPERADAS = "0";

	private HttpSession session;

	public UsuarioLogado(HttpSession session) {
		this.session = session;
	}

	public UsuarioId getId() {
		return (UsuarioId) session.getAttribute(USUARIO_LOGADO);
	}

	public void incrementaContPublicacoesRecuperadas() {
		if (session.getAttribute(CONT_PUBLICACOES_RECUPERADAS) == "0") {
			session.setAttribute(CONT_PUBLICACOES_RECUPERADAS, 1);

		} else {
			int valor = Integer.parseInt( session.getAttribute(CONT_PUBLICACOES_RECUPERADAS).toString());
			valor++;
			session.setAttribute(CONT_PUBLICACOES_RECUPERADAS, Integer.toString(valor));
		}
	}

	public void setId(UsuarioId id) {
		session.setAttribute(USUARIO_LOGADO, id);
	}

	public CidadeId getCidadeId() {
		return (CidadeId) session.getAttribute(CIDADE_ATUAL);
	}

	public void setCidadeId(CidadeId id) {
		session.setAttribute(CIDADE_ATUAL, id);

	}

	public String getContPublicacoesRecuperadas() {
		return (String)session.getAttribute(CONT_PUBLICACOES_RECUPERADAS);
		 //(String) session.getAttribute(CONT_PUBLICACOES_RECUPERADAS);
	}
	
	public void initContPublicacoesRecuperas() {
		session.setAttribute(CONT_PUBLICACOES_RECUPERADAS, Integer.toString(0));
	}
	
	



	

}
