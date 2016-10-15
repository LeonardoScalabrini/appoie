package com.appoie.models;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import com.appoie.ids.ApoiadorId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;

@Entity
public class Apoiador extends BasicEntity<ApoiadorId> {
	@AttributeOverride(name = "id", column = @Column(name = "publicacao_id") )
	private PublicacaoId idPublicacao;
	@AttributeOverride(name = "id", column = @Column(name = "usuario_id") )
	private UsuarioId apoiador;

	private Apoiador() {
		super(new ApoiadorId());
	}

	public Apoiador(PublicacaoId idPublicacao, UsuarioId idApoiador) {
		super(new ApoiadorId());
		this.setApoiador(idApoiador);
		this.setIdPublicacao(idPublicacao);
	}

	private void setApoiador(UsuarioId idApoiador) {
		this.apoiador = idApoiador;
	}

	private void setIdPublicacao(PublicacaoId idPublicacao) {
		this.idPublicacao = idPublicacao;
	}

	public PublicacaoId getIdPublicacao() {
		return idPublicacao;
	}

	public UsuarioId getApoiadores() {
		return apoiador;
	}

}
