package com.appoie.models;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.ids.NotificacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.utils.ValidationObject;

@Entity
public class Notificacao extends BasicEntity<NotificacaoId> {
	@Temporal(TemporalType.DATE)
	private Calendar dataProximaNotificacao;
	@AttributeOverride(name = "id", column = @Column(name = "publicacao_id") )
	private PublicacaoId idPublicacao;
	@AttributeOverride(name = "id", column = @Column(name = "usuario_id") )
	private UsuarioId idUsuario;

	private Notificacao() {
		super(new NotificacaoId());
	}

	public Notificacao(Calendar data, PublicacaoId idPublicacao, UsuarioId idUsuario) {
		this();
		setDataProximaNotificacao(data);
		setPublicacaoId(idPublicacao);
		setUsuarioId(idUsuario);
	}

	public Notificacao(NotificacaoId idNotificacao, Calendar data, PublicacaoId publicacaoId, UsuarioId usuarioId) {
		super(idNotificacao);
		setDataProximaNotificacao(data);
		setPublicacaoId(publicacaoId);
		setUsuarioId(usuarioId);
	}
	
	private void setDataProximaNotificacao(Calendar dataProximaNotificacao){
		ValidationObject.isNull(dataProximaNotificacao);
		this.dataProximaNotificacao = dataProximaNotificacao;
	}
	
	private void setPublicacaoId(PublicacaoId publicacaoId){
		ValidationObject.isNull(publicacaoId);
		this.idPublicacao = publicacaoId;
	}
	
	private void setUsuarioId(UsuarioId usuarioId){
		ValidationObject.isNull(usuarioId);
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
