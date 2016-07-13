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
		setDescrição(descricao);
		setCategoria(categoria);

		if (fotosId.size() < 1 || fotosId.size() > 3) {

			throw new NumeroFotosPublicacaoInvalido();

		} else {
			for (FotoPublicacaoId foto : fotosId) {
				this.fotosId.add(foto);
			}
		}
	}

	public Publicacao(PublicacaoCommand command, UsuarioId usuarioId, List<FotoPublicacao> fotos) throws NumeroFotosPublicacaoInvalido {
		this();
		isNull(command);
		this.usuarioId = usuarioId;				
		this.cidadeId = command.cidadeId;
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

	public void setTitulo(String titulo) {
		isNullOrEmpty(titulo);
		this.titulo = titulo;
	}

	public void setDescrição(String descrição) {
		isNullOrEmpty(descrição);
		this.descricao = descrição;
	}

	public void setCategoria(Categoria categoria) {
		isNull(categoria);
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescrição() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public UsuarioId getUsuarioId() {
		return usuarioId;
	}

	public Calendar getDataPublicação() {
		return dataPublicacao;
	}

	public CidadeId getCidadeId() {
		return cidadeId;
	}
<<<<<<< HEAD

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((cidadeId == null) ? 0 : cidadeId.hashCode());
		result = prime * result + ((dataPublicação == null) ? 0 : dataPublicação.hashCode());
		result = prime * result + ((descrição == null) ? 0 : descrição.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publicacao other = (Publicacao) obj;
		if (categoria != other.categoria)
			return false;
		if (cidadeId == null) {
			if (other.cidadeId != null)
				return false;
		} else if (!cidadeId.equals(other.cidadeId))
			return false;
		if (dataPublicação == null) {
			if (other.dataPublicação != null)
				return false;
		} else if (!dataPublicação.equals(other.dataPublicação))
			return false;
		if (descrição == null) {
			if (other.descrição != null)
				return false;
		} else if (!descrição.equals(other.descrição))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuarioId == null) {
			if (other.usuarioId != null)
				return false;
		} else if (!usuarioId.equals(other.usuarioId))
			return false;
		return true;
	}
	
	
=======
>>>>>>> 965d571481b94a573f411ca0db11be076890ab90

}
