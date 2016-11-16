package com.appoie.models;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.ApoiadorId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.utils.ValidationObject;

@Entity
public class Apoiador extends BasicEntity<ApoiadorId> {
	@AttributeOverride(name = "id", column = @Column(name = "publicacao_id") )
	private PublicacaoId idPublicacao;
	@AttributeOverride(name = "id", column = @Column(name = "usuario_id") )
	private UsuarioId apoiador;

	private Apoiador() {
		super(new ApoiadorId());
	}

	public Apoiador(PublicacaoId idPublicacao, UsuarioId usuarioId) {
		this();
		ValidationObject.isNull(usuarioId);
		ValidationObject.isNull(idPublicacao);
		this.idPublicacao = idPublicacao;
		this.apoiador = usuarioId;
	}

	public PublicacaoId getIdPublicacao() {
		return idPublicacao;
	}

	public UsuarioId getApoiador() {
		return apoiador;
	}

}
