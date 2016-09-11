package com.appoie.models;

import static com.appoie.utils.ValidationObject.isNull;
import static com.appoie.utils.ValidationString.isNullOrEmpty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.commands.PublicacaoCommand;
import com.appoie.commands.PublicacaoEditarCommand;
import com.appoie.exceptions.QuantidadeFotosPublicacaoException;
import com.appoie.ids.CidadeId;
import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;

@Entity
public class Publicacao extends BasicEntity<PublicacaoId> {

	@AttributeOverride(name = "id", column = @Column(name = "usuario_id") )
	private UsuarioId usuarioId;
	
	@AttributeOverride(name = "id", column = @Column(name = "cidade_id") )
	private CidadeId cidadeId;
	
	private String titulo;
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataPublicacao = Calendar.getInstance();

	@ElementCollection
	@CollectionTable(name = "FotoPublicacao", joinColumns = @JoinColumn(name = "publicacaoId") )
	private List<FotoPublicacaoId> fotosId = new ArrayList<>();

	private Publicacao() {
		super(new PublicacaoId());
	}

	public Publicacao(UsuarioId usuarioId, CidadeId cidadeId, String titulo, String descricao, Categoria categoria,
			List<FotoPublicacaoId> fotosId) throws QuantidadeFotosPublicacaoException {
		this();
		isNull(usuarioId);
		isNull(cidadeId);
		setUsuarioId(usuarioId);
		setCidadeId(cidadeId);
		setTitulo(titulo);
		setDescricao(descricao);
		setCategoria(categoria);
		setFotos(fotosId);
	}

	public Publicacao(PublicacaoCommand command, UsuarioId usuarioId, CidadeId cidadeId, List<FotoPublicacaoId> fotosId) throws QuantidadeFotosPublicacaoException{
		this();
		setUsuarioId(usuarioId);
		setCidadeId(cidadeId);
		setTitulo(command.titulo);
		setDescricao(command.descricao);
		setCategoria(command.categoria);
		setFotos(fotosId);
	}
	
	public void editar(PublicacaoEditarCommand command) {
		setTitulo(command.titulo);
		setDescricao(command.descricao);
		setCategoria(command.categoria);
	}

	private void setFotos(List<FotoPublicacaoId> fotosId) throws QuantidadeFotosPublicacaoException {
		if (fotosId.size() < 1 || fotosId.size() > 3)
			throw new QuantidadeFotosPublicacaoException();
		
			this.fotosId = fotosId;
	}
	
	private void setCidadeId(CidadeId cidadeId) {
		isNull(cidadeId);
		this.cidadeId = cidadeId;
	}

	private void setUsuarioId(UsuarioId usuarioId) {
		isNull(usuarioId);
		this.usuarioId = usuarioId;
	}

	public void setTitulo(String titulo) {
		isNullOrEmpty(titulo);
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		isNullOrEmpty(descricao);
		this.descricao = descricao;
	}

	public void setCategoria(Categoria categoria) {
		isNull(categoria);
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public UsuarioId getUsuarioId() {
		return usuarioId;
	}

	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}

	public CidadeId getCidadeId() {
		return cidadeId;
	}
}
