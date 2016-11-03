package com.appoie.utils;

import com.appoie.ids.CidadeId;
import com.appoie.ids.UsuarioId;

public class Sessao {

	private static UsuarioId usuarioId;
	private static CidadeId cidadeId;
	
	public static UsuarioId getUsuarioId() {
		return usuarioId;
	}

	public static void setUsuarioId(UsuarioId id) {
		usuarioId = id;
	}

	public static CidadeId getCidadeId() {
		return cidadeId;
	}

	public static void setCidadeId(CidadeId id) {
		cidadeId = id;
	}
}
