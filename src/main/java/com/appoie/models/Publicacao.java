package com.appoie.models;

import static com.appoie.utils.ValidationObject.isNull;
import static com.appoie.utils.ValidationString.isNullOrEmpty;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.appoie.commands.EditarPublicacaoCommand;
import com.appoie.commands.SalvarPublicacaoCommand;
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

	@AttributeOverride(name = "id", column = @Column(name = "foto_publicacao_id") )
	private FotoPublicacaoId fotoPublicacaoId;
	
	private Long qtdApoiadores = 0L;
	
	private Double latitude;
	private Double longitude;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataFechamento;
	
	@Enumerated(EnumType.STRING)
	private CriticidadeProblema criticidade;
	
	private Publicacao() {
		super(new PublicacaoId());
	}

	public Publicacao(SalvarPublicacaoCommand command, UsuarioId usuarioId, CidadeId cidadeId, FotoPublicacaoId fotoPublicacaoId){
		this();
		setUsuarioId(usuarioId);
		setCidadeId(cidadeId);
		setTitulo(command.titulo);
		setDescricao(command.descricao);
		setCategoria(command.categoria);
		setFoto(fotoPublicacaoId);
		setLatitude(command.lat);
		setLongitude(command.lng);
		status = Status.ABERTO;
		this.criticidade = command.criticidade;
	}

	public void editar(EditarPublicacaoCommand command) {
		setTitulo(command.titulo);
		setDescricao(command.descricao);
		setCategoria(command.categoria);
	}

	public void apoiar(){
		this.qtdApoiadores++;
	}

	
	private void setFoto(FotoPublicacaoId fotoPublicacaoId){
		isNull(fotoPublicacaoId);	
		this.fotoPublicacaoId = fotoPublicacaoId;
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

	private void setCategoria(String categoria) {
		isNull(categoria);
		this.categoria = Categoria.valueOf(categoria.toUpperCase());
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

	public Double getLatitude() {
		return latitude;
	}


	public Double getLongitude() {
		return longitude;
	}

	public Long getQtdApoiadores() {
		return qtdApoiadores;
	}
	
	public Status getStatus() {
		return status;
	}

	public FotoPublicacaoId getFotoPublicacaoId() {
		return fotoPublicacaoId;
	}

	public CriticidadeProblema getCriticidade() {
		return criticidade;
	}
	
	public void fechar() {
		this.status = Status.FECHADO;
		this.dataFechamento = Calendar.getInstance();
	}

	public void desapoiar() {
		if(qtdApoiadores == 0)
			return;
		
		this.qtdApoiadores--;
		
	}

	public Calendar getDataFechamento() {
		return dataFechamento;
	}
	
	
	
	
	
}
