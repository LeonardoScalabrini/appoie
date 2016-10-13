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

import com.appoie.commands.SalvarPublicacaoCommand;
import com.appoie.commands.EditarPublicacaoCommand;
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
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataPublicacao = Calendar.getInstance();

	@ElementCollection
	@CollectionTable(name = "FotoPublicacao", joinColumns = @JoinColumn(name = "publicacaoId") )
	private List<FotoPublicacaoId> fotosId = new ArrayList<>();
	
	private Long qtdApoiadores = 0L;
	
	private Double latitude;
	private Double longitude;
	
	private Publicacao() {
		super(new PublicacaoId());
	}

	public Publicacao(SalvarPublicacaoCommand command, UsuarioId usuarioId, CidadeId cidadeId, List<FotoPublicacaoId> fotosId) throws QuantidadeFotosPublicacaoException{
		this();
		setUsuarioId(usuarioId);
		setCidadeId(cidadeId);
		setTitulo(command.titulo);
		setDescricao(command.descricao);
		setCategoria(command.categoria);
		setFotos(fotosId);
		setLatitude(command.lat);
		setLongitude(command.lng);
		status = Status.ABERTO;
	}

	public void editar(EditarPublicacaoCommand command) {
		setTitulo(command.titulo);
		setDescricao(command.descricao);
		setCategoria(command.categoria);
	}
	
	public void apoiar(){
		this.qtdApoiadores++;
	}

	public void fechar(){
		status = Status.FECHADO;
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

	private void setTitulo(String titulo) {
		isNullOrEmpty(titulo);
		this.titulo = titulo;
	}

	private void setDescricao(String descricao) {
		isNullOrEmpty(descricao);
		this.descricao = descricao;
	}

	private void setCategoria(Categoria categoria) {
		isNull(categoria);
		this.categoria = categoria;
	}
	
	private void setLongitude(Double lng) {
		isNull(lng);
		this.longitude = lng;
	}

	private void setLatitude(Double lat) {
		isNull(lat);
		this.latitude = lat;
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

<<<<<<< HEAD
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
	public List<FotoPublicacaoId> getFotosId() {
		return fotosId;
	}
	
>>>>>>> c689ad998e6d1dda6c9864ec9d71c77ecc5d9757

=======
	public Double getLatitude() {
		return latitude;
	}
>>>>>>> 04cc248f05638bfe5ce43b6d49990d9e0d208f4f

	public Double getLongitude() {
		return longitude;
	}
}
