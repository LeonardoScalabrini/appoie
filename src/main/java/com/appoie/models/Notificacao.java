package com.appoie.models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.ids.NotificacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;

@Entity
public class Notificacao extends BasicEntity<NotificacaoId> {
	@Temporal(TemporalType.DATE)
	private Calendar dataProximaNotificacao;
	@AttributeOverride(name = "id", column = @Column(name = "publicacao_id") )
	private PublicacaoId idPublicacao;
	@AttributeOverride(name = "id", column = @Column(name = "usuario_id") )
	private UsuarioId idUsuario;

	public Notificacao() {
		super(new NotificacaoId());
	}

	public Notificacao(Calendar data, PublicacaoId idPublicacao, UsuarioId idUsuario) {
		super(new NotificacaoId());
		this.dataProximaNotificacao = data;
		this.idPublicacao = idPublicacao;
		this.idUsuario = idUsuario;
	}

	public Notificacao(NotificacaoId idNotificacao, Calendar data, PublicacaoId publicacaoId, UsuarioId usuarioId) {
		super(idNotificacao);
		this.dataProximaNotificacao = data;
		this.idPublicacao = publicacaoId;
		this.idUsuario = usuarioId;
	}

	public Calendar getDataProximaNotificacao() {
		return dataProximaNotificacao;
	}

	public PublicacaoId getIdPublicacao() {
		return idPublicacao;
	}

	public UsuarioId getIdUsuario() {
		return idUsuario;
	}

}
