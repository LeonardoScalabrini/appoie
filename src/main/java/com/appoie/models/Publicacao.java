package com.appoie.models;

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
import com.appoie.exceptions.NumeroFotosPublicacaoInvalido;
import com.appoie.ids.CidadeId;
import com.appoie.ids.FotoPublicacaoId;
import com.appoie.ids.PublicacaoId;
import com.appoie.ids.UsuarioId;
import com.appoie.utils.UsuarioLogado;

import static com.appoie.utils.ValidationObject.*;

import static com.appoie.utils.ValidationString.*;

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
			List<FotoPublicacaoId> fotosId) throws NumeroFotosPublicacaoInvalido {
		this();
		isNull(usuarioId);
		isNull(cidadeId);
		this.usuarioId = usuarioId;
		this.cidadeId = cidadeId;
		setTitulo(titulo);
		setDescricao(descricao);
		setCategoria(categoria);

		if (fotosId.size() < 1 || fotosId.size() > 3) {

			throw new NumeroFotosPublicacaoInvalido();

		} else {
			for (FotoPublicacaoId foto : fotosId) {
				this.fotosId.add(foto);
			}
		}
	}

	public Publicacao(PublicacaoCommand command, UsuarioId usuarioId, List<FotoPublicacao> fotos, CidadeId cidadeId)
			throws NumeroFotosPublicacaoInvalido {
		this();
		isNull(command);
		this.usuarioId = usuarioId;
		this.cidadeId = cidadeId;
		this.titulo = command.titulo;
		this.descricao = command.descricao;
		this.categoria = command.categoria;
		this.dataPublicacao = command.dataPublicação;

		if (fotos.size() < 1 || fotos.size() > 3) {

			throw new NumeroFotosPublicacaoInvalido();

		} else {
			for (FotoPublicacao foto : fotos) {

				this.fotosId.add(foto.getId());
			}
		}

	}

	public Publicacao(PublicacaoEditarCommand command, CidadeId cidadeId, Calendar dataPublicacao, UsuarioId usuarioId, List<FotoPublicacaoId> fotosId) {
		super(command.id);
		isNull(command);
		setUsuarioId(usuarioId);
		setCidadeId(cidadeId);
		setTitulo(command.titulo);
		setDescricao(command.descricao);
		setCategoria(command.categoria);
		setDataPublicacao(dataPublicacao);
		setFotosId(fotosId);

	}

	private void setFotosId(List<FotoPublicacaoId> fotosId) {
		this.fotosId = fotosId;

	}

	private void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;

	}

	private void setCidadeId(CidadeId cidadeId) {
		this.cidadeId = cidadeId;

	}

	private void setUsuarioId(UsuarioId usuarioId) {
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

	public List<FotoPublicacaoId> getFotosId() {
		return fotosId;
	}
	


}
